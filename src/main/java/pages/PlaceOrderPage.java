package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import enums.RequestPages;

public class PlaceOrderPage extends BasePage {

    //region First page
    @FindBy(xpath = "//input[@id='form_order_username']")
    private WebElementFacade nameInput;

    @FindBy(xpath = "//select[@id='form_order_site_id']")
    private WebElementFacade domainDropdown;

    @FindBy(xpath = "//select[@id='form_order_category_id']")
    private WebElementFacade categoryDropdown;

    @FindBy(xpath = "//select[@id='cr_a_0']")
    private WebElementFacade whatToDoDropDown;

    @FindBy(xpath = "//select[@id='cr_a_1']")
    private WebElementFacade questionSelector;

    @FindBy(xpath = "//div[@class='dropzone-field']")
    private WebElementFacade photoForm;

    @FindBy(xpath = "//textarea[@id='form_order_text']")
    private WebElementFacade additionalInfoInput;

    @FindBy(xpath = "//div[@class='step step-1']//button[@class='button-submit']")
    private WebElementFacade nextButtonFirst;
    //endregion

    //region Second page
    @FindBy(xpath = "//div[@id='prices-title']")
    private WebElementFacade pricesRangeDisclaimer;

    @FindBy(xpath = "//div[@class='step step-2']//button[@class='button-submit']")
    private WebElementFacade nextButtonSecond;
    //endregion

    //region Third page
    @FindBy(xpath = "//input[@id='form_order_address']")
    private WebElementFacade addressInput;

    @FindBy(xpath = "//input[@id='form_order_contact_phone']")
    private WebElementFacade phoneInput;

    @FindBy(xpath = "//div[@class='step step-3']//button[@class='button-submit']")
    private WebElementFacade nextButtonLast;
    //endregion

    //region Confirmation form
    @FindBy(xpath = "//input[@id='form_confirm_code']")
    private WebElementFacade codeInput;

    @FindBy(xpath = "//form[@id='form-confirmation']//button[@class='button-submit']")
    private WebElementFacade confirmBtn;
    //endregion

    @FindBy(xpath = "//a[contains(@href, '/customer/requests/')]")
    private WebElementFacade myRequestsBtn;

    public void nameInputShouldBeVisible() {
        nameInput.shouldBeVisible();
    }

    public void domainDropdownShouldBeVisible() {
        domainDropdown.shouldBeVisible();
    }

    public void categoryDropdownShouldBeVisible() {
        categoryDropdown.shouldBeVisible();
    }

    public void photoFormShouldBeVisible() {
        photoForm.shouldBeVisible();
    }

    public void additionalInfoFormShouldBeVisible() {
        additionalInfoInput.shouldBeVisible();
    }

    public void enterName(String userName) {
        nameInput.sendKeys(userName);
    }

    public void selectCategory(String systemId) {
        categoryDropdown.selectByValue(systemId);
        waitForLoaderDisappears();
    }

    public void clickNextButton(RequestPages pageNumber) {
        switch (pageNumber) {
            case First:
                nextButtonFirst.click();
                break;
            case Second:
                nextButtonSecond.click();
                break;
            case Last:
                nextButtonLast.click();
                break;
        }
    }

    public void enterAddress(String address) {
        addressInput.sendKeys(address);
    }

    public void enterPhoneNumber(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void enterSmsCode(String smsCode) {
        codeInput.clear();
        codeInput.sendKeys(smsCode);
    }

    public void clickConfirmButton() {
        confirmBtn.click();
    }

    public void successPageShouldBeVisible() {
        myRequestsBtn.shouldBeVisible();
    }

    public void clickMyRequestsBtn() {
        myRequestsBtn.click();
    }

    public void selectWhatToDo(String question) {
        whatToDoDropDown.selectByVisibleText(question);
    }

    public void selectQuestion(String question) {
        questionSelector.selectByVisibleText(question);
    }

    public void priceShouldBeVisible(String price) {
        pricesRangeDisclaimer.shouldContainText(price);
    }
}
