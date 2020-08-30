package steps.adminSteps;

import entities.TestCategory;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddRequestQuestionsPage;

public class AddRequestQuestionsPageSteps extends ScenarioSteps {

    private AddRequestQuestionsPage addRequestQuestionsPage;

    public void openPage() {
        addRequestQuestionsPage.openPage();
    }

    public void addQuestionToCategory(TestCategory category, String question) {
        addRequestQuestionsPage.openQuestionsFormForCategory(category);
        addRequestQuestionsPage.addQuestion(question);
    }

    public void setQuestionPriceForCurrentCountry(String question, String countryName, String minPrice, String maxPrice) {
        addRequestQuestionsPage.openPricesTableForQuestion(question);
        addRequestQuestionsPage.setQuestionPriceForCountry(countryName, minPrice, maxPrice);
    }
}
