import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class TC008_CustomerRegistration extends RegistrationTestBase {

    @Test
    public void customerRegistration() throws TimeoutException {
        user.atHomePage.registerAsCustomer(
                email.getEmail(),
                Config.getUsers().getNewCustomer().getPassword()
        );

        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());

        user.atCustomerProfilePage.verifyCustomerProfilePageIsOpened();
    }

    @After
    public void tearDown() {
        user.atCustomerProfilePage.openCustomerProfilePage();
        user.atCustomerProfilePage.deleteProfile();
    }
}
