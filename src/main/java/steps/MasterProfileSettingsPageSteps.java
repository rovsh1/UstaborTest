package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.MasterProfileSettingsPage;

public class MasterProfileSettingsPageSteps extends ScenarioSteps {

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
