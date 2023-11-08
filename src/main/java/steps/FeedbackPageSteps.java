package steps;

import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.FeedbackPage;

public class FeedbackPageSteps extends ScenarioSteps {

    private FeedbackPage feedbackPage;

    @Step
    public void leftFeedback(int rating, String comment) {
        feedbackPage.setRating(rating);
        feedbackPage.enterFeedbackComment(comment);
        feedbackPage.submitFeedback();
    }
}
