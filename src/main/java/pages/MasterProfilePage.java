package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterProfilePage extends BasePage {

    @FindBy(xpath = "//a[@href='/profile/projects/']")
    private WebElementFacade projectsTab;

    @FindBy(xpath = "//div[@class='user-profile']//div[@class='h2']")
    private WebElementFacade profileFirstName;

    @FindBy(xpath = "//div[@class='p about']")
    private WebElementFacade aboutMeText;

    @FindBy(xpath = "//div[@class='user-profile']//ul[@class='cities']")
    private WebElementFacade masterCity;

    public void projectsTabShouldBeVisible() {
        projectsTab.shouldBeVisible();
    }

    public void masterFullNameShouldContain(String firstName) {
        assertThat(profileFirstName.getText()).contains(firstName);
    }

    public void aboutMeTextShould(String aboutMe) {
        aboutMeText.shouldContainOnlyText(aboutMe);
    }

    public void masterCityShouldBe(String city) {
        masterCity.shouldContainText(city);
    }
}
