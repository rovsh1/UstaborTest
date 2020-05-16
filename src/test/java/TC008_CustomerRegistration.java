import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class TC008_CustomerRegistration extends RegistrationTestBase {

    @Test
    public void customerRegistration() throws TimeoutException {
        var customer = data.getCustomer(email.getEmailAddress());

        user.atHomePage.registerAsCustomer(customer.getEmail(), customer.getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());
        user.atCustomerProfilePage.verifyCustomerProfilePageIsOpened();
    }
}
