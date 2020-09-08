package steps.adminSteps;

import entities.TestCategory;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddRequestQuestionsPage;
import utils.Config;

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
        if (Config.isUstabor()) {
            addRequestQuestionsPage.setQuestionPriceUstabor(minPrice, maxPrice);
        } else {
            addRequestQuestionsPage.setQuestionPriceForCountry(countryName, minPrice, maxPrice);
        }

    }
}
