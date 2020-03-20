package steps.adminSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.PromotionPage;

public class PromotionPageSteps extends ScenarioSteps {

    private PromotionPage promotionPage;

    @Step
    public void approveProject(String systemId) {
        promotionPage.open();
        promotionPage.approvePromotionByProjectSystemId(systemId);
    }
}
