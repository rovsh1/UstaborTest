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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        addServiceQuestionsPage.selectService();

        addServiceQuestionsPage.addQuestion(question);
    }

    public void setPriceForCurrentCountry(String country, String minPrice, String maxPrice) {
        addServiceQuestionsPage.openPriceForm();
        addServiceQuestionsPage.setMinPrice(country, minPrice);
        addServiceQuestionsPage.setMaxPrice(country, maxPrice);
        addServiceQuestionsPage.clickSubmitPrice();
    }

    public void addSubQuestion(String question) {
        addServiceQuestionsPage.addSubQuestion(question);
    }
}
