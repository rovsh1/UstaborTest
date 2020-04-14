package pages.admin;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.time.temporal.ChronoUnit;

public class BaseAdminPage extends PageObject {

    @FindBy(xpath = "//form[@class='quicksearch']/input[@id='quicksearch']")
    private WebElementFacade quickSearchInput;

    @FindBy(xpath = "//form[@class='quicksearch']/button[@type='submit']")
    private WebElementFacade quickSearchSubmit;

    private String loaderXpath = "//div[contains(@class, 'loading')]";

    public void quickSearch(String query) {
        quickSearchInput.sendKeys(query);
        quickSearchSubmit.click();
    }

    public void waitForLoaderDisappears() {
        if (Config.isChrome()) {
            withTimeoutOf(25, ChronoUnit.SECONDS)
                    .waitForCondition()
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loaderXpath)));
        } else {
            new WebDriverWait(getDriver(), 10)
                    .until(d -> d.findElements(By.xpath(loaderXpath)).size() == 0);
        }
    }
}
