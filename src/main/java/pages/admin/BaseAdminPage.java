package pages.admin;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class BaseAdminPage extends PageObject {

    @FindBy(xpath = "//button[@id='details-button']")
    private WebElementFacade chromeHideAdvancedBtn;

    @FindBy(xpath = "//a[@id='proceed-link']")
    private WebElementFacade chromeProceedLink;

    @FindBy(xpath = "//form[@class='quicksearch']/input[@id='quicksearch']")
    private WebElementFacade quickSearchInput;

    @FindBy(xpath = "//form[@class='quicksearch']/button[@type='submit']")
    private WebElementFacade quickSearchSubmit;

    public void quickSearch(String query) {
        quickSearchInput.sendKeys(query);
        quickSearchSubmit.click();
    }

    public void closeChromeWarningIfPresent() {
        chromeHideAdvancedBtn.click();
        chromeProceedLink.click();
    }
}
