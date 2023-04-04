import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true, addServiceQuestion = true)
@AddMasters(masters = 1)
public class TC011_CustomerRequestPaymentForAssign extends TestBase {

    @Test
    public void verifyMasterClickPriceWithdraw() throws TimeoutException, InterruptedException {
        admin.addMoneyToMaster(10000, watcher.getMaster(), false);

        var customer = DataGenerator.getGuestCustomer();
        watcher.users.add(customer);

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrderForLoggedUser(customer, category);
        user.atCustomerProfileRequestsPage.openRequestsPage();

        customer.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());
        var requestId = user.atCustomerProfileRequestsPage.getRequestId();

        user.atHomePage.logsOut();

        admin.atRequestsPage.openRequestById(requestId);

        if (getTashkentHour() >= 9 && getTashkentHour() < 18) {
            admin.atRequestsPage.assignRequestToMasterForPayment(watcher.getMaster());
        } else {
            admin.atRequestsPage.reassignRequestToMasterForPayment(watcher.getMaster());
        }

        user.atHomePage.openHomePage();
        user.atHomePage.login(watcher.getMaster(), true);
        user.atHomePage.waitForLoaderDisappears();

        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.verifyBalance(9000);
        user.atMasterProfileRequestsPage.openRequest();

        user.atMasterRequestPage.clickConnectClientButton();

        user.atMasterRequestPage.verifyCustomerInfo(customer);
    }
}
