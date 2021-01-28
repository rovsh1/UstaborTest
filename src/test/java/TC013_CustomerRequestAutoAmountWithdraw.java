import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.Email;

import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true, addRequestQuestion = true)
@AddMasters(addProject = false)
@WithTag("new")
public class TC013_CustomerRequestAutoAmountWithdraw extends TestBase {

    protected Email email = new Email();

    @Test
    public void verifyAutoWithdrawForRequest() throws TimeoutException {
        var customer = DataGenerator.getCustomer(email.getEmailAddress());
        watcher.users.add(customer);

        user.atHomePage.registerAsCustomer(customer);
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
        user.atHomePage.login(masters.get(0), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyBalance(1000);
        user.atMasterProfileRequestsPage.clickPhoneButton();
        user.atMasterRequestPage.verifyCustomerInfo(customer);
        user.atMasterRequestPage.closeConnectCustomerPopup();
        user.atHomePage.logsOut();

        user.atHomePage.login(masters.get(1), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterRequestPage.verifyCustomerConnectButtonHasNoPrice();
        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.verifyCustomerInfo(customer);
        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.verifyBalance(1000);
        user.atHomePage.logsOut();

        user.atHomePage.loginAsCustomer(customer.getEmail(), customer.getPassword());
        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerRequestPage.openRequest();
        user.atCustomerRequestPage.openAssignedMasters();
        masters.forEach(master -> user.atCustomerRequestPage.verifyMasterAssigned(master));

        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.deleteRequest();
        user.atCustomerProfileRequestsPage.verifyRequestsTableIsEmpty();
    }
}
