import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true, addServiceQuestion = true)
@AddMasters(masters = 1, addProject = false)
@WithTag("new")
public class TC011_CustomerRequestWithdrawAmount extends TestBase {

    @Test
    public void verifyMasterClickPriceWithdraw() throws TimeoutException {
        var customer = DataGenerator.getGuestCustomer();
        watcher.users.add(customer);

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrder(customer, category);
        user.atCustomerProfileRequestsPage.openRequestsPage();

        customer.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());
        var requestId = user.atCustomerProfileRequestsPage.getRequestId();

        user.atHomePage.logsOut();

        admin.atRequestsPage.openRequestById(requestId);
        admin.atRequestsPage.assignRequestToMaster(watcher.getMaster());

        user.atHomePage.openHomePage();
        user.atHomePage.login(watcher.getMaster(), true);

        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequest();

        user.atMasterRequestPage.clickConnectClientButton();

        user.atMasterRequestPage.verifyCustomerInfo(customer);
    }
}
