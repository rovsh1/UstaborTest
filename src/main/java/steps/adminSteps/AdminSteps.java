package steps.adminSteps;

import entities.Master;
import entities.Project;
import entities.TestCategory;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.concurrent.TimeoutException;


public class AdminSteps extends ScenarioSteps {

    @Steps
    public HomePageSteps atAdminHomePage;

    @Steps
    public MastersPageSteps atMastersPage;

    @Steps
    public CategoriesPageSteps atCategoriesPage;

    @Steps
    public PromotionPageSteps atPromotionPage;

    @Steps
    public AddCategoryPageSteps atAddCategoryPage;

    public void addTestCategory(TestCategory category) {
        atAdminHomePage.loginAsAdmin();
        atAddCategoryPage.addTestCategory(category);
        atCategoriesPage.getCategoryIdByName(category);
    }

    public void addMoneyToMaster(int amount, Master master) {
        atAdminHomePage.loginAsAdmin();
        atMastersPage.addMoneyToMaster(amount, master.getProfileId());
    }

    public void approveProject(Project project) {
        atAdminHomePage.loginAsAdmin();
        atPromotionPage.approveProject(project);
    }

    public void enablePromotion(Master master) throws TimeoutException {
        atAdminHomePage.loginAsAdmin();
        atCategoriesPage.enablePromotionAndSetPrice(master.getCategoryId(), "100", "500");
    }
}
