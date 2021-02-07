import entities.Category;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.Email;

import static org.assertj.core.api.Assertions.assertThat;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC003_MasterForgotPassword extends TestBase {

    @Test
    public void verifyMasterCanResetPassword() throws Exception {
        var email = new Email();
        var master = DataGenerator.getMasterWithEmail(email);
        watcher.users.add(master);

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(user.atMasterProfilePage.getProfileId());

        //user.atMasterProfilePage.open(email.getUrl(Email.EmailType.ConfirmRegister));

        user.atHomePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.clickForgotPassword();
        user.atHomePage.requestNewPasswordAtEmail(master.getEmail());

        user.atMasterProfileSettingsPage.open(email.getUrl(Email.EmailType.ForgotPassword));

        var newPassword = DataGenerator.getPassword();

        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfileSettingsPage.openChangePasswordForm();
        user.atMasterProfileSettingsPage.changePassword(newPassword);
        user.atMasterProfileSettingsPage.logsOut();

        assertThat(user.atHomePage.login(master, true))
                .isFalse();

        user.atHomePage.login(master.getEmail(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }
}
