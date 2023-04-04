import annotations.AddCategory;
import annotations.AddMasters;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;
import utils.XmlParser;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true)
@AddMasters(masters = 1, useAdminSite = true)
public class TC010_CustomerRequestAssignForFree extends TestBase {

    protected User customer;

    @Before
    public void setUp() throws TimeoutException, InterruptedException {
        super.setUp();
        admin.addMoneyToMaster(10000, watcher.getMaster(), false);

        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);
        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();

        user.atHomePage.registerAsCustomer(customer);
        var smsCode = Admin.getInstance().getSmsCode(customer.getPhoneNumber());
        user.atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
    }

    @Test
    public void verifyRequestAssignToMaster() throws TimeoutException, InterruptedException {
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
        user.atMasterProfileRequestsPage.verifyRequestId(requestId);
        user.atMasterProfileRequestsPage.verifyBalance(10000);
    }
}
