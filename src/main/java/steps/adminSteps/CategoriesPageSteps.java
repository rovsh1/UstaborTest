package steps.adminSteps;

import entities.Category;
import entities.User;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.admin.CategoriesPage;
import utils.Admin;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class CategoriesPageSteps extends ScenarioSteps {

    private static final Logger logger = LoggerFactory.getLogger(Admin.class);

    private CategoriesPage categoriesPage;

    @Step
    public void openViewCategoryPage(String categoryId) {
        categoriesPage.openViewCategoryPage(categoryId);
    }

    public void getCategoryId(Category category) {
        categoriesPage.findCategory(category.getName());
        var id = categoriesPage.getCategoryIdByName(category.getName());

        category.setSystemId(id);
        logger.info("Test category with id '{}' has been created", id);
    }

    @Step
    public void addMastersToCategoryRequest(Category category, List<User> masters) {
        openViewCategoryPage(category.getSystemId());
        masters.forEach(m -> {
            categoriesPage.openAddMasterForm();
            categoriesPage.addMasterToCategoryRequest(m.getLastName());
            categoriesPage.waitForLoaderDisappears();
        });
    }

    @Step
    public void setPromotionAndClickPrice(String categoryId, String minPrice, String maxPrice, String clickPrice) throws TimeoutException {
        categoriesPage.openPromotionPage();
        categoriesPage.selectCategory(categoryId);
        categoriesPage.selectCurrentCountry();
        categoriesPage.setPrice(minPrice, maxPrice);
        categoriesPage.setClickPrice(clickPrice);
        categoriesPage.submitPromotion();
//        categoriesPage.waitForLoaderDisappears();
    }
}
