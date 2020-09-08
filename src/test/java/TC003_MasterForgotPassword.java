import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Ignore;
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
        var master = DataGenerator.getMasterValidEmail(email.getEmailAddress());
        watcher.users.add(master);

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(user.atMasterProfilePage.getProfileId());

        user.atMasterProfilePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.clickForgotPassword();
        user.atHomePage.requestNewPasswordAtEmail(master.getEmail());

        user.atMasterProfileSettingsPage.open(email.getForgotPasswordUrl());

        String newPassword = DataGenerator.getPassword();

        user.atMasterProfileSettingsPage.changePassword(newPassword);
        user.atMasterProfileSettingsPage.logsOut();

        assertThat(user.atHomePage.loginAsMaster(
                master.getEmail(), master.getPassword(), true))
                .isFalse();

        user.atHomePage.loginAsMaster(master.getEmail(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }
}
