import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

@RunWith(SerenityRunner.class)
@AddCategory(requestClickPrice = 1000)
@AddMasters(masters = 1, addProject = false)
public class TC011_CustomerRequestWithdrawAmount extends TestBase {

    @Test
    public void verifyMasterClickPriceWithdraw() {
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
        user.atHomePage.loginAsMaster(
                watcher.getMaster().getEmail(),
                watcher.getMaster().getPassword(),
                true);

        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterProfileRequestsPage.verifyClickPriceFirstPage("1000");
        user.atMasterProfileRequestsPage.verifyClickPriceSecondPage("1000");
        user.atMasterProfileRequestsPage.verifyClickPriceLastPage("1000");

        user.atMasterProfileRequestsPage.clickConnectClientButton();
        user.atMasterProfileRequestsPage.verifyErrorMessage(getText("WithdrawError"));

        admin.addMoneyToMaster(1000, watcher.getMaster());

        user.atHomePage.openHomePage();
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterProfileRequestsPage.clickConnectClientButton();

        user.atMasterProfileRequestsPage.verifyCustomerInfo(customer);

        user.atMasterProfilePage.open();
        user.atMasterProfilePage.verifyBalance(900);
    }
}
