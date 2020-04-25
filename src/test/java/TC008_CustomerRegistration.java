import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;
import utils.mailApi.client.EmailSingleton;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
public class TC008_CustomerRegistration extends RegistrationTestBase {

    @Test
    public void customerRegistration() throws TimeoutException {
        user.atHomePage.registerAsCustomer(
                EmailSingleton.INSTANCE.getEmail(),
                Config.getUsers().getNewCustomer().getPassword()
        );

        user.atHomePage.enterAuthCodeAndSubmit(EmailSingleton.INSTANCE.getAuthCode());

        user.atCustomerProfilePage.verifyCustomerProfilePageIsOpened();
    }

    @After
    public void tearDown() {
        user.atCustomerProfilePage.openCustomerProfilePage();
        user.atCustomerProfilePage.deleteProfile();
    }
}
