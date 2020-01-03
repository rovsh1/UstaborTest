package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.MasterPromotionPage;

public class MasterPromotionPageSteps extends ScenarioSteps {

    private MasterPromotionPage masterPromotionPage;

    @Step
    public void openPromotionTab() {
        masterPromotionPage.clickPromotionTab();
    }

    @Step
    public void selectProjectByName(String projectName) {
        masterPromotionPage.selectProject(projectName);
    }

    @Step
    public void selectRecommendedOption() {
        masterPromotionPage.selectRecommendedOption();
    }

    @Step
    public void submitPromotion() {
        masterPromotionPage.clickSubmitPromotionBtn();
    }

    @Step
    public void promoteProjectWithRecommendedPrice(String projectName) {
        masterPromotionPage.selectProject(projectName);
        masterPromotionPage.selectRecommendedOption();
        masterPromotionPage.clickSubmitPromotionBtn();
        masterPromotionPage.successPopupShouldBeVisible();
        masterPromotionPage.clickSuccessPopupOkBtn();
    }
}
