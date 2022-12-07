package steps.adminSteps;

import entities.Category;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddServiceQuestionsPage;

public class AddRequestQuestionsPageSteps extends ScenarioSteps {

    private AddServiceQuestionsPage addServiceQuestionsPage;

    public void openPage() {
        addServiceQuestionsPage.openPage();
    }

    public void addQuestionToCategory(Category category, String question) {
        addServiceQuestionsPage.selectCategory(category.getSystemId());
        addServiceQuestionsPage.selectService();
        addServiceQuestionsPage.addQuestion(question);
    }

    public void setPriceForCurrentCountry(String country, String minPrice, String maxPrice) {
        addServiceQuestionsPage.openPriceForm();
        addServiceQuestionsPage.setMinPrice(country, minPrice);
        addServiceQuestionsPage.setMaxPrice(country, maxPrice);
        addServiceQuestionsPage.clickSubmitPrice();
    }
}
