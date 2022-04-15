package pages.masterProfile;

import enums.RequestPages;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MasterRequestPage extends BasePage {

    @FindBy(xpath = "//button[@id='btn-customer-contact']")
    private WebElementFacade customerContactBtn;

    @FindBy(xpath = "//div[contains(@class, 'request-contact')]//div[@class='window-body']")
    private WebElementFacade errorPopup;

    @FindBy(xpath = "//div[@class='contact']//div[@class='phone']/a")
    private WebElementFacade customerPhoneNumber;

    @FindBy(xpath = "//div[@class='contact']//div[@class='name']")
    private WebElementFacade customerName;

    @FindBy(xpath = "//li[@data-tab='tab-1']")
    private WebElementFacade requestFirstPage;

    @FindBy(xpath = "//li[@data-tab='tab-2']")
    private WebElementFacade requestSecondPage;

    @FindBy(xpath = "//li[@data-tab='tab-3']")
    private WebElementFacade requestLastPage;

    @FindBy(xpath = "//div[@class='btn-close']")
    private WebElementFacade closePopupButton;

    @FindBy(xpath = "//div[@class='msg']//textarea")
    private WebElementFacade msgToCustomerInput;

    @FindBy(xpath = "//button[@id='btn-send-msg']")
    private WebElementFacade sendMessageBtn;

    @FindBy(xpath = "//div[@class='price']//input")
    private WebElementFacade offerPriceInput;

    @FindBy(xpath = "//button[@id='btn-send-price']")
    private WebElementFacade offerPriceBtn;

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

    public void verifyCustomerConnectButtonHasNoPrice() {
        assertThat(customerContactBtn.getText().replaceAll("[^0-9.]", "")).isEqualTo("");
    }

    public void clickClosePopupBtn() {
        closePopupButton.click();
    }

    public void sendMessageToCustomer(String message) {
        msgToCustomerInput.sendKeys(message);
    }

    public void clickSendMessageBtn() {
        sendMessageBtn.click();
    }

    public void typeOffer(String price) {
        offerPriceInput.sendKeys(price);
    }

    public void clickOfferPriceButton() {
        offerPriceBtn.click();
    }
}
