import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC008_CustomerRegistration extends TestBase {

    private User customer;

    @Test
    public void customerRegistration() throws InterruptedException {
        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.atHomePage.registerAsCustomer(customer);

        var smsCode = user.getSmsCode(customer);

        user.atHomePage.enterAuthCodeAndSubmit(smsCode, customer.getPhoneNumber());
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();

        customer.setProfileId(user.atCustomerProfilePersonalInfoPage.getCustomerProfileId());
    }

    @After
    public void tearDown() throws TimeoutException {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.deleteProfile();
        customer.setProfileId(null);
    }
}
