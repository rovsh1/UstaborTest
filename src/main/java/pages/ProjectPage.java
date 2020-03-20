package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectPage extends BasePage {

    @FindBy(xpath = "//div[@class='master']//div[@class='h2']")
    private WebElementFacade masterName;

    @FindBy(xpath = "//ul[@class='categories']//a")
    private List<WebElementFacade> masterCategories;

    @FindBy(xpath = "//div[@id='button-favorite']")
    private WebElementFacade addToFavoriteBtn;

    @FindBy(xpath = "//div[@id='project-gallery']")
    private WebElementFacade projectImagesGallery;

    @FindBy(xpath = "//div[@class='about']")
    private WebElementFacade aboutMe;

    @FindBy(xpath = "//div[@class='text']")
    private WebElementFacade projectDescription;

    public void addProjectToFavorites() {
        addToFavoriteBtn.click();
    }

    public void projectImageShouldBeVisible() {
        projectImagesGallery.shouldBeVisible();
    }

    public void aboutMeShouldBeVisible() {
        aboutMe.shouldBeVisible();
    }

    public void masterNameShouldContain(String name) {
        masterName.shouldContainText(name);
    }

    public void masterCategoriesShouldContain(String category) {
        assertThat(masterCategories
                .stream()
                .map(WebElementFacade::getText)
                .map(String::toLowerCase)
                .collect(Collectors.toList()))
                .contains(category.toLowerCase());
    }

    public void projectUrlShouldContain(String projectUrl) {
        assertThat(getDriver().getCurrentUrl()).contains(projectUrl);
    }

    public void projectDescriptionShouldBe(String description) {
        projectDescription.shouldContainOnlyText(description);
    }
}
