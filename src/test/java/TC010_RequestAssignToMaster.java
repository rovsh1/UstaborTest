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
@AddMasters(1)
public class TC010_RequestAssignToMaster extends TestBase {

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
    }

    @Test
    public void verifyRequestAssignToMaster() {
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.placeOrder(customer, category);
        user.atPlaceOrderPage.openRequestsPage();

        var requestId = user.atCustomerProfileRequestsPage.getRequestId();

        admin.atRequestsPage.openRequestById(requestId);
        admin.atRequestsPage.verifyRequest(customer, category, getText("Question"));
        admin.atRequestsPage.addAssignRequestToMaster(masters.get(0));

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMasterIfNeed(masters.get(0).getEmail(), masters.get(0).getPassword());
        user.atMasterProfilePage.open();
        user.atMasterProfileRequestsPage.open();
        user.atMasterProfileRequestsPage.verifyRequestId(requestId);
    }
}
