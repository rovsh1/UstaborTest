import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC004_MasterLoginFailure extends TestBase {

    @Test
    public void verifyMasterCantLoginWithWrongPassword() {
        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.selectIamMasterAndVerify();
        user.atHomePage.loginAsMaster(
                Config.getUsers().getDefaultMaster().getEmail(),
                "thisIsWrongPassword",
                false);
        user.atHomePage.loginErrorWithTextShouldBeVisible(getText("LoginFailedError"));
    }
}
