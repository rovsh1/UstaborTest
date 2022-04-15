import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true, addServiceQuestion = true)
@AddMasters(addProject = false)
@WithTag("new")
public class TC013_CustomerRequestAutoAmountWithdraw extends TestBase {

    @Test
    public void verifyAutoWithdrawForRequest() throws TimeoutException {
        var customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.atHomePage.registerAsCustomer(customer);
        var smsCode = new Admin().getSmsCode(customer.getPhoneNumber());
        user.atHomePage.enterAuthCodeAndSubmit(smsCode);

        var password = new Admin().getSmsPassword(customer.getPhoneNumber());
        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
        customer.setPassword(password);

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
        masters.forEach(m -> admin.atMastersPage.addMoneyToMaster(20000, m.getProfileId()));
        admin.atCategoriesPage.addMastersToCategoryRequest(category, masters);
        admin.atRequestsPage.openRequestById(requestId);
        admin.atRequestsPage.assignRequestToAllMastersOfCategory();

        user.atHomePage.openHomePage();
        user.atHomePage.login(masters.get(0), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyBalance(19000);
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
        user.atMasterProfilePage.verifyBalance(19000);
        user.atHomePage.logsOut();

        user.atHomePage.login(customer, true);
        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerRequestPage.openRequest();
        masters.forEach(master -> user.atCustomerRequestPage.verifyMasterAssigned(master));

        user.atCustomerProfileRequestsPage.openRequestsPage();
        user.atCustomerProfileRequestsPage.deleteRequest();
        user.atCustomerProfileRequestsPage.verifyRequestsTableIsEmpty();
    }
}
