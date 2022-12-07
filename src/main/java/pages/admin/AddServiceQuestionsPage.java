package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class AddServiceQuestionsPage extends BaseAdminPage {

    private String countryMinPriceXpath = "//tr[./th[text()='%s']]//input[contains(@name, 'price_min')]";
    private String countryMaxPriceXpath = "//tr[./th[text()='%s']]//input[contains(@name, 'price_max')]";

    @FindBy(xpath = "//select[@id='category_id']")
    private WebElementFacade selectCategory;

    @FindBy(xpath = "//select[@id='service_id']")
    private WebElementFacade selectService;

    @FindBy(xpath = "//textarea")
    private WebElementFacade questionTextArea;

    @FindBy(xpath = "//button[@class='submit']")
    private WebElementFacade submitQuestionBtn;

    @FindBy(xpath = "//div[@class='prices']")
    private WebElementFacade addPriceBtn;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade submitPriceBtn;


    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "request/answer");
    }

    public void addQuestion(String question) {
        questionTextArea.sendKeys(question);
        submitQuestionBtn.click();
    }

    public void selectCategory(String id) {
        selectCategory.selectByValue(id);
    }

    public void selectService() {
        selectService.selectByIndex(1);
    }

    public void openPriceForm() {
        addPriceBtn.click();
    }

    public void setMinPrice(String country, String minPrice) {
        var input = getDriver().findElement(By.xpath(String.format(countryMinPriceXpath, country)));
        input.clear();
        input.sendKeys(minPrice);
    }

    public void setMaxPrice(String country, String maxPrice) {
        var input = getDriver().findElement(By.xpath(String.format(countryMaxPriceXpath, country)));
        input.clear();
        input.sendKeys(maxPrice);
    }

    public void clickSubmitPrice() {
        submitPriceBtn.click();
    }
}
