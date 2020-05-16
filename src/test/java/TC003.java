import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Email;

import static org.assertj.core.api.Assertions.assertThat;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC003 extends TestBase {

    @Test
    public void masterAccountForgotPassword() throws Exception {
        var email = new Email();

        var master = data.getMasterValidEmail(email.getEmailAddress());
        watcher.masters.add(master);

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();
        master.setProfileId(user.atMasterProfilePage.getProfileId());
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.clickForgotPassword();
        user.atHomePage.requestNewPasswordAtEmail(master.getEmail());

        var url = email.getForgotPasswordUrl();
        user.atMasterProfileSettingsPage.open(url);
        String newPassword = data.getPassword();
        user.atMasterProfileSettingsPage.changePassword(newPassword);
        user.atMasterProfileSettingsPage.logsOut();

        assertThat(user.atHomePage.loginAsMaster(
                master.getEmail(), master.getPassword(), true))
                .isFalse();
        user.atHomePage.loginAsMaster(master.getEmail(), newPassword, false);
        user.atHomePage.verifyUserIsLoggedIn();
    }
}
