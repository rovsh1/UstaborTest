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
        placeOrderPage.nameInputShouldBeVisible();
        placeOrderPage.domainDropdownShouldBeVisible();
        placeOrderPage.categoryDropdownShouldBeVisible();
        placeOrderPage.photoFormShouldBeVisible();
        placeOrderPage.additionalInfoFormShouldBeVisible();
    }

    @Step
    public void placeOrder(User customer, Category category) {
        fillInFirstPage(
                customer.getFirstName(),
                category,
                XmlParser.getTextByKey("Service"),
                XmlParser.getTextByKey("Question"),
                "test request");

        placeOrderPage.clickNextButton(RequestPages.First);
        placeOrderPage.clickNextButton(RequestPages.Second);

        fillInThirdPage(customer);
        customer.setPhoneCode(placeOrderPage.getCountryCode());
        placeOrderPage.clickNextButton(RequestPages.Last);

        placeOrderPage.waitForSubmitCodeForm();
        var smsCode = Admin.getInstance().getSmsCode(customer.getPhoneNumber());
        confirmPhoneNumber(smsCode);
        placeOrderPage.waitForLoaderDisappears();
    }

    @Step
    public void placeOrderForLoggedUser(User customer, Category category) {
        fillInFirstPage(
                category,
                XmlParser.getTextByKey("Service"),
                XmlParser.getTextByKey("Question"),
                "test request");

        placeOrderPage.clickNextButton(RequestPages.First);
        placeOrderPage.clickNextButton(RequestPages.Second);

        placeOrderPage.enterAddress(customer.getCity());

        customer.setPhoneCode(placeOrderPage.getCountryCode());
        placeOrderPage.clickNextButton(RequestPages.Last);

        placeOrderPage.waitForSubmitCodeForm();
        var smsCode = Admin.getInstance().getSmsCode(customer.getPhoneNumber());
        confirmPhoneNumber(smsCode);
        placeOrderPage.waitForLoaderDisappears();
    }

    @Step
    public void fillInFirstPage(Category category, String service, String question, String info) {
        placeOrderPage.selectCategory(category.getSystemId());
        placeOrderPage.selectService(service);
        placeOrderPage.selectQuestion(question);
        placeOrderPage.enterAdditionalInfo(info);
    }

    @Step
    public void fillInFirstPage(String userName, Category category, String service, String question, String info) {
        placeOrderPage.enterName(userName);
        placeOrderPage.selectCategory(category.getSystemId());
        placeOrderPage.selectService(service);
        placeOrderPage.selectQuestion(question);
        placeOrderPage.enterAdditionalInfo(info);
    }

    @Step
    public void clickNextButton(RequestPages pageNumber) {
        placeOrderPage.clickNextButton(pageNumber);
        placeOrderPage.waitForLoaderDisappears();
    }

    @Step
    public void fillInThirdPage(User customer) {
        placeOrderPage.enterAddress(customer.getCity());
        placeOrderPage.enterPhoneNumber(customer.getPhoneNumber());
    }

    @Step
    public PlaceOrderPageSteps confirmPhoneNumber(String smsCode) {
        placeOrderPage.enterSmsCode(smsCode);
        placeOrderPage.clickConfirmButton();
        return this;
    }

    @Step
    public void successPageShouldBeVisible() {
        placeOrderPage.successPageShouldBeVisible();
    }

    @Step
    public void openRequestsPage() {
        placeOrderPage.clickMyRequestsBtn();
    }

    @Step
    public PlaceOrderPageSteps priceRangeShouldBeVisible(String minPrice, String maxPrice) {
        placeOrderPage.priceShouldBeVisible(minPrice);
        placeOrderPage.priceShouldBeVisible(maxPrice);
        return this;
    }

    @Step
    public void waitForCodeForm() {
        placeOrderPage.waitForSubmitCodeForm();
    }

    @Step
    public String getSmsCode(String phoneNumber) throws InterruptedException {
        return placeOrderPage.getSmsCode(phoneNumber);
    }
}
