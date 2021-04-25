package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class AddServicePage extends BaseAdminPage {

    private final String minPriceXpath = "//tr[./th[text()='%s']]//input[contains(@name, 'price_min')]";
    private final String maxPriceXpath = "//tr[./th[text()='%s']]//input[contains(@name, 'price_max')]";

    @FindBy(xpath = "//input[@id='form_data_name']")
    private WebElementFacade serviceName;

    @FindBy(xpath = "//select[@id='form_data_category_id']")
    private WebElementFacade serviceCategory;

    @FindBy(xpath = "//input[@id='form_data_question']")
    private WebElementFacade serviceHeader;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElementFacade submit;

    @FindBy(xpath = "//tr[./td[text()='AutotestCategory']]")
    private WebElementFacade serviceRow;

    @FindBy(xpath = "//tr[./td[text()='AutotestCategory']]//a")
    private WebElementFacade editService;

    @FindBy(xpath = "//li[@data-tab='tab-prices']")
    private WebElementFacade pricesTab;

    @FindBy(xpath = "//a[@id='btn-price-add']")
    private WebElementFacade addPriceButton;

    @FindBy(xpath = "//select[@id='form_data_country_id']")
    private WebElementFacade countrySelector;

    @FindBy(xpath = "//input[@id='form_data_price_contact']")
    private WebElementFacade contractPrice;

    @FindBy(xpath = "//input[@id='form_data_price_min']")
    private WebElementFacade minimalPrice;

    @FindBy(xpath = "//input[@id='form_data_price_max']")
    private WebElementFacade maximumPrice;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitPriceBtn;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "customer/service/edit/new/");
    }

    public void enterName(String name) {
        serviceName.sendKeys(name);
    }

    public void selectCategory(String categoryName) {
        serviceCategory.selectByVisibleText(categoryName);
    }

    public void enterHeader(String header) {
        serviceHeader.sendKeys(header);
    }

    public void saveService() {
        submit.click();
    }

    public void openEditPage(String categoryName) {
        var builder = new Actions(getDriver());
        builder.moveToElement(serviceRow)
                .click()
                .build()
                .perform();
        editService.click();
    }

    public void openPricesPopup() {
        addPriceButton.click();
    }

    public void setQuestionPriceForCountry(String countryName, String minPrice, String maxPrice) {
        countrySelector.selectByVisibleText(countryName);
        contractPrice.sendKeys(minPrice);
        minimalPrice.sendKeys(minPrice);
        maximumPrice.sendKeys(maxPrice);
        submitPriceBtn.click();
    }

    public void clickPricesTab() {
        pricesTab.click();
    }
}
