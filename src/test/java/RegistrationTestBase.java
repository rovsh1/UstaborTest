import org.junit.After;
import org.junit.Before;
import utils.Email;

import java.util.concurrent.TimeoutException;

public class RegistrationTestBase extends ProdTestBase {

    protected Email email;

    @Before
    public void setUp() throws TimeoutException {
        super.setUp();
        email = new Email();
        user.atHomePage.openHomePage();
    }

    @After
    public void tearDown() {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePage.openCustomerProfilePage();
        user.atCustomerProfilePage.deleteProfile();
    }
}
