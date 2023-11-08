package steps.masterProfileSteps;

import entities.User;
import enums.RequestPages;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.masterProfile.MasterRequestPage;

public class MasterRequestPageSteps extends ScenarioSteps {

    private MasterRequestPage masterRequestPage;

    @Step
    public void verifyClickPriceFirstPage(String clickPrice) {
        masterRequestPage.openRequestPage(RequestPages.First);
        masterRequestPage.verifyContactClientPrice(clickPrice);
    }

    @Step
    public void verifyClickPriceSecondPage(String clickPrice) {
        masterRequestPage.openRequestPage(RequestPages.Second);
        masterRequestPage.verifyContactClientPrice(clickPrice);
    }

    @Step
    public void verifyClickPriceLastPage(String clickPrice) {
        masterRequestPage.openRequestPage(RequestPages.Last);
        masterRequestPage.verifyContactClientPrice(clickPrice);
    }

    @Step
    public void clickConnectClientButton() {
        masterRequestPage.clickConnectClientButton();
        masterRequestPage.waitForLoaderDisappears();
    }

    @Step
    public void verifyErrorMessage(String withdrawError) {
        masterRequestPage.verifyErrorPopupText(withdrawError);
    }

    @Step
    public void verifyCustomerInfo(User customer) {
        masterRequestPage.verifyCustomerNumber(customer.getPhoneNumber());
        masterRequestPage.verifyCustomerName(customer.getFirstName());
    }

    @Step
    public void verifyCustomerConnectButtonHasNoPrice() {
        masterRequestPage.verifyCustomerConnectButtonHasNoPrice();
    }

    @Step
    public void closeConnectCustomerPopup() {
        masterRequestPage.clickClosePopupBtn();
    }

    @Step
    public void sendMessageToCustomer(String message) {
        masterRequestPage.sendMessageToCustomer(message);
        masterRequestPage.clickSendMessageBtn();
        masterRequestPage.waitForLoaderDisappears();
    }

    @Step
    public void makeOfferToCustomer(String price) {
        masterRequestPage.typeOffer(price);
        masterRequestPage.clickOfferPriceButton();
        masterRequestPage.waitForLoaderDisappears();
    }
}
