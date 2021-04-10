package pages.admin;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.WaitHelper;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeoutException;

public class BaseAdminPage extends PageObject {

    private final String loaderXpath = "//div[contains(@class, 'loading')]";

    @FindBy(xpath = "//form[@id='grid-filter-form']//input[@id='quicksearch']")
    private WebElementFacade quickSearchInput;

    @FindBy(xpath = loaderXpath)
    private WebElementFacade loader;

    public void waitForLoaderDisappears(int timeoutMilliseconds) {
        setTimeouts(1, ChronoUnit.SECONDS);
        try {
            WaitHelper.pollingWait(timeoutMilliseconds, 500, () -> !loader.isVisible());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        resetTimeouts();
    }

    public void waitForLoaderDisappears() {
        waitForLoaderDisappears(60000);
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

    public void scrollIntoView(WebElementFacade element) {
        evaluateJavascript("arguments[0].scrollIntoView(true);", element);
    }
}
