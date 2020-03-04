package steps.adminSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.MastersPage;

public class MastersPageSteps extends ScenarioSteps {

    private MastersPage mastersPage;

    @Step
    public void addMoneyToMaster(int amount, String masterLastName) {
        mastersPage.open();
        mastersPage.openMasterProfileByName(masterLastName);
        mastersPage.addMoneyToAccount(amount);
    }

    @Step
    public void addAllBadgesToMaster(String lastName) {
        mastersPage.open();
        mastersPage.openMasterProfileByName(lastName);
        mastersPage.openEditMasterPage();
        mastersPage.addAllBadgesToMaster();
    }
}
