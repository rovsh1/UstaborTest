import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true, addRequestQuestion = true)
@AddMasters(masters = 1, addProject = false)
@WithTag("new")
public class TC011_CustomerRequestWithdrawAmount extends TestBase {

    @Test
    public void verifyMasterClickPriceWithdraw() throws TimeoutException {
        var customer = DataGenerator.getGuestCustomer();
        watcher.users.add(customer);

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrder(customer, category);
        user.atPlaceOrderPage.openRequestsPage();

        customer.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());
        var requestId = user.atCustomerProfileRequestsPage.getRequestId();

        user.atHomePage.logsOut();

        admin.atRequestsPage.openRequestById(requestId);
        admin.atRequestsPage.addAssignRequestToMaster(watcher.getMaster());

        admin.addMoneyToMaster(900, watcher.getMaster());

        user.atHomePage.openHomePage();
        user.atHomePage.login(watcher.getMaster(), true);

        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterRequestPage.verifyClickPriceFirstPage("1000");
        user.atMasterRequestPage.verifyClickPriceSecondPage("1000");
        user.atMasterRequestPage.verifyClickPriceLastPage("1000");

        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.verifyErrorMessage(getText("WithdrawError"));

        admin.addMoneyToMaster(1000, watcher.getMaster());

        user.atHomePage.openHomePage();
        user.atMasterProfileRequestsPage.openRequestsPage();
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterRequestPage.clickConnectClientButton();

        user.atMasterRequestPage.verifyCustomerInfo(customer);

        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.verifyBalance(900);
    }
}
