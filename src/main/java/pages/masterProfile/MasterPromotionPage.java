package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class MasterPromotionPage extends MasterProfileBasePage {

    public enum PromotionType {
        MinimalPrice,
        RecommendedPrice
    }

    @FindBy(xpath = "//select[@id='promotion-form_category_id']")
    private WebElementFacade categorySelector;

    @FindBy(xpath = "//label[@for='mp_pf_recommended']")
    private WebElementFacade recommendedPrice;

    @FindBy(xpath = "//label[@for='mp_pf_min']")
    private WebElementFacade minimumPrice;

    @FindBy(xpath = "//div[@class='profile-promotion']//button[@type='submit']")
    private WebElementFacade sendToModerationBtn;

    @FindBy(xpath = "//div[@class='button' and ./a[contains(@href, 'you')]]")
    private WebElementFacade promotionManualBtn;

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

    public void categorySelectorShouldBeVisible() {
        categorySelector.shouldBeVisible();
    }

    public void sendToModerationBtnShouldBeVisible() {
        sendToModerationBtn.shouldBeVisible();
    }

    public void promotionManualBtnShouldBeVisible() {
        promotionManualBtn.shouldBeVisible();
    }

    public void selectCategoryToPromote(String categoryName) {
        categorySelector.selectByVisibleText(categoryName);
    }

    public void selectPromotionType(PromotionType type) {
        switch (type) {
            case MinimalPrice:
                minimumPrice.click();
                break;
            case RecommendedPrice:
                recommendedPrice.click();
                break;
        }
    }

    public void sendToModeration() {
        sendToModerationBtn.click();
    }
}
