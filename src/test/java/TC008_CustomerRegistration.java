import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Email;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC008_CustomerRegistration extends TestBase {

    protected Email email;

    @Before
    public void setUp() throws TimeoutException {
        super.setUp();
        email = new Email();
    }

    @Test
    public void customerRegistration() throws TimeoutException {
        var customer = data.getCustomer(email.getEmailAddress());

        user.atHomePage.registerAsCustomer(customer.getEmail(), customer.getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());
        user.atCustomerProfilePage.verifyCustomerProfilePageIsOpened();
    }

    @After
    public void tearDown() {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePage.openCustomerProfilePage();
        user.atCustomerProfilePage.deleteProfile();
    }
}
