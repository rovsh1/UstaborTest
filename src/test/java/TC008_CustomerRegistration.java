import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

@RunWith(SerenityRunner.class)
public class TC008_CustomerRegistration extends RegistrationTestBase {

    @Test
    public void customerRegistration() throws Exception {
        user.atHomePage.registerAsCustomer(
                Config.getUsers().getNewCustomer().getLogin(), Config.getUsers().getNewCustomer().getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(emailUtils.getAuthorizationCode());

        user.atCustomerProfilePage.verifyCustomerProfilePageIsOpened();
    }

    @After
    public void tearDown() {
        user.atCustomerProfilePage.open();
        user.atCustomerProfilePage.deleteProfile();
    }
}
