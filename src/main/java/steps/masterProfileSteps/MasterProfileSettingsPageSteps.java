package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterProfileSettingsPage;

public class MasterProfileSettingsPageSteps extends MasterProfileSteps {

    private MasterProfileSettingsPage masterProfileSettingsPage;

    @Step
    public void changePassword(String newPassword) {
        masterProfileSettingsPage.openPasswordFormIfNeeded();
        masterProfileSettingsPage.enterNewPassword(newPassword);
        masterProfileSettingsPage.confirmNewPassword(newPassword);
        masterProfileSettingsPage.clickSaveNewPasswordBtn();
        masterProfileSettingsPage.successPopupShouldBeVisible();
        masterProfileSettingsPage.clickCloseBtn();
    }

    @Step
    public void logsOut() {
        masterProfileSettingsPage.logsOut();
    }

    @Step
    public void editUsername(String username) {
        masterProfileSettingsPage.editUsername(username);
    }

    @Step
    public void saveChanges() {
        masterProfileSettingsPage.saveChanges();
    }

    @Step
    public void openChangePasswordForm() {
        masterProfileSettingsPage.openPasswordFormIfNeeded();
    }
}
