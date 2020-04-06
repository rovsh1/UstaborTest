import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import utils.Config;
import utils.EmailUtilities;

public class RegistrationTestBase extends TestBase {

    static EmailUtilities emailUtils;

    @BeforeClass
    public static void connectToEmail() {
        try {
            emailUtils = new EmailUtilities(
                    Config.getUsers().getNewCustomer().getLogin(),
                    Config.getUsers().getNewCustomer().getPassword());
            emailUtils.markAllAsReadAndDeleteEmails();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Before
    public void setUp() {
        super.setUp();
        boolean result = user.atHomePage.loginAsCustomer(
                Config.getUsers().getNewCustomer().getLogin(),
                Config.getUsers().getNewCustomer().getPassword());
        if (result) {
            user.atCustomerProfilePage.openCustomerProfilePage();
            user.atCustomerProfilePage.deleteProfile();
        }
        user.atHomePage.openHomePage();
    }

    @After
    public void tearDown() {
        user.atHomePage.openHomePage();
        user.atCustomerProfilePage.openCustomerProfilePage();
        user.atCustomerProfilePage.deleteProfile();
    }
}
