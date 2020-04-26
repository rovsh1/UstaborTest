import org.junit.After;
import org.junit.Before;
import utils.Email;

public class RegistrationTestBase extends TestBase {

    protected Email email;

    @Before
    public void setUp() {
        email = new Email();
        super.setUp();
        user.atHomePage.openHomePage();
    }

    @After
    public void tearDown() {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePage.openCustomerProfilePage();
        user.atCustomerProfilePage.deleteProfile();
    }
}
