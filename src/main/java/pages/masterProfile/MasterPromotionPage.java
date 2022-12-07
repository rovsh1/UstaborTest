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

    @FindBy(xpath = "//select[@id='form_data_category_id']")
    private WebElementFacade categorySelector;

    @FindBy(xpath = "//div[@class='profile-promotion']//button[@type='submit']")
    private WebElementFacade sendToModerationBtn;

    @FindBy(xpath = "//div[@class='button' and ./a[contains(@href, 'you')]]")
    private WebElementFacade promotionManualBtn;

    @FindBy(xpath = "//label[@for='mp_pf_recommended']")
    private WebElementFacade recommendedPrice;

    @FindBy(xpath = "//label[@for='mp_pf_min']")
    private WebElementFacade minimumPrice;


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
