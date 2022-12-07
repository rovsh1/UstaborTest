package pages.customerProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerProfileRequestsPage extends CustomerProfileBasePage {

    @FindBy(xpath = "//div[contains(@class, 'requests-list')]/div")
    private List<WebElementFacade> requests;

    @FindBy(xpath = "//span[@class='id']")
    private WebElementFacade requestId;

    @FindBy(xpath = "//div[@class='btn-menu menu-wrap']")
    private WebElementFacade additionalOptionsBtn;

    @FindBy(xpath = "//a[@class='delete']")
    private WebElementFacade deleteBtn;

    @FindBy(xpath = "//a[contains(@href,'close')]")
    private WebElementFacade hideRequest;

    @FindBy(xpath = "//span[contains(@class, 'ui-request-status')]")
    private WebElementFacade requestStatus;

    public void requestShouldBeVisible() {
        assertThat(requests).isNotEmpty();
    }

    public String getRequestId() {
        return requestId.getText().replaceAll("[^0-9.]", "");
    }

    public void openPage() {
        getDriver().get(Config.getFullUrl() + "customer/requests");
    }

    public void deleteRequest() {
        evaluateJavascript("document.getElementsByClassName('delete')[0].click();");
    }

    public void verifyRequestsTabIsEmpty() {
        assertThat(requests).isEmpty();
    }

    public void clickHideRequest() {
        evaluateJavascript("document.getElementsByClassName('more')[0].click();");
    }

    public void verifyRequestStatus(String status) {
        requestStatus.shouldContainText(status);
    }

    public void closePopup() {
        clickCloseBtn();
    }
}
