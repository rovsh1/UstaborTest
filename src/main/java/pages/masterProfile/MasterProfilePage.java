package pages.masterProfile;

import entities.CategoryCheckbox;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterProfilePage extends MasterProfileBasePage {

    @FindBy(xpath = "//div[@class='user-profile']//div[@class='h2']/text()")
    private WebElementFacade masterName;

    @FindBy(xpath = "//div[@class='p about']")
    private WebElementFacade aboutMeText;

    @FindBy(xpath = "//div[@class='user-profile']//ul[@class='cities']")
    private WebElementFacade masterCity;

    @FindBy(xpath = "//ul[@class='categories']//a")
    private List<WebElementFacade> masterCategories;

    @FindBy(xpath = "//a[contains(@class, 'button-edit')]")
    private WebElementFacade profileSettingsBtn;

    public void projectsTabShouldBeVisible() {
        projectsTab.shouldBeVisible();
    }

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
        assertThat(masterCategories
                .stream()
                .map(WebElementFacade::getText)
                .map(String::toLowerCase)
                .collect(Collectors.toList()))
                .contains(category.toLowerCase());
    }

    public void openProfileSettings() {
        profileSettingsBtn.click();
    }

    public void masterFullNameShouldBeEquals(String userName) {
        masterName.shouldContainOnlyText(userName);
    }

    public void verifyCategories(CategoryCheckbox category) {
        List<String> categories = masterCategories
                .stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());

        if (category.getEnabled()) {
            assertThat(categories.contains(category.getName())).isTrue();
        } else {
            assertThat(categories.contains(category.getName())).isFalse();
        }
    }
}
