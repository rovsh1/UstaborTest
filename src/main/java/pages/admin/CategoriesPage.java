package pages.admin;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.Config;

@DefaultUrl("http://s660022689.onlinehome.us/category/")
public class CategoriesPage extends PageObject {

    private static String categoryXpath = "//td[.//a[contains(@href, '%s')]]";
    private static String promotionXpath = "//div[@id='tab-countries']//tr[.//td[contains(text(), '%s')]]//a[@class='button-edit']";
    private static String countryXpath = "//div[@id='tab-countries']//tr[.//td[contains(text(), '%s')]]";

    @FindBy(xpath = "//li[@data-tab='tab-countries']")
    private WebElementFacade promotionTab;

    @FindBy(xpath = "//input[@id='form_data_enabled']")
    private WebElementFacade enablePromoCheckbox;

    @FindBy(xpath = "//input[@id='form_data_price_min']")
    private WebElementFacade minPrice;

    @FindBy(xpath = "//input[@id='form_data_price_max']")
    private WebElementFacade maxPrice;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitBtn;

    public void editCategoryById(String categoryId) {
        getDriver().findElement(By.xpath(String.format(categoryXpath, categoryId))).click();
    }

    public void openPromotionTab() {
        promotionTab.click();
    }

    public void enablePromotionAndSetPrice(String minimalPrice, String maximumPrice) {
        if (!enablePromoCheckbox.isSelected()) {
            enablePromoCheckbox.click();
        }

        minPrice.sendKeys(String.valueOf(minimalPrice));
        maxPrice.sendKeys(String.valueOf(maximumPrice));

        submitBtn.click();

    }

    public void openPromotionForCurrentCountry() {
        WebElement countryRow = getDriver().findElement(By.xpath(String.format(countryXpath, Config.getCountry())));

        Actions builder = new Actions(getDriver());
        builder.moveToElement(countryRow).build().perform();

        getDriver().findElement(By.xpath(String.format(promotionXpath, Config.getCountry()))).click();
    }
}
