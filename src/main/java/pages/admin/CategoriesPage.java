package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.Config;
import utils.WaitHelper;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

public class CategoriesPage extends BaseAdminPage {

    private static final String categoryXpath = "//td[.//a[contains(@href, '%s')]]";
    private static final String promotionXpath = "//div[@id='tab-countries']//tr[.//td[contains(text(), '%s')]]//a";
    private static final String countryXpath = "//div[@id='tab-countries']//tr[.//td[contains(text(), '%s')]]";
    private static final String countriesAndPromotionXpath = "//li[@data-tab='tab-countries' and @class='current']";
    private static final String categoryUrlByNameXpath = "//tr[./td[text()='%s']]//a";

    @FindBy(xpath = "//input[@id='quicksearch']")
    private WebElementFacade categoriesSearch;

    @FindBy(xpath = "//form[@class='quicksearch']//button[@type='submit']")
    private WebElementFacade searchBtn;

    //region Edit category page
    @FindBy(xpath = "//li[@data-tab='tab-countries']")
    private WebElementFacade promotionTab;

    @FindBy(xpath = "//input[@id='form_data_enabled']")
    private WebElementFacade enablePromoCheckbox;

    @FindBy(xpath = "//input[@id='form_data_price_min']")
    private WebElementFacade minPrice;

    @FindBy(xpath = "//input[@id='form_data_price_max']")
    private WebElementFacade maxPrice;

    @FindBy(xpath = "//input[@id='form_data_request_price']")
    private WebElementFacade requestClickPrice;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitBtn;

    @FindBy(xpath = "//td[.//a[@class='button-edit']]")
    private WebElementFacade editBtn;

    @FindBy(xpath = "//li[@data-tab='tab-countries' and @class='current']")
    private WebElementFacade countriesAndPromotionTabSelected;

    @FindBy(xpath = "//li[@data-tab='tab-countries']")
    private WebElementFacade countriesAndPromotionTab;
    //endregion

    //region View category page
    @FindBy(xpath = "//a[@id='btn-master-add']")
    private WebElementFacade addMasterBtn;

    @FindBy(xpath = "//input[@class='ui-autocomplete-input']")
    private WebElementFacade masterInput;

    @FindBy(xpath = "//ul[contains(@class, 'ui-autocomplete')]/li")
    private WebElementFacade autocompleteSuggestion;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitMasterAssignBtn;
    //endregion

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "/reference/category");
    }

    public void openEditCategoryPage(String categoryId) throws TimeoutException {
        getDriver().get(Config.getAdminUrl() + String.format("catalog/category/edit/%s/#!tab-countries", categoryId));

        if (!Config.isChrome()) {
            setTimeouts(1, ChronoUnit.SECONDS);
            WaitHelper.pollingWait(60000, 1000, () -> {
                if (!countriesAndPromotionTabSelected.isPresent()) {
                    countriesAndPromotionTab.click();
                }
                return countriesAndPromotionTabSelected.isPresent();
            });
            resetTimeouts();
        }
    }

    public void openViewCategoryPage(String categoryId) {
        getDriver().get(Config.getAdminUrl() + String.format("catalog/category/view/%s/", categoryId));
    }

    public void setPrice(String minimalPrice, String maximumPrice) {
        minPrice.clear();
        minPrice.sendKeys(String.valueOf(minimalPrice));
        maxPrice.clear();
        maxPrice.sendKeys(String.valueOf(maximumPrice));
    }

    public void setClickPrice(String clickPrice) {
        requestClickPrice.sendKeys(clickPrice);
    }

    public void submitPromotion() {
        submitBtn.click();
    }

    public void openPromotionForCurrentCountry() {
        WebElement countryRow = getDriver().findElement(By.xpath(String.format(countryXpath, Config.getCountry())));

        Actions builder = new Actions(getDriver());
        builder.moveToElement(countryRow).build().perform();

        getDriver().findElement(By.xpath(String.format(promotionXpath, Config.getCountry()))).click();
    }

    public String getCategoryIdByName(String categoryName) {
        var url = getDriver()
                .findElement(By.xpath(String.format(categoryUrlByNameXpath, categoryName)))
                .getAttribute("href");

        var matcher = Pattern.compile("(?<=/)\\d{3,}(?=/)").matcher(url);
        if (matcher.find()) {
            return matcher.group(0);
        }

        return null;
    }

    public void openAddMasterForm() {
        addMasterBtn.click();
    }

    public void addMasterToCategoryRequest(String profileId) {
        masterInput.sendKeys(profileId);
        autocompleteSuggestion.waitUntilPresent();
        autocompleteSuggestion.click();
    }

    public void submitMasterAssign() {
        submitMasterAssignBtn.click();
    }

    public void enablePromotion() {
        if (!enablePromoCheckbox.isSelected()) {
            enablePromoCheckbox.click();
        }
    }

    public void findCategory(String name) {
        categoriesSearch.sendKeys(name);
        searchBtn.click();
    }
}
