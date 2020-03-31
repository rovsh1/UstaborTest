package steps.adminSteps;

import entities.Master;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.MastersPage;

public class MastersPageSteps extends ScenarioSteps {

    private MastersPage mastersPage;

    @Step
    public void addMoneyToMaster(int amount, String masterLastName) {
        mastersPage.openPage();
        mastersPage.openMasterProfileByName(masterLastName);
        mastersPage.addMoneyToAccount(amount);
    }

    @Step
    public void addAllBadgesToMaster(Master master) {
        mastersPage.openPage();
        mastersPage.openMasterProfileByName(master.getLastName());
        mastersPage.openEditMasterPage();
        mastersPage.addAllBadgesToMaster(master);
    }

    public void deleteMaster(Master master) {
        mastersPage.deleteMaster(master.getProfileId());
    }
}
