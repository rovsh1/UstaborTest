import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SerenityRunner.class)
@AddCategory(addRequestQuestion = true)
@AddMasters(masters = 1, addProject = false)
@WithTag("new")
public class TC012_CustomerRequestStatuses extends TestBase {

    @Test
    public void verifyCustomerRequestStatuses() {
        var customer = DataGenerator.getGuestCustomer();
        watcher.users.add(customer);

        user.atHomePage.openHomePage();
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrder(customer, category);
        user.atPlaceOrderPage.openRequestsPage();

        customer.setPassword(new Admin().getSmsPassword(customer.getPhoneNumber()));
        var requestId = user.atCustomerProfileRequestsPage.getRequestId();
        customer.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());

        user.atHomePage.logsOut();

        admin.atRequestsPage.openRequestById(requestId);
        admin.atRequestsPage.addAssignRequestToMaster(watcher.getMaster());

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMaster(watcher.getMaster().getEmail(), watcher.getMaster().getPassword(), true);
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyRequestStatus(getText("RequestNew"));
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyRequestStatus(getText("RequestWatched"));
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterRequestPage.clickConnectClientButton();
        user.atMasterRequestPage.closeConnectCustomerPopup();
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyRequestStatus(getText("RequestPaid"));
        user.atMasterProfileRequestsPage.openRequest();
        user.atMasterRequestPage.clickConnectClientButton();

        user.atMasterRequestPage.sendMessageToCustomer(getText("SmsTestMessage"));
        var msg = new Admin().getSmsByText(customer.getPhoneNumber(), getText("SmsMasterMessage"));
        assertThat(msg).isNotNull();

        user.atMasterRequestPage.makeOfferToCustomer("1000");
        var offer = new Admin().getSmsByText(customer.getPhoneNumber(), getText("SmsMasterPrice"));
        assertThat(offer).isNotNull();

        user.atMasterRequestPage.closeConnectCustomerPopup();
        user.atHomePage.logsOut();

        user.atHomePage.loginAsCustomer(customer.getPhoneNumber(), customer.getPassword());
        user.atCustomerProfileRequestsPage.open();
        user.atCustomerRequestPage.openRequest();
        user.atCustomerRequestPage.openAssignedMasters();
        user.atCustomerRequestPage.verifyMasterSmsText(getText("SmsTestMessage"));
        user.atCustomerRequestPage.verifyMasterOffer("1000");
        user.atCustomerRequestPage.hideMasterOffer();
        user.atCustomerRequestPage.verifyOfferIsHidden();
        user.atHomePage.logsOut();

        user.atHomePage.loginAsMaster(watcher.getMaster().getEmail(), watcher.getMaster().getPassword(), true);
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyRequestStatus(getText("RequestDeclined"));
        user.atHomePage.logsOut();

        user.atHomePage.loginAsCustomer(customer.getPhoneNumber(), customer.getPassword());
        user.atCustomerProfileRequestsPage.open();
        user.atCustomerProfileRequestsPage.hideRequest();
        var decline = new Admin().getSmsByText(watcher.getMaster().getPhoneNumber(), getText("SmsRequestClosed"));
        assertThat(decline).isNotNull();
        user.atCustomerProfileRequestsPage.verifyRequestStatus(getText("RequestClosed"));
        user.atHomePage.logsOut();

        user.atHomePage.loginAsMaster(watcher.getMaster().getEmail(), watcher.getMaster().getPassword(), true);
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyRequestStatusSetByCustomer(getText("RequestClosed"));
        user.atHomePage.logsOut();

        user.atHomePage.loginAsCustomer(customer.getPhoneNumber(), customer.getPassword());
        user.atCustomerProfileRequestsPage.open();
        user.atCustomerProfileRequestsPage.deleteRequest();
        user.atHomePage.logsOut();

        user.atHomePage.loginAsMaster(watcher.getMaster().getEmail(), watcher.getMaster().getPassword(), true);
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyRequestsTableIsEmpty();
    }

}
