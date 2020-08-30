import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.Email;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC008_CustomerRegistration extends TestBase {

    protected Email email = new Email();

    @Test
    public void customerRegistration() throws TimeoutException {
        var customer = DataGenerator.getCustomer(email.getEmailAddress());
        watcher.users.add(customer);

        user.atHomePage.registerAsCustomer(customer.getEmail(), customer.getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());
        user.atCustomerProfilePersonalInfoPage.verifyCustomerProfilePageIsOpened();

        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
    }

    @After
    public void tearDown() {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.deleteProfile();
    }
}
