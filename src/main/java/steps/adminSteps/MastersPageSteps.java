package steps.adminSteps;

import entities.Master;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.MastersPage;

public class MastersPageSteps extends ScenarioSteps {

    private MastersPage mastersPage;

    @Step
    public void addMoneyToMaster(int amount, String masterId) {
        mastersPage.openMasterPageByDirectUrl(masterId);
        mastersPage.addMoneyToAccount(amount);
    }

    @Step
    public void addAllBadgesToMaster(Master master) {
        mastersPage.openMasterPageByDirectUrl(master.getProfileId());
        mastersPage.addAllBadgesToMaster();
    }
}
