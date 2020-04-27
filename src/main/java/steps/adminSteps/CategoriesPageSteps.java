package steps.adminSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.CategoriesPage;

import java.util.concurrent.TimeoutException;

public class CategoriesPageSteps extends ScenarioSteps {

    private CategoriesPage categoriesPage;

    @Step
    public void openEditCategoryPage(String categoryId) throws TimeoutException {
        categoriesPage.openEditCategoryPage(categoryId);
    }

    @Step
    public void enablePromotionAndSetPrice(String categoryId, String minPrice, String maxPrice) throws TimeoutException {
        openEditCategoryPage(categoryId);
        categoriesPage.openPromotionForCurrentCountry();
        categoriesPage.enablePromotionAndSetPrice(minPrice, maxPrice);
        categoriesPage.waitForLoaderDisappears();
    }
}
