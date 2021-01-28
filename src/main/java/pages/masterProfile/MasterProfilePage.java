package pages.masterProfile;

import entities.CategoryCheckbox;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterProfilePage extends MasterProfileBasePage {

    @FindBy(xpath = "//div[@class='user-profile']//div[@class='name']")
    private WebElementFacade masterName;

    @FindBy(xpath = "//div[@class='p about']")
    private WebElementFacade aboutMeText;

    @FindBy(xpath = "//div[@class='user-profile']//ul[@class='cities']")
    private WebElementFacade masterCity;

    @FindBy(xpath = "//ul[@class='categories']//li")
    private List<WebElementFacade> masterCategories;

    @FindBy(xpath = "//a[contains(@class, 'button-edit')]")
    private WebElementFacade profileSettingsBtn;

    @FindBy(xpath = "//div[@class='user-profile']//div[contains(@class, 'rating-stars')]")
    private WebElementFacade masterRating;

    @FindBy(xpath = "//a[@href='#reviews']")
    private WebElementFacade masterReviews;

    public void masterFullNameShouldContain(String firstName) {
        masterName.shouldContainText(firstName);
    }

    public void aboutMeTextShouldBeEqual(String aboutMe) {
        aboutMeText.shouldContainOnlyText(aboutMe);
    }

    public void masterCityShouldBeEqual(String city) {
        masterCity.shouldContainText(city);
    }

    public void profilePageUrlShouldBeEqual(String profileUrl) {
        assertThat(getDriver().getCurrentUrl()).contains(profileUrl);
    }

    public void masterCategoriesShouldContain(String category) {
        var masterCategoriesNames = masterCategories
                .stream()
                .map(WebElementFacade::getText)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        assertThat(masterCategoriesNames.stream().anyMatch(c -> category.toLowerCase().contains(c))).isTrue();
    }

    public void openProfileSettings() {
        profileSettingsBtn.click();
    }

    public void verifyCategories(CategoryCheckbox category) {
        List<String> categories = masterCategories
                .stream()
                .map(WebElementFacade::getText)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        if (category.getEnabled()) {
            assertThat(categories.contains(category.getName().toLowerCase())).isTrue();
        } else {
            assertThat(categories.contains(category.getName().toLowerCase())).isFalse();
        }
    }

    public void waitForPageIsVisible() {
        withTimeoutOf(Duration.ofSeconds(120)).waitFor(profileSettingsBtn).isPresent();
    }

    public void masterRatingShouldBe(String rating) {
        assertThat(masterRating.getAttribute("class")).isEqualTo(rating);
    }

    public void masterFeedbackShouldContain(String feedback) {
        masterReviews.shouldContainText(feedback);
    }
}
