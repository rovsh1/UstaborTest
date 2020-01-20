package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterProfilePage extends BasePage {

    @FindBy(xpath = "//a[@href='/profile/projects/']")
    private WebElementFacade projectsTab;

    @FindBy(xpath = "//div[@class='user-profile']//div[@class='h2']")
    private WebElementFacade masterName;

    @FindBy(xpath = "//div[@class='p about']")
    private WebElementFacade aboutMeText;

    @FindBy(xpath = "//div[@class='user-profile']//ul[@class='cities']")
    private WebElementFacade masterCity;

    @FindBy(xpath = "//ul[@class='categories']//a")
    private List<WebElementFacade> masterCategories;

    public void projectsTabShouldBeVisible() {
        projectsTab.shouldBeVisible();
    }

    public void masterFullNameShouldContain(String firstName) {
        masterName.shouldContainText(firstName);
    }

    public void aboutMeTextShould(String aboutMe) {
        aboutMeText.shouldContainOnlyText(aboutMe);
    }

    public void masterCityShouldBe(String city) {
        masterCity.shouldContainText(city);
    }

    public void profilePageUrlShouldBe(String profileUrl) {
        assertThat(getDriver().getCurrentUrl()).contains(profileUrl);
    }

    public void masterCategoriesShouldContain(String category) {
        assertThat(masterCategories
                .stream()
                .map(WebElementFacade::getText)
                .map(String::toLowerCase)
                .collect(Collectors.toList()))
                .contains(category.toLowerCase());
    }
}
