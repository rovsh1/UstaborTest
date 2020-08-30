import annotations.AddCategory;
import annotations.AddMasters;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.Email;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory
@AddMasters(masters = 1, addProject = false)
public class TC010_CustomerRequestAssignToMaster extends TestBase {

    protected Email email;
    protected User customer;

    @Before
    public void setUp() throws TimeoutException {
        super.setUp();
        email = new Email();
        customer = DataGenerator.getCustomer(email.getEmailAddress());
        watcher.users.add(customer);

        user.atHomePage.openHomePage();

        user.atHomePage.registerAsCustomer(customer.getEmail(), customer.getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
    }

    @Test
    public void verifyRequestAssignToMaster() {
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrder(customer, category);
        user.atPlaceOrderPage.openRequestsPage();

        var requestId = user.atCustomerProfileRequestsPage.getRequestId();

        admin.atRequestsPage.openRequestById(requestId);
        admin.atRequestsPage.verifyRequest(customer, category, getText("Question"));
        admin.atRequestsPage.addAssignRequestToMaster(watcher.getMaster());

        user.atHomePage.openHomePage();
        user.atHomePage.logsOut();
        user.atHomePage.loginAsMaster(
                watcher.getMaster().getEmail(),
                watcher.getMaster().getPassword(),
                true);
        user.atMasterProfilePage.open();
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyRequestId(requestId);
    }
}
