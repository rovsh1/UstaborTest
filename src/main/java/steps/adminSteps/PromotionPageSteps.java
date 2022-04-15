package steps.adminSteps;

import entities.Category;
import entities.Project;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.PromotionPage;

public class PromotionPageSteps extends ScenarioSteps {

    private PromotionPage promotionPage;

    @Step
    public void approvePromotion(Category category) {
        promotionPage.openPage();
        var promoId = promotionPage.getCategoryPromoId(category.getName());
        category.setPromoId(promoId);
        promotionPage.approvePromotionByCategoryName(category.getName());
    }
}
