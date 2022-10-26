import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

@Ignore
@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC004_MasterLoginFailure extends TestBase {

    @Test
    @Ignore
    public void verifyMasterCantLoginWithWrongPassword() {
        var master = DataGenerator.getMaster();
        watcher.users.add(master);

        user.register(master, true);
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.login(
                master.getLogin(),
                "thisIsWrongPassword",
                false);
        user.atHomePage.loginErrorWithTextShouldBeVisible(getText("LoginFailedError"));
    }
}
