import entities.Master;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;
import utils.EmailUtilities;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class TC003 extends TestBase {

    private static EmailUtilities emailUtils;

    @BeforeClass
    public static void connectToEmail() {
        try {
            emailUtils = new EmailUtilities(
                    Config.getUsers().getNewMaster().getLogin(),
                    Config.getUsers().getNewMaster().getPassword());
            emailUtils.markAllAsReadAndDeleteEmails();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void masterAccountForgotPassword() throws Exception {
        Master master = data.getFullInfoMasterValidEmail(Config.getUsers().getNewMaster().getLogin());

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.clickForgotPassword();
        user.atHomePage.requestNewPasswordAtEmail(master.getLogin());
        String url = emailUtils.getResetPasswordUrl();
        user.atMasterProfileSettingsPage.open(url);
        String newPassword = data.getPassword();
        user.atMasterProfileSettingsPage.changePassword(newPassword);
        user.atMasterProfileSettingsPage.logsOut();

        assertThat(user.atHomePage.loginAsMaster(
                master.getLogin(), master.getPassword(), true))
                .isFalse();
        user.atHomePage.loginAsMaster(master.getLogin(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }
}
