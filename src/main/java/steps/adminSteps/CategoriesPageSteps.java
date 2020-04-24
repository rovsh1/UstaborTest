package steps.adminSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.CategoriesPage;

public class CategoriesPageSteps extends ScenarioSteps {

    private CategoriesPage categoriesPage;

    @Step
    public void enablePromotionAndSetPrice(String categoryId, String minPrice, String maxPrice) {
        categoriesPage.openEditCategoryPage(categoryId);
        categoriesPage.openPromotionForCurrentCountry();
        categoriesPage.enablePromotionAndSetPrice(minPrice, maxPrice);
        categoriesPage.waitForLoaderDisappears();
    }
}
