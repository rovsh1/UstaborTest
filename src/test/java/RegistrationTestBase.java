import org.junit.After;
import org.junit.Before;

public class RegistrationTestBase extends TestBase {

    @Before
    public void setUp() {
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
