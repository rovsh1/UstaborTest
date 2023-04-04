package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterPromotionPage;
import utils.Config;

public class MasterPromotionPageSteps extends MasterProfileSteps {

    private MasterPromotionPage masterPromotionPage;

    @Step
    public void pageShouldBeVisible() {
        masterPromotionPage.categorySelectorShouldBeVisible();
        masterPromotionPage.sendToModerationBtnShouldBeVisible();
    }

    @Step
    public void promoteCategory(String categoryName, MasterPromotionPage.PromotionType promotionType) {
        masterPromotionPage.selectCategoryToPromote(categoryName);
        masterPromotionPage.selectPromotionType(promotionType);
        masterPromotionPage.sendToModeration();
    }
}
