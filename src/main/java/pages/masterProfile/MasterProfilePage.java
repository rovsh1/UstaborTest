package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterProfilePage extends MasterProfileBasePage {

    @FindBy(xpath = "//div[@class='presentation']")
    private WebElementFacade masterName;

    @FindBy(xpath = "//div[@class='p about']")
    private WebElementFacade aboutMeText;

    @FindBy(xpath = "//ul[@class='cities']")
    private WebElementFacade masterCity;

    @FindBy(xpath = "//ul[@class='categories']//li")
    private List<WebElementFacade> masterCategories;

    @FindBy(xpath = "//a[contains(@class, 'button-edit')]")
    private WebElementFacade profileSettingsBtn;

    @FindBy(xpath = "//div[@class=' user-profile']//div[contains(@class, 'rating-stars')]")
    private WebElementFacade masterRating;

    @FindBy(xpath = "//a[@href='#reviews']")
    private WebElementFacade masterReviews;

    @FindBy(xpath = "//table[@class='services']//td[@class='main']/div")
    private WebElementFacade service;

    @FindBy(xpath = "//table[@class='services']//input[@type='text']")
    private WebElementFacade servicePrice;

    public void masterFullNameShouldContain(String firstName) {
        masterName.shouldContainText(firstName);
    }

    public void aboutMeTextShouldBeEqual(String aboutMe) {
        aboutMeText.shouldContainText(aboutMe);
    }

    public void masterCityShouldBeEqual(String city) {
        masterCity.shouldContainText(city);
    }

    public void openProfileSettings() {
        profileSettingsBtn.click();
    }

    public void masterRatingShouldBe(String rating) {
        assertThat(masterRating.getAttribute("class")).contains(rating);
    }

    public void masterFeedbackShouldContain(String feedback) {
        masterReviews.shouldContainText(feedback);
    }

    public void selectService() throws InterruptedException {
        service.click();
        servicePrice.sendKeys("100");
        Thread.sleep(1000); //Wait for backend response
    }
}
