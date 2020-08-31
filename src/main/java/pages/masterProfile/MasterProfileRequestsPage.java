package pages.masterProfile;

import enums.RequestPages;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.Config;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MasterProfileRequestsPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, '/profile/requests')]")
    private WebElementFacade requestsTab;

    @FindBy(xpath = "//span[@class='id']")
    private WebElementFacade requestIdForm;

    @FindBy(xpath = "//a[contains(@href, '/request/')]")
    private WebElementFacade requestDetailsBtn;

    @FindBy(xpath = "//li[@data-tab='tab-1']")
    private WebElementFacade requestFirstPage;

    @FindBy(xpath = "//li[@data-tab='tab-2']")
    private WebElementFacade requestSecondPage;

    @FindBy(xpath = "//li[@data-tab='tab-3']")
    private WebElementFacade requestLastPage;

    @FindBy(xpath = "//button[@id='btn-customer-contact']")
    private WebElementFacade customerContactBtn;

    @FindBy(xpath = "//div[contains(@class, 'request-contact')]//div[@class='window-body']")
    private WebElementFacade errorPopup;

    @FindBy(xpath = "//div[@class='contact']//div[@class='phone']/a")
    private WebElementFacade customerPhoneNumber;

    @FindBy(xpath = "//div[@class='contact']//div[@class='name']")
    private WebElementFacade customerName;

    @FindBy(xpath = "//div[@class='button-close']")
    private WebElementFacade closePopupButton;

    @FindBy(xpath = "//div[@class='btn-contact']")
    private WebElementFacade phoneButton;

    public void openPage() {
        getDriver().get(Config.getFullUrl() + "profile/requests/");
        requestsTab.click();
    }

    public void verifyRequestId(String requestId) {
        assertThat(requestIdForm.getText().replaceAll("[^0-9.]", "")).isEqualTo(requestId);
    }

    public void openRequest() {
        requestDetailsBtn.click();
    }

    public void verifyContactClientPrice(String clickPrice) {
        assertThat(customerContactBtn.getText().replaceAll("[^0-9.]", "")).isEqualTo(clickPrice);
    }

    public void openRequestPage(RequestPages page) {
        switch (page) {
            case First:
                requestFirstPage.click();
                break;
            case Second:
                requestSecondPage.click();
                break;
            case Last:
                requestLastPage.click();
                break;
        }
    }

    public void clickConnectClientButton() {
        customerContactBtn.click();
    }

    public void verifyErrorPopupText(String withdrawError) {
        errorPopup.shouldContainText(withdrawError);
    }

    public void verifyCustomerNumber(String phoneNumber) {
        customerPhoneNumber.shouldContainText(phoneNumber);
    }

    public void verifyCustomerName(String firstName) {
        customerName.shouldContainText(firstName);
    }

    public void phoneButtonShouldBeVisible() {
        phoneButton.shouldBeVisible();
    }

    public void verifyCustomerConnectButtonHasNoPrice() {
        assertThat(customerContactBtn.getText().replaceAll("[^0-9.]", "")).isEqualTo("");
    }
}
