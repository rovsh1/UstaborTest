package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterPromotionPage;

public class MasterPromotionPageSteps extends MasterProfileSteps {

    private MasterPromotionPage masterPromotionPage;

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

    @Step
    public void pageShouldBeVisible() {
        masterPromotionPage.categorySelectorShouldBeVisible();
        masterPromotionPage.sendToModerationBtnShouldBeVisible();
        masterPromotionPage.promotionManualBtnShouldBeVisible();
    }
}
