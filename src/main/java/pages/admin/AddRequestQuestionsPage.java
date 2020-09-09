package pages.admin;

import entities.TestCategory;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.List;

public class AddRequestQuestionsPage extends BaseAdminPage {

    private final String questionXpath = "//span[text()='%s']";
    private final String minPriceXpath = "//tr[./th[text()='%s']]//input[contains(@name, 'price_min')]";
    private final String maxPriceXpath = "//tr[./th[text()='%s']]//input[contains(@name, 'price_max')]";

    @FindBy(xpath = "//nav[@id='main-categories']//div[contains(@class, 'empty')]")
    private List<WebElementFacade> categories;

    @FindBy(xpath = "//textarea")
    private WebElementFacade questionTextArea;

    @FindBy(xpath = "//button[@class='submit']")
    private WebElementFacade submitQuestionBtn;

    @FindBy(xpath = "//div[@class='prices']")
    private WebElementFacade pricesBtn;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitPriceBtn;

    @FindBy(xpath = "//input[contains(@name, 'price_min')]")
    private WebElementFacade minPriceUstabor;

    @FindBy(xpath = "//input[contains(@name, 'price_max')]")
    private WebElementFacade maxPriceUstabor;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "requestanswer/");
    }

    public void openQuestionsFormForCategory(TestCategory category) {
        var expectedCategory = categories
                .stream()
                .filter(c -> c.getText().contains(category.getName()))
                .findFirst()
                .orElse(null);

        if (expectedCategory == null) {
            throw new NullPointerException(String.format("No such category found: %s", category.getName()));
        }

        expectedCategory.click();
    }

    public void addQuestion(String question) {
        questionTextArea.sendKeys(question);
        submitQuestionBtn.click();
    }

    public void openPricesTableForQuestion(String question) {
        var builder = new Actions(getDriver());
        builder.moveToElement(getDriver().findElement(By.xpath(String.format(questionXpath, question))))
                .moveToElement(pricesBtn)
                .click()
                .build()
                .perform();
    }

    public void setQuestionPriceForCountry(String countryName, String minPrice, String maxPrice) {
        getDriver().findElement(By.xpath(String.format(minPriceXpath, countryName))).sendKeys(minPrice);
        getDriver().findElement(By.xpath(String.format(maxPriceXpath, countryName))).sendKeys(maxPrice);
        submitPriceBtn.click();
    }


    public void setQuestionPriceUstabor(String minPrice, String maxPrice) {
        minPriceUstabor.sendKeys(minPrice);
        maxPriceUstabor.sendKeys(maxPrice);
        submitPriceBtn.click();
    }
}

