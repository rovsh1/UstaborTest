package steps.adminSteps;

import entities.Category;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddRequestQuestionsPage;

public class AddRequestQuestionsPageSteps extends ScenarioSteps {

    private AddRequestQuestionsPage addRequestQuestionsPage;

    public void openPage() {
        addRequestQuestionsPage.openPage();
    }

    public void addQuestionToCategory(Category category, String question) {
        addRequestQuestionsPage.openQuestionsFormForCategory(category);
        addRequestQuestionsPage.addQuestion(question);
    }
}
