import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true, promotionAndClickPrice = true)
@AddMasters(masters = 1, useAdminSite = true)
public class TC015_CustomerRequestClientContactWithdraw extends TestBase {

    @Test
    public void verifyWithdrawForCustomerContacts() throws TimeoutException, InterruptedException {
        admin.addMoneyToMaster(500, watcher.getMaster(), false);

        var customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrderForLoggedUser(customer, category);
        user.atCustomerProfileRequestsPage.openRequestsPage();

        var requestId = user.atCustomerProfileRequestsPage.getRequestId();

        if (getTashkentHour() >= 9 && getTashkentHour() < 18) {
            admin.atRequestsPage.openRequestById(requestId);
            admin.atRequestsPage.verifyRequest(customer, category, getText("Question_0"));
            admin.atRequestsPage.assignRequestToMasterForFree(watcher.getMaster());
        }

        user.atHomePage.openHomePage();
        user.atHomePage.logsOut();
        user.atHomePage.login(watcher.getMaster(), true);
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.verifyErrorMessage(getText("InsufficientFunds"));
    }
}
