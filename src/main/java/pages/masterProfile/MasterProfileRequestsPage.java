package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.Config;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MasterProfileRequestsPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, '/profile/requests')]")
    private WebElementFacade requestsTab;

    @FindBy(xpath = "//div[contains(@class, 'requests-list')]//div[contains(@class, 'item')]")
    private List<WebElementFacade> requests;

    @FindBy(xpath = "//span[@class='id']")
    private WebElementFacade requestIdForm;

    @FindBy(xpath = "//a[contains(@href, '/request/')]")
    private WebElementFacade requestDetailsBtn;

    @FindBy(xpath = "//div[@class='btn-contact']")
    private WebElementFacade phoneButton;

    @FindBy(xpath = "//span[contains(@class, 'CUSTOMER_REQUEST_MASTER_STATUS')]")
    private WebElementFacade requestStatus;

    @FindBy(xpath = "//span[contains(@class, 'CUSTOMER_REQUEST_STATUS')]")
    private WebElementFacade customerRequestStatus;

    public void openPage() {
        getDriver().get(Config.getFullUrl() + "profile/requests/");
    }

    public void verifyRequestId(String requestId) {
        assertThat(requestIdForm.getText().replaceAll("[^0-9.]", "")).isEqualTo(requestId);
    }

    public void openRequest() {
        requestDetailsBtn.click();
    }

    public void clickPhoneButton() {
        phoneButton.click();
    }

    public void verifyRequestStatus(String status) {
        requestStatus.shouldContainText(status);
    }

    public void verifyCustomerRequestStatus(String status) {
        customerRequestStatus.shouldContainOnlyText(status);
    }

    public void verifyRequestsTableIsEmpty() {
        assertThat(requests).isEmpty();
    }
}
