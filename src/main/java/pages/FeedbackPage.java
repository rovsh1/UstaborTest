package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FeedbackPage extends BasePage {

    @FindBy(xpath = "//div[@class='master']//div[@class='rating-stars active']/div")
    private List<WebElementFacade> ratingStars;
    
    @FindBy(xpath = "//div[@class='master']//textarea")
    private WebElementFacade masterFeedbackInput;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElementFacade submitFeedbackBtn;

    public void setRating(int rating) {
        if (rating != 0) {
            ratingStars.get(rating - 1).click();
        }
    }

    public void enterFeedbackComment(String comment) {
        masterFeedbackInput.sendKeys(comment);
    }

    public void submitFeedback() {
        submitFeedbackBtn.click();
    }
}
