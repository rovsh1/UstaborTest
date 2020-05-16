import entities.CategoryCheckbox;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.text.SimpleDateFormat;
import java.util.Date;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC002 extends TestBase {

    @Test
    public void masterAccountLogin() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddhhmm");
        String userName = "Test " + dateFormat.format(new Date());

        user.atHomePage.openLoginFormAndVerify();
        user.atHomePage.selectIamMasterAndVerify();
        user.atHomePage.loginAsMaster(
                Config.getUsers().getDefaultMaster().getEmail(),
                Config.getUsers().getDefaultMaster().getPassword(),
                false);
        user.atHomePage.verifyUserIsLoggedIn();

        user.atMasterProfilePage.open();
        user.atMasterProfilePage.openProfileSettings();

        user.atMasterProfileSettingsPage.editUsername(userName);
        CategoryCheckbox category = user.atMasterProfileSettingsPage.enableOrDisableRandomCategory();
        user.atMasterProfileSettingsPage.saveChanges();

        user.atMasterProfilePage.masterFullNameShouldContain(userName);
        user.atMasterProfilePage.verifyCategoriesListValue(category);

        user.atMasterProfilePage.openProfileSettings();
        user.atMasterProfileSettingsPage.openChangePasswordForm();
        user.atMasterProfileSettingsPage.changePassword(Config.getUsers().getDefaultMaster().getPassword());

        user.atMasterProfileSettingsPage.logsOut();
        user.atHomePage.loginAsMaster(
                Config.getUsers().getDefaultMaster().getEmail(),
                Config.getUsers().getDefaultMaster().getPassword(),
                true);
        user.atHomePage.verifyUserIsLoggedIn();

        user.atMasterProfilePage.open();

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
