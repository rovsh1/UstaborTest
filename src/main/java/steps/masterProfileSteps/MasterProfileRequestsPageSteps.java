package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.masterProfile.MasterProfileRequestsPage;

public class MasterProfileRequestsPageSteps extends ScenarioSteps {

    private MasterProfileRequestsPage masterProfileRequestsPage;

    @Step
    public void open() {
        masterProfileRequestsPage.openPage();
    }

    @Step
    public void verifyRequestId(String requestId) {
        masterProfileRequestsPage.verifyRequestId(requestId);
    }
}
