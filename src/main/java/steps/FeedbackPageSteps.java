package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.FeedbackPage;

public class FeedbackPageSteps extends ScenarioSteps {

    private FeedbackPage feedbackPage;

    @Step
    public void leftFeedbackWithRandomGradeAndComment(String comment) {
        feedbackPage.selectRandomGrade();
        feedbackPage.enterFeedbackComment(comment);
        feedbackPage.submitFeedback();
    }
}
