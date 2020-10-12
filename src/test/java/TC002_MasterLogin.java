import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.text.SimpleDateFormat;
import java.util.Date;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC002_MasterLogin extends TestBase {

    @Test
    public void verifyMasterAccountLogin() {
        var userName = "Test " + new SimpleDateFormat("MMddhhmm").format(new Date());

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.selectIamMasterAndVerify();
        user.atHomePage.loginAsMaster(Config.getUsers().getDefaultMaster(), false);
        user.atHomePage.verifyUserIsLoggedIn();

        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.openProfileSettings();

        user.atMasterProfileSettingsPage.editUsername(userName);
        var category = user.atMasterProfileSettingsPage.enableOrDisableRandomCategory();
        user.atMasterProfileSettingsPage.saveChanges();

        user.atMasterProfilePage.masterFullNameShouldContain(userName);
        user.atMasterProfilePage.verifyCategoriesListValue(category);

        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfileSettingsPage.openChangePasswordForm();
        user.atMasterProfileSettingsPage.changePassword(Config.getUsers().getDefaultMaster().getPassword());

        user.atMasterProfileSettingsPage.logsOut();
        user.atHomePage.loginAsMaster(Config.getUsers().getDefaultMaster(), true);
        user.atHomePage.verifyUserIsLoggedIn();

        user.atMasterProfilePage.openProfilePage();

        user.atMasterProfilePage.openProjectsTab();
        user.atMasterProjectsPage.pageShouldBeVisible();

        user.atMasterProjectsPage.openWalletTab();
        user.atMasterWalletPage.pageShouldBeVisible();

        user.atMasterWalletPage.openNotificationTab();
        user.atMasterNotificationsPage.pageShouldBeVisible();

        user.atMasterNotificationsPage.openPromotionTab();
        user.atMasterPromotionPage.pageShouldBeVisible();

        user.atMasterPromotionPage.openFaqTab();
        user.atMasterFaqPage.pageShouldBeVisible();
    }
}
