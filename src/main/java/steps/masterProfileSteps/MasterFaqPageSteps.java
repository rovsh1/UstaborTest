package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterFaqPage;

public class MasterFaqPageSteps extends MasterProfileSteps{

    private MasterFaqPage masterFaqPage;

    @Step
    public void pageShouldBeVisible() {
        masterFaqPage.verifyFaqItems();
    }
}
