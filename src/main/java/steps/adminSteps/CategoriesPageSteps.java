package steps.adminSteps;

import entities.TestCategory;
import entities.User;
import net.thucydides.core.annotations.Step;
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
    public void openEditCategoryPage(String categoryId) throws TimeoutException {
        categoriesPage.openEditCategoryPage(categoryId);
    }

    @Step
    public void openViewCategoryPage(String categoryId) {
        categoriesPage.openViewCategoryPage(categoryId);
    }

    @Step
    public void enablePromotionAndSetPrice(String categoryId, String minPrice, String maxPrice) throws TimeoutException {
        openEditCategoryPage(categoryId);
        categoriesPage.openPromotionForCurrentCountry();
        categoriesPage.enablePromotion();
        categoriesPage.setPrice(minPrice, maxPrice);
        categoriesPage.submitPromotion();
        categoriesPage.waitForLoaderDisappears();
    }

    public void getCategoryIdByName(TestCategory category) {
        categoriesPage.openPage();
        category.setSystemId(categoriesPage.getCategoryIdByName(category.getName()));
        logger.info("Test category with id '{}' was created", category.getSystemId());
    }

    @Step
    public void setRequestClickPrice(String categoryId, String clickPrice) throws TimeoutException {
        openEditCategoryPage(categoryId);
        categoriesPage.openPromotionForCurrentCountry();
        categoriesPage.enablePromotion();
        categoriesPage.setClickPrice(clickPrice);
        categoriesPage.submitPromotion();
        categoriesPage.waitForLoaderDisappears();
    }

    @Step
    public void addMastersToCategoryRequest(TestCategory category, List<User> masters) {
        openViewCategoryPage(category.getSystemId());
        masters.forEach(m -> {
            categoriesPage.openAddMasterForm();
            categoriesPage.addMasterToCategoryRequest(m.getProfileId());
            categoriesPage.submitMasterAssign();
            categoriesPage.waitForLoaderDisappears();
        });
    }

    @Step
    public void setPromotionAndClickPrice(String categoryId, String minPrice, String maxPrice, String clickPrice) throws TimeoutException {
        openEditCategoryPage(categoryId);
        categoriesPage.openPromotionForCurrentCountry();
        categoriesPage.enablePromotion();
        categoriesPage.setPrice(minPrice, maxPrice);
        categoriesPage.setClickPrice(clickPrice);
        categoriesPage.submitPromotion();
        categoriesPage.waitForLoaderDisappears();
    }
}
