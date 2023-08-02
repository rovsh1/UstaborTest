package steps;

import entities.Category;
import entities.User;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.PlaceOrderPage;
import enums.RequestPages;
import utils.Admin;
import utils.XmlParser;

public class PlaceOrderPageSteps extends ScenarioSteps {

    private PlaceOrderPage placeOrderPage;

    @Step
    public void verifyPage() {
//        placeOrderPage.makeSureFormIsVisible();
//        placeOrderPage.nameInputShouldBeVisible();
//        placeOrderPage.domainDropdownShouldBeVisible();
//        placeOrderPage.categoryDropdownShouldBeVisible();
//        placeOrderPage.photoFormShouldBeVisible();
//        placeOrderPage.additionalInfoFormShouldBeVisible();
    }

    @Step
    public void placeOrderForLoggedUser(User customer, Category category) throws InterruptedException {
        var request = XmlParser.getTextByKey("Service");
        var question = XmlParser.getTextByKey("Question_0");

        selectBuildDomain();
        selectCategory(category);
        selectRequest(request);
        selectQuestion(question);
        selectStartDateImmediately();
        selectStartTime();
        fillContactInfo(customer, "Some request info");
        clickPlaceOrder();

        waitForCodeForm();
        var smsCode = getSmsCode(customer);
        confirmPhoneNumber(smsCode, customer.getPhoneNumber());
    }

    @Step
    public void selectBuildDomain() {
        placeOrderPage.selectBuildDomain();
    }

    @Step
    public void selectStartTime() {
        placeOrderPage.selectStartTimeMorning();
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void selectCategory(Category category) {
        placeOrderPage.selectCategory(category.getSystemId());
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void selectRequest(String request) {
        placeOrderPage.selectWhatToDo(request);
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void selectQuestion(String question) {
        placeOrderPage.selectQuestion(question);
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void selectStartDateImmediately() {
        placeOrderPage.selectStartDateImmediately();
        placeOrderPage.waitForServiceLoader();
    }

    @Step
    public void fillContactInfo(User guest, String info) {
        placeOrderPage.enterName(guest.getFirstName());
        guest.setPhoneCode(placeOrderPage.getCountryCode());
        placeOrderPage.enterPhoneNumber(guest.getPhoneNumber());
        placeOrderPage.enterAddress(guest.getCity());
        placeOrderPage.enterAdditionalInfo(info);
    }

    @Step
    public void confirmPhoneNumber(String code, String number) {
        placeOrderPage.enterSmsCode(code);
        placeOrderPage.clickCodeConfirm();

        if (placeOrderPage.isRefreshLinkVisible()) {
            retryEnterCode(number);
        }
    }

    @Step
    public void retryEnterCode(String phoneNumber) {
        placeOrderPage.resendCode();
        placeOrderPage.waitForLoaderDisappears();

        var smsCode = Admin.getInstance().getSmsCode(phoneNumber);
        placeOrderPage.enterSmsCode(smsCode);
        placeOrderPage.clickConfirmButton();
    }

    @Step
    public void openRequestsPage() {
        placeOrderPage.clickMyRequestsBtn();
    }

    @Step
    public void waitForCodeForm() {
        placeOrderPage.waitForSubmitCodeForm();
    }

    @Step
    public String getSmsCode(User customer) throws InterruptedException {
        return placeOrderPage.getSmsCode(customer);
    }

    @Step
    public void clickPlaceOrder() {
        placeOrderPage.clickConfirmButton();
    }

    @Step
    public void verifyProgress(int percent) {
        placeOrderPage.verifyProgress(percent);
    }

    @Step
    public void verifyMastersCount(int i) {
        placeOrderPage.verifyMastersCount(i);
    }
}
