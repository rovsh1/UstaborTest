import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;
import utils.DataGenerator;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC004_MasterLoginFailure extends TestBase {

    @Test
    public void verifyMasterCantLoginWithWrongPassword() {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);
        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.waitForPageIsVisible();
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();
        master.setProfileId(user.atMasterProfilePage.getProfileId());
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.login(
                master.getLogin(),
                "thisIsWrongPassword",
                false);
        user.atHomePage.loginErrorWithTextShouldBeVisible(getText("LoginFailedError"));
    }
}
