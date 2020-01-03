package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MasterPromotionPage extends BasePage{

    @FindBy(xpath = "//a[@href='/profile/promotion/']")
    private WebElementFacade promotionTab;

    @FindBy(xpath = "//div[@id='master-projects']/a")
    private List<WebElementFacade> projectsList;

    @FindBy(xpath = "//div[@class='promotion-select']//div[contains(@class, 'recommended')]")
    private WebElementFacade recommendedPromotionPrice;

    @FindBy(xpath = "//form[@id='promotion-form']//button[@type='submit']")
    private WebElementFacade submitPromotionBtn;

    @FindBy(xpath = "//div[@class='window window-message window-success' and .//div[text()='Promotion']]//button")
    private WebElementFacade successPopupOkButton;


    public void selectProject(String projectName) {
        WebElementFacade project = projectsList
                .stream()
                .filter(p -> p.getText().contains(projectName))
                .findFirst().orElse(null);

        if (project == null) {
            throw new NullPointerException(String.format("No project with such name: %s ", projectName));
        }

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

    public void clickPromotionTab() {
        scrollPageUpJS();
        promotionTab.click();
    }
}
