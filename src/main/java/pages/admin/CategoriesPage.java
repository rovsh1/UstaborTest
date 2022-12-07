package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.regex.Pattern;

public class CategoriesPage extends BaseAdminPage {

    private static final String categoryUrlByNameXpath = "//tr[./td[text()='%s']]//a";

    @FindBy(xpath = "//input[@id='quicksearch']")
    private WebElementFacade categoriesSearch;

    @FindBy(xpath = "//form[@class='quicksearch']//button[@type='submit']")
    private WebElementFacade searchBtn;


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


    //region New admin
    @FindBy(xpath = "//input[@id='form_data_request_price']")
    private WebElementFacade requestClickPrice;

    @FindBy(xpath = "//input[@id='form_data_price_min']")
    private WebElementFacade minPrice;

    @FindBy(xpath = "//input[@id='form_data_price_max']")
    private WebElementFacade maxPrice;

    @FindBy(xpath = "//select[@id='form_data_category_id']")
    private WebElementFacade selectCategory;

    @FindBy(xpath = "//select[@id='form_data_country_id']")
    private WebElementFacade selectCountry;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade submitBtn;
    //endregion


    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "/reference/category");
    }

    public void openViewCategoryPage(String categoryId) {
        getDriver().get(Config.getAdminUrl() + String.format("catalog/category/view/%s/", categoryId));
    }

    public void setPrice(String minimalPrice, String maximumPrice) {
        minPrice.sendKeys(minimalPrice);
        maxPrice.sendKeys(maximumPrice);
    }

    public void setClickPrice(String clickPrice) {
        requestClickPrice.sendKeys(clickPrice);
    }

    public void submitPromotion() {
        submitBtn.click();
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

    public void findCategory(String name) {
        categoriesSearch.sendKeys(name);
        searchBtn.click();
    }

    public void openPromotionPage() {
        getDriver().get(Config.getAdminUrl() + "reference/category-country/create");
    }

    public void selectCategory(String categoryId) {
        selectCategory.selectByValue(categoryId);
    }

    public void selectCurrentCountry() {
        selectCountry.selectByVisibleText(Config.getCountry());
    }
}
