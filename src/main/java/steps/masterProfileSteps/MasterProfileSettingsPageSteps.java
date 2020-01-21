package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterProfileSettingsPage;

public class MasterProfileSettingsPageSteps extends MasterProfileSteps {

    private MasterProfileSettingsPage masterProfileSettingsPage;

    @Step
    public void open(String url) {
        masterProfileSettingsPage.goToUrl(url);
    }

    @Step
    public void changePassword(String newPassword) {
        masterProfileSettingsPage.enterNewPassword(newPassword);
        masterProfileSettingsPage.confirmNewPassword(newPassword);
        masterProfileSettingsPage.clickSaveNewPasswordBtn();
        masterProfileSettingsPage.successPopupShouldBeVisible();
        masterProfileSettingsPage.closePopup();
    }

    @Step
    public void logsOut() {
        masterProfileSettingsPage.logsOut();
    }
}
