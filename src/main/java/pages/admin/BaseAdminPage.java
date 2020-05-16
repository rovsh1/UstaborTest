package pages.admin;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import utils.WaitHelper;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeoutException;

public class BaseAdminPage extends PageObject {

    private final String loaderXpath = "//div[contains(@class, 'loading')]";

    @FindBy(xpath = "//form[@class='quicksearch']/input[@id='quicksearch']")
    private WebElementFacade quickSearchInput;

    @FindBy(xpath = "//form[@class='quicksearch']/button[@type='submit']")
    private WebElementFacade quickSearchSubmit;

    @FindBy(xpath = loaderXpath)
    private WebElementFacade loader;

    public void quickSearch(String query) {
        quickSearchInput.sendKeys(query);
        quickSearchSubmit.click();
    }

    public void waitForLoaderDisappears() {
        setTimeouts(1, ChronoUnit.SECONDS);
        try {
            WaitHelper.pollingWait(60000, 500, () -> !loader.isVisible());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        resetTimeouts();
    }

    public void setTimeouts(int duration, TemporalUnit timeUnit) {
        setImplicitTimeout(duration, timeUnit);
        setWaitForTimeout(duration * 1000);
    }

    public void resetTimeouts() {
        resetImplicitTimeout();
        setWaitForTimeout(15000);
    }

    public void waitForLogin() {
        withTimeoutOf(Duration.ofSeconds(25)).waitFor(quickSearchInput).isPresent();
    }
}
