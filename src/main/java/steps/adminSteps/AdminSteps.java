package steps.adminSteps;

import entities.Master;
import entities.Category;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

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

    @Steps
    public AddRequestQuestionsPageSteps atAddRequestQuestionsPage;

    @Steps
    public RequestsPageSteps atRequestsPage;

    @Steps
    public AddServicePageSteps atAddEditServicePage;

    public void addTestCategory(Category category) {
        atAdminHomePage.loginAsAdmin();
        atAddCategoryPage.addTestCategory(category);
        atCategoriesPage.getCategoryId(category);
    }

    public void addMoneyToMaster(int amount, Master master) {
        atAdminHomePage.loginAsAdmin();
        atMastersPage.addMoneyToMaster(amount, master.getProfileId());
    }

    public void approvePromotion(Category category) {
        atAdminHomePage.loginAsAdmin();
        atPromotionPage.approvePromotion(category);
    }

    public void addServiceRequestQuestions(Category category, String question) {
        atAddRequestQuestionsPage.openPage();
        atAddRequestQuestionsPage.addQuestionToCategory(category, question);
    }

    public void addCategoryService(Category category) {
        atAddEditServicePage.openPage();
        atAddEditServicePage.fillAddServiceForm(category);
        atAddEditServicePage.saveService();
    }

    public void setRequestPrices(Category category, String country, String minPrice, String maxPrice) {
        atAddEditServicePage.openEditPage(category.getName());
        atAddEditServicePage.setPriceForCurrentCountry(country, minPrice, maxPrice);
    }
}
