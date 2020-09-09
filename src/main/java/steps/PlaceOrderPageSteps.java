package steps;

import entities.TestCategory;
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
    public void placeOrder(User customer, TestCategory category) {
        fillInFirstPage(customer.getFirstName(), category, XmlParser.getTextByKey("Question"))
                .clickNextButton(RequestPages.First);
        fillInSecondPage()
                .clickNextButton(RequestPages.Second);
        fillInThirdPage(customer.getCity(), customer.getPhoneNumber())
                .clickNextButton(RequestPages.Last);

        var smsCode = new Admin().getSmsCode(customer.getPhoneNumber());

        confirmPhoneNumber(smsCode);
    }

    @Step
    public PlaceOrderPageSteps fillInFirstPage(String userName, TestCategory category, String question) {
        placeOrderPage.enterName(userName);
        placeOrderPage.selectCategory(category.getSystemId());
        placeOrderPage.selectWhatToDo(question);
        return this;
    }

    @Step
    public void clickNextButton(RequestPages pageNumber) {
        placeOrderPage.clickNextButton(pageNumber);
        placeOrderPage.waitForLoaderDisappears();
    }

    @Step
    public PlaceOrderPageSteps fillInSecondPage() {
        return this;
    }

    @Step
    public PlaceOrderPageSteps fillInThirdPage(String address, String phone) {
        placeOrderPage.enterAddress(address);
        placeOrderPage.enterPhoneNumber(phone);
        return this;
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
}