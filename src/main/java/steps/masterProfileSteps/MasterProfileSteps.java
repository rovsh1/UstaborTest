package steps.masterProfileSteps;

import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.masterProfile.MasterProfileBasePage;

class MasterProfileSteps extends ScenarioSteps {

    private MasterProfileBasePage masterProfileBasePage;

    @Step
    public void openProjectsTab() {
        masterProfileBasePage.openProjectsTab();
    }

    @Step
    public void openWalletTab() {
        masterProfileBasePage.openWalletTab();
    }

    @Step
    public void openPromotionTab() {
        masterProfileBasePage.openPromotionTab();
    }

    @Step
    public void openNotificationTab() {
        masterProfileBasePage.openNotificationsTab();
    }

    @Step
    public void openFaqTab() {
        masterProfileBasePage.openFaqTab();
    }

    public String getProfileId() {
        return masterProfileBasePage.getProfileId();
    }

    @Step
    public void verifyBalance(int amount) {
        masterProfileBasePage.verifyBalanceAmount(amount);
    }
}
