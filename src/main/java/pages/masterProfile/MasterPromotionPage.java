package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterPromotionPage extends MasterProfileBasePage{

    @FindBy(xpath = "//div[@id='master-projects']/a")
    private List<WebElementFacade> projectsList;

    @FindBy(xpath = "//div[@class='promotion-select']//div[contains(@class, 'recommended')]")
    private WebElementFacade recommendedPromotionPrice;

    @FindBy(xpath = "//form[@id='promotion-form']//button[@type='submit']")
    private WebElementFacade submitPromotionBtn;

    @FindBy(xpath = "//div[@class='window window-message window-success' and .//div[text()='Promotion']]//button")
    private WebElementFacade successPopupOkButton;

    @FindBy(xpath = "//a[@id='button-add']")
    private WebElementFacade addProjectBtn;


    public void selectProject(String projectName) {
        WebElementFacade project = projectsList
                .stream()
                .filter(p -> p.getText().contains(projectName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("No project with such name: %s ", projectName)));

        project.click();
    }

    public void selectRecommendedOption() {
        recommendedPromotionPrice.click();
    }

    public void clickSubmitPromotionBtn() {
        submitPromotionBtn.click();
    }

    public void successPopupShouldBeVisible() {
        successPopupOkButton.shouldBeVisible();
    }

    public void clickSuccessPopupOkBtn() {
        successPopupOkButton.click();
    }

    public void addProjectBtnShouldBeVisible() {
        addProjectBtn.shouldBeVisible();
    }

    public void projectsListShouldBeVisible() {
        assertThat(projectsList.stream().allMatch(WebElementFacade::isVisible)).isTrue();
    }
}
