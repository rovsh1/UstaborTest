import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.Email;

import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@RunWith(SerenityRunner.class)
@AddCategory(requestClickPrice = 500)
@AddMasters(addProject = false)
public class TC013_CustomerRequestAutoAmountWithdraw extends TestBase {

    protected Email email = new Email();

    @Test
    public void verifyAutoWithdrawForRequest() throws TimeoutException {
        var customer = DataGenerator.getCustomer(email.getEmailAddress());
        watcher.users.add(customer);

        user.atHomePage.registerAsCustomer(customer.getEmail(), customer.getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());

        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());

        user.atHomePage.openHomePage();
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrder(customer, category);
        user.atPlaceOrderPage.openRequestsPage();

        customer.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());
        var requestId = user.atCustomerProfileRequestsPage.getRequestId();

        user.atHomePage.logsOut();

        var masters = watcher.users.stream()
                .filter(master -> master.getClass().getSimpleName().equals("Master"))
                .collect(Collectors.toList());
        masters.forEach(m -> admin.atMastersPage.addMoneyToMaster(2000, m.getProfileId()));
        admin.atCategoriesPage.addMastersToCategoryRequest(category, masters);
        admin.atRequestsPage.openRequestById(requestId);
        admin.atRequestsPage.assignRequestToAllMastersOfCategory();

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMaster(masters.get(0).getEmail(), masters.get(0).getPassword(), true);
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyBalance(1000);
        user.atMasterProfileRequestsPage.clickPhoneButton();
        user.atMasterProfileRequestsPage.verifyCustomerInfo(customer);
        user.atHomePage.logsOut();

        user.atHomePage.loginAsMaster(masters.get(1).getEmail(), masters.get(1).getPassword(), true);
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterProfileRequestsPage.verifyCustomerConnectButtonHasNoPrice();
        user.atMasterProfileRequestsPage.clickConnectClientButton();
        user.atMasterProfileRequestsPage.verifyCustomerInfo(customer);
        user.atMasterProfilePage.open();
        user.atMasterProfilePage.verifyBalance(1000);
        user.atHomePage.logsOut();

        user.atHomePage.loginAsCustomer(customer.getEmail(), customer.getPassword());
        user.atCustomerProfileRequestsPage.open();
        user.atCustomerRequestPage.openRequest();
        user.atCustomerRequestPage.openAssignedMasters();
        masters.forEach(master -> user.atCustomerRequestPage.verifyMasterAssigned(master));

        user.atCustomerProfileRequestsPage.open();
        user.atCustomerProfileRequestsPage.deleteRequest();
        user.atCustomerProfileRequestsPage.verifyRequestsTabIsEmpty();
    }
}
