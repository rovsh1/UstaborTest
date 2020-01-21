import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

@WithTags({
        @WithTag("test"),
        @WithTag("full")
})
@RunWith(SerenityRunner.class)
public class TC002 extends TestBase {

    @Test
    public void masterAccountLogin() {

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.selectIamMasterAndVerify();
        user.atHomePage.loginAsMaster(
                Config.getUsers().getDefaultMaster().getLogin(),
                Config.getUsers().getDefaultMaster().getPassword(),
                false);
        user.atHomePage.verifyUserIsLoggedIn();

//        user.atMasterProfilePage.openProjectsTab();
//        user.atMasterProjectsPage.pageShouldBeVisible();
//
//        user.atMasterProjectsPage.openWalletTab();
//        user.atMasterWalletPage.pageShouldBeVisible();
//
//        user.atMasterWalletPage.openNotificationTab();
//        user.atMasterNotificationsPage.pageShouldBeVisible();
//
//        user.atMasterNotificationsPage.openPromotionTab();
//        user.atMasterPromotionPage.pageShouldBeVisible();
//
//        user.atMasterPromotionPage.openFaqTab();
//        user.atMasterFaqPage.pageShouldBeVisible();
    }
}
