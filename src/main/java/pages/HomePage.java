package pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends SearchBlock {

    @FindBy(xpath = "//div[contains(@class,'customer')]")
    private WebElementFacade iAmCustomerBtn;

    @FindBy(xpath = "//div[contains(@class,'master')]")
    private WebElementFacade iAmMasterBtn;

    //region Login form
    @FindBy(xpath = "//input[@id='form_login_login']")
    private WebElementFacade signInFormLoginInput;

    @FindBy(xpath = "//input[@id='form_login_password']")
    private WebElementFacade signInFormPasswordInput;

    @FindBy(xpath = "//form[@id='form-login']//button[@type='submit']")
    private WebElementFacade signInFormLoginBtn;

    @FindBy(xpath = "//a[@class='link-register']")
    private WebElementFacade signInFormRegisterLink;

    @FindBy(xpath = "//a[@href='#window-recovery']")
    private WebElementFacade forgotPasswordLink;

    @FindBy(xpath = "//form[@id='form-login']//div[@class='form-errors']")
    private WebElementFacade loginError;

    //endregion

    //region Customer registration form
    @FindBy(xpath = "//a[@href='#registrationForm']")
    private WebElementFacade performRegistrationBtn;

    @FindBy(xpath = "//input[@type='email']")
    private WebElementFacade regFormEmailInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElementFacade regFormPasswordInput;

    @FindBy(xpath = "//input[@type='password' and contains(@name, 'password_confirm')]")
    private WebElementFacade confirmPasswordInput;

    @FindBy(xpath = "//form[contains(@id, 'form-registration')]//button[@type='submit']")
    private WebElementFacade sendRegisterFormBtn;

    @FindBy(xpath = "//input[@id='form_confirm_code']")
    private WebElementFacade confirmationCodeInput;

    @FindBy(xpath = "//form[@id='form-confirmation']//button[@type='submit']")
    private WebElementFacade submitCodeBtn;
    //endregion

    //region Master registration form
    //private final String regMasterCategoriesXpath = "(//div[@id='rfm-categories']//a)[%d]";

    @FindBy(xpath = "//form[@id='form-registration-master']")
    private WebElementFacade masterRegistrationForm;

    @FindBy(xpath = "//input[@type='file']")
    private WebElementFacade fileInput;

    @FindBy(xpath = "//input[@id='form_registration_master_name']")
    private WebElementFacade regMasterNameInput;

    @FindBy(xpath = "//input[@id='form_registration_master_surname']")
    private WebElementFacade regMasterSurnameInput;

    @FindBy(xpath = "//textarea[@id='form_registration_master_about']")
    private WebElementFacade regMasterAboutMeInput;

    @FindBy(xpath = "//select[@id='form_registration_master_site_id']")
    private WebElementFacade regMasterDomainSelector;

    @FindBy(xpath = "//div[@id='rfm-categories']//a")
    private WebElementFacade regMasterCategory;

    @FindBy(xpath = "//div[@id='rfm-categories']//a")
    private List<WebElementFacade> regMasterCategoriesList;

    @FindBy(xpath = "//input[@id='form_registration_master_phone']")
    private WebElementFacade regMasterPhoneNumber;

    @FindBy(xpath = "//select[@id='form_registration_master_experience']")
    private WebElementFacade regMasterExperienceSelector;

    @FindBy(xpath = "//select[@id='form_registration_master_city_id']")
    private WebElementFacade regMasterCitySelector;
    //endregion

    @FindBy(xpath = "//a[@id='categories-catalog-btn']")
    private WebElementFacade viewFullCatalogBtn;

    @FindBy(xpath = "//div[@class='window feedback-window']//a[@class='button button-submit']")
    private WebElementFacade leaveFeedbackBtn;

    @FindBy(xpath = "//div[@id='categories']//div[@class='item']/a")
    private List<WebElementFacade> categoriesList;

    @FindBy(xpath = "//input[@id='form_password_login']")
    private WebElementFacade forgotPasswordEmailInput;

    @FindBy(xpath = "//form[@id='form-password']//button[@type='submit']")
    private WebElementFacade restorePasswordBtn;

    //region Validation methods

    public void iAmMasterBtnShouldBeVisible() {
        iAmMasterBtn.shouldBeVisible();
    }

    public void iAmCustomerBtnShouldBeVisible() {
        iAmCustomerBtn.shouldBeVisible();
    }

    public void viewFullCatalogBtnShouldBeVisible() {
        viewFullCatalogBtn.shouldBeVisible();
    }

    public void pageShouldBeVisible() {
        if (!isOpenLoginFormBtnVisible()) {
            Serenity.throwExceptionsImmediately();
            throw new NullPointerException();
        }
    }

    public void registerLinkShouldBeVisible() {
        signInFormRegisterLink.shouldBeVisible();
    }

    public void loginInputShouldBeVisible() {
        signInFormLoginInput.shouldBeVisible();
    }

    public void passwordInputShouldBeVisible() {
        signInFormPasswordInput.shouldBeVisible();
    }

    public void loginBtnShouldBeVisible() {
        signInFormLoginBtn.shouldBeVisible();
    }

    public void forgotPasswordLinkShouldBeVisible() {
        forgotPasswordLink.shouldBeVisible();
    }

    //endregion

    public void clickRegistrationBtn() {
        performRegistrationBtn.click();
    }

    public void clickCustomerBtn() {
        iAmCustomerBtn.click();
    }

    public void clickMasterBtn() {
        iAmMasterBtn.click();
    }

    public void enterRegistrationLogin(String login) {
        regFormEmailInput.sendKeys(login);
    }

    public void enterRegistrationPassword(String password) {
        regFormPasswordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
    }

    public void sendRegisterForm() {
        sendRegisterFormBtn.click();
    }

    public void enterAuthCode(String code) {
        confirmationCodeInput.sendKeys(code);
    }

    public void clickSubmitAuthCode() {
        submitCodeBtn.click();
    }

    public void clickCatalogBtn() {
        viewFullCatalogBtn.click();
    }

    public void enterLogin(String login) {
        clearInputJS(signInFormLoginInput);
        signInFormLoginInput.sendKeys(login);
    }

    public void enterPassword(String password) {
        clearInputJS(signInFormPasswordInput);
        signInFormPasswordInput.sendKeys(password);
    }

    public void clickLoginBtn() {
        signInFormLoginBtn.click();
    }

    public boolean isLoginSuccessful() {
        return isLogoutBtnVisible();
    }

    public void waitUntilFeedbackPopupIsVisible() {
        leaveFeedbackBtn.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
    }

    public void clickLeaveFeedback() {
        leaveFeedbackBtn.click();
    }


    public void openRandomCategory() {
        categoriesList.get(new Random().nextInt(categoriesList.size())).click();
    }


    public void loginErrorWithTextShouldBeVisible(String text) {
        assertThat(loginError.getText()).isEqualTo(text);
    }

    public void enterRegistrationName(String name) {
        regMasterNameInput.sendKeys(name);
    }

    public void enterRegistrationSurname(String surname) {
        regMasterSurnameInput.sendKeys(surname);
    }

    public void enterAboutMe(String aboutMe) {
        regMasterAboutMeInput.sendKeys(aboutMe);
    }

    public void selectRandomSubDomain() {
        regMasterDomainSelector.selectByIndex(1);
    }

    public void selectRandomCategory() {
        regMasterCategory.waitUntilVisible();
        regMasterCategoriesList.get(new Random().nextInt(10)).click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        regMasterPhoneNumber.sendKeys(phoneNumber);
    }

    public void selectExperience() {
        regMasterExperienceSelector.selectByIndex(1);
    }

    public String selectCity() {
        regMasterCitySelector.selectByIndex(1);
        return regMasterCitySelector.getSelectedVisibleTextValue();
    }

    public void clickForgotPassword() {
        forgotPasswordLink.click();
    }

    public void forgotPasswordEnterEmail(String email) {
        forgotPasswordEmailInput.sendKeys(email);
    }

    public void clickRestoreBtn() {
        restorePasswordBtn.click();
    }

    public void registerFormShouldNotBeVisible() {
        masterRegistrationForm.withTimeoutOf(Duration.ofSeconds(30)).waitUntilNotVisible();
    }

    public void registerFormShouldBeVisible() {
        regMasterNameInput.shouldBeVisible();
    }

    public void uploadAvatar() {
        try {
            String html = Files.readString(
                    new File("files/avatar_input.js").toPath());
            String script = Files.readString(
                    new File("files/registration_form.js").toPath());
            evaluateJavascript(html);
            evaluateJavascript(script);
            fileInput.sendKeys(new File("files/avatar.jpg").getAbsolutePath());
        } catch (IOException e) {
            Serenity.throwExceptionsImmediately();
            e.printStackTrace();
        }
    }


    public void authCodeFormShouldBeVisible() {
        confirmationCodeInput.shouldBeVisible();
    }
}
