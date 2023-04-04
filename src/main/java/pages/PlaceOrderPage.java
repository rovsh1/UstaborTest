package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import enums.RequestPages;
import utils.Admin;

import java.time.Duration;

public class PlaceOrderPage extends BasePage {

    //region First page
    @FindBy(xpath = "//div[@class='site-items']/a[1]")
    private WebElementFacade buildDomain;
    //endregion

    //region Second page
    @FindBy(xpath = "//div[@data-id='now']")
    private WebElementFacade startDateImmediately;

    @FindBy(xpath = "//div[@data-id='morning']")
    private WebElementFacade startTimeMorning;
    //endregion

    //region Third page
    @FindBy(xpath = "//input[@id='form_data_username']")
    private WebElementFacade nameInput;

    @FindBy(xpath = "//input[@id='form_data_contact_phone']")
    private WebElementFacade phoneInput;

    @FindBy(xpath = "//input[@id='form_data_address']")
    private WebElementFacade addressInput;

    @FindBy(xpath = "//textarea[@id='form_data_note']")
    private WebElementFacade additionalInfoInput;
    //endregion

    //region Confirmation form
    @FindBy(xpath = "//input[@id='form_confirmation_code']")
    private WebElementFacade codeInput;

    @FindBy(xpath = "//form[@id='form-confirmation']//button[@type='submit']")
    private WebElementFacade codeConfirm;

    @FindBy(xpath = "//form//button[@type='submit']")
    private WebElementFacade confirmBtn;
    //endregion

    @FindBy(xpath = "//a[contains(@href, '/customer/requests')]")
    private WebElementFacade myRequestsBtn;

    @FindBy(xpath = "//div[@class='progress']/div[@class='value']")
    private WebElementFacade progress;

    @FindBy(xpath = "//a[@class='catalog']//i")
    private WebElementFacade mastersCount;

    public void nameInputShouldBeVisible() {
        nameInput.shouldBeVisible();
    }

    public void additionalInfoFormShouldBeVisible() {
        additionalInfoInput.shouldBeVisible();
    }

    public void enterName(String userName) {
        nameInput.sendKeys(userName);
    }

    public void selectCategory(String systemId) {
        getDriver().findElement(By.xpath(String.format("//div[@data-id='%s']", systemId))).click();
        waitForLoaderDisappears();
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

    public void clickMyRequestsBtn() {
        myRequestsBtn.click();
    }

    public void selectWhatToDo(String question) {
        getDriver().findElement(By.xpath(String.format("//div[text()='%s']", question))).click();
    }

    public void selectQuestion(String question) {
        getDriver().findElement(By.xpath(String.format("//div[text()='%s']", question))).click();
    }

    public String getCountryCode() {
        return phoneInput.getAttribute("placeholder").replaceAll("[^\\d]", "");
    }

    public void enterAdditionalInfo(String info) {
        additionalInfoInput.sendKeys(info);
    }

    public String getSmsCode(String phoneNumber) throws InterruptedException {
        var smsCode = Admin.getInstance().getSmsCode(phoneNumber);

        if (smsCode.isEmpty()) {
            clickResendCode();
            Thread.sleep(3000);
        }

        return Admin.getInstance().getSmsCode(phoneNumber);
    }

    public void selectBuildDomain() {
        buildDomain.click();
    }

    public void selectStartDateImmediately() {
        startDateImmediately.click();
    }

    public void selectStartTimeMorning() {
        startTimeMorning.click();
    }

    public void clickCodeConfirm() {
        codeConfirm.click();
    }

    public void verifyProgress(int percent) {
        progress.shouldContainText(String.format("%d%%", percent));
    }

    public void verifyMastersCount(int i) {
        mastersCount.shouldContainOnlyText(Integer.toString(i));
    }
}
