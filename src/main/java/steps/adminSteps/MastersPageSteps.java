package steps.adminSteps;

import entities.Master;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.MastersPage;

import java.util.List;

public class MastersPageSteps extends ScenarioSteps {

    private MastersPage mastersPage;

    @Step
    public void addMoneyToMaster(int amount, String masterId) {
        mastersPage.openMasterPageByDirectUrl(masterId);
        mastersPage.addMoneyToAccount(amount);
    }

    @Step
    public void addAllBadgesToMaster(Master master) {
        mastersPage.openEditMasterPageByDirectUrl(master.getProfileId());
        mastersPage.addAllBadgesToMaster(master);
    }

    public void performSearch(String text) {
        mastersPage.performSearch(text);
    }

    public List<String> getMastersIds() {
        return mastersPage.getMastersIds();
    }

}
