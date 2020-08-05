package steps.adminSteps;

import entities.Project;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.PromotionPage;

public class PromotionPageSteps extends ScenarioSteps {

    private PromotionPage promotionPage;

    @Step
    public void approveProject(Project project) {
        promotionPage.openPage();
        project.setPromoId(promotionPage.getProjectPromoId(project.getSystemId()));
        promotionPage.approvePromotionByProjectSystemId(project.getSystemId());
    }
}
