package steps.adminSteps;

import entities.Master;
import entities.Category;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddMasterPage;

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

    @Steps
    public CronTasksPageSteps atCronTasksPage;

    @Steps
    public AddMasterPage atAddMasterPage;
    @Steps
    public ServicePricePageSteps atServicePricesPage;

    public void addTestCategory(Category category) {
        atAdminHomePage.loginAsAdmin();
        atAddCategoryPage.addTestCategory(category);
        atCategoriesPage.getCategoryId(category);
    }

    public void addMoneyToMaster(int amount, Master master, boolean isLoginNeeded) {
        if (isLoginNeeded) {
            atAdminHomePage.loginAsAdmin();
        }

        atMastersPage.addMoneyToMaster(amount, master.getProfileId());
    }

    public void approvePromotion(Category category) {
        atAdminHomePage.loginAsAdmin();
        atPromotionPage.approvePromotion(category);
    }

    public void addServiceQuestions(Category category, String question) {
        atAddRequestQuestionsPage.openPage();
        atAddRequestQuestionsPage.addQuestionToCategory(category, question);
    }

    public void addCategoryService(Category category, boolean price) {
        atAddEditServicePage.openPage();
        atAddEditServicePage.fillAddServiceForm(category);
        if (price) {
            atAddEditServicePage.setPrices();
        }
        atAddEditServicePage.saveService();
    }

    public void setServicePrices(String country, String minPrice, String maxPrice) {
        atAddRequestQuestionsPage.setPriceForCurrentCountry(country, minPrice, maxPrice);
    }

    @Step
    public void waitForCronTaskCompleted(String taskId, int timeout) {
        atCronTasksPage.waitForCronTaskCompleted(taskId, timeout);
    }

    public void addSubQuestion(String question) {
        atAddRequestQuestionsPage.addSubQuestion(question);
    }

    public void addMaster(Master master) {
        atAddMasterPage.openPage();
        atAddMasterPage.createMaster(master);
        atAddMasterPage.openMasterPage(master);
        atAddMasterPage.setCategoryToMaster(master);
    }
}
