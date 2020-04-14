package pages;

import entities.Master;
import entities.User;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import utils.XmlParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends SearchBlock {

    //region Common elements
    @FindBy(xpath = "//div[contains(@class,'customer')]")
    private WebElementFacade iAmCustomerBtn;

    @FindBy(xpath = "//div[contains(@class,'master')]")
    private WebElementFacade iAmMasterBtn;

    @FindBy(xpath = "//li[@data-i='1']")
    private WebElementFacade builderTab;
    //endregion

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
    private WebElementFacade regFormConfirmPasswordInput;

    @FindBy(xpath = "//form[contains(@id, 'form-registration')]//button[@type='submit']")
    private WebElementFacade regFormSubmitBtn;

    @FindBy(xpath = "//input[@id='form_confirm_code']")
    private WebElementFacade regFormConfirmationCodeInput;

    @FindBy(xpath = "//form[@id='form-confirmation']//button[@type='submit']")
    private WebElementFacade regFormSubmitCodeBtn;

    @FindBy(xpath = "//div[contains(@class, 'field-rules')]//a")
    private WebElementFacade regFormRulesLink;
    //endregion

    //region Master registration form
    private static final String registrationFormXpath = "//form[@id='form-registration-master']";
    @FindBy(xpath = registrationFormXpath)
    private WebElementFacade masterRegistrationForm;

    @FindBy(xpath = registrationFormXpath)
    private List<WebElementFacade> masterRegistrationForm1;

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

    @FindBy(xpath = "//a[@data-id='901']")
    private WebElementFacade regMasterDesignCategory;

    @FindBy(xpath = "//div[@id='rfm-categories']//a")
    private List<WebElementFacade> regMasterCategoriesList;

    @FindBy(xpath = "//input[@id='form_registration_master_phone']")
    private WebElementFacade regMasterPhoneNumber;

    @FindBy(xpath = "//select[@id='form_registration_master_experience']")
    private WebElementFacade regMasterExperienceSelector;

    @FindBy(xpath = "//select[@id='form_registration_master_city_id']")
    private WebElementFacade regMasterCitySelector;
    //endregion

    //region Place order form
    @FindBy(xpath = "//form[@id='form-order']//input[@id='form_order_username']")
    private WebElementFacade orderFormUsernameInput;

    @FindBy(xpath = "//form[@id='form-order']//input[@id='form_order_phone']")
    private WebElementFacade orderFormPhoneInput;

    @FindBy(xpath = "//form[@id='form-order']//select[@id='form_order_service']")
    private WebElementFacade orderFormServiceSelector;

    @FindBy(xpath = "//form[@id='form-order']//button[@class='button-submit']")
    private WebElementFacade orderFormSubmitBtn;
    //endregion

    //region Mobile view elements
    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'countries')]")
    private WebElementFacade mobileMenuCountryBtn;

    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'countries')]//a")
    private List<WebElementFacade> mobileCountryList;

    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'language')]")
    private WebElementFacade mobileMenuLangBtn;

    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'language')]//a")
    private List<WebElementFacade> mobileMenuLangsList;

    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'sites')]")
    private WebElementFacade mobileMenuSitesBtn;

    @FindBy(xpath = "//div[@class='sitemap']//nav[contains(@class, 'sites')]//a")
    private List<WebElementFacade> mobileMenuSitesList;

    @FindBy(xpath = "//div[@class='sitemap']//div[@id='sitemap-phone-button']")
    private WebElementFacade mobileMenuPhoneBtn;

    @FindBy(xpath = "//div[@class='window']//div[@class='hint']")
    private WebElementFacade mobileMenuPhonePopup;

    @FindBy(xpath = "//div[@class='sitemap']//a[contains(@class, 'btn-customer')]")
    private WebElementFacade mobileMenuCustomerRegistrationBtn;

    @FindBy(xpath = "//div[@class='sitemap']//a[contains(@class, 'btn-master')]")
    private WebElementFacade mobileMenuMasterRegistrationBtn;
    //endregion

    @FindBy(xpath = "//div[contains(@class, 'categories-tabs')]//a[@id='categories-catalog-btn']")
    private WebElementFacade viewFullCatalogBtn;

    @FindBy(xpath = "//div[@class='window feedback-window']//a[@class='button button-submit']")
    private WebElementFacade leaveFeedbackBtn;

    @FindBy(xpath = "//div[@id='categories']//div[@class='item']/a")
    private List<WebElementFacade> categoriesList;

    @FindBy(xpath = "//input[@id='form_password_login']")
    private WebElementFacade forgotPasswordEmailInput;

    @FindBy(xpath = "//form[@id='form-password']//button[@type='submit']")
    private WebElementFacade restorePasswordBtn;

    @FindBy(xpath = "//div[@class='row row-faq']//div[@class='item']")
    private List<WebElementFacade> faqItemsList;

    @FindBy(xpath = "//div[@class='row row-faq']//div[@class='item expanded']//div[@class='text']")
    private WebElementFacade faqOpenedItemText;


    //region Customer registration form methods
    public void openRegistrationForm() {
        performRegistrationBtn.click();
    }

    public void regFormEnterLogin(String login) {
        regFormEmailInput.sendKeys(login);
    }

    public void regFormEnterPassword(String password) {
        regFormPasswordInput.sendKeys(password);
        regFormConfirmPasswordInput.sendKeys(password);
    }

    public void regFormClickSubmit() {
        regFormSubmitBtn.click();
        waitForLoaderDisappears();
    }

    public void regFormEnterAuthCode(String code) {
        regFormConfirmationCodeInput.sendKeys(code);
    }

    public void regFormClickSubmitAuthCode() {
        regFormSubmitCodeBtn.click();
    }
    //endregion

    //region Master registration form methods
    public void regMasterFormEnterFirstName(String name) {
        regMasterNameInput.sendKeys(name);
    }

    public void regMasterFormEnterLastName(String surname) {
        regMasterSurnameInput.sendKeys(surname);
    }

    public void regMasterFormEnterAboutMe(String aboutMe) {
        regMasterAboutMeInput.sendKeys(aboutMe);
    }

    public void regMasterFormSelectBuildSubDomain() {
        regMasterDomainSelector.selectByVisibleText(XmlParser.getTextByKey("SiteDomainBuild_Full"));
        waitForLoaderDisappears();
    }

    public void regMasterFormSelectRandomCategory(Master master) {
        regMasterCategory.waitUntilVisible();
        WebElementFacade category = regMasterCategoriesList.get(new Random().nextInt(regMasterCategoriesList.size()));
        focusElementJS(category);
        category.click();

        master.setCategory(category.getText());
        master.setCategoryId(category.getAttribute("data-id"));
    }

    public void regMasterFormSelectCategory(Master master, String categoryName) {
        regMasterCategory.waitUntilVisible();
        WebElementFacade category = regMasterCategoriesList.stream()
                .filter(x -> x.getText().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (category == null) {
            throw new NullPointerException(String.format("No category found: %s", categoryName));
        }

        focusElementJS(category);
        category.click();

        master.setCategory(category.getText());
        master.setCategoryId(category.getAttribute("data-id"));
    }

    public void regMasterFormEnterPhoneNumber(String phoneNumber) {
        regMasterPhoneNumber.sendKeys(phoneNumber);
    }

    public void regMasterFormSelectExperience() {
        regMasterExperienceSelector.selectByIndex(1);
    }

    public String regMasterFormSelectCity() {
        regMasterCitySelector.selectByIndex(1);
        return regMasterCitySelector.getSelectedVisibleTextValue();
    }
    //endregion

    //region SignIn form methods
    public void signInFormEnterLogin(String login) {
        clearInputJS(signInFormLoginInput);
        signInFormLoginInput.sendKeys(login);
    }

    public void signInFormEnterPassword(String password) {
        clearInputJS(signInFormPasswordInput);
        signInFormPasswordInput.sendKeys(password);
    }

    public void signInFormClickLoginBtn() {
        signInFormLoginBtn.click();
    }
    //endregion

    //region Forgot password form methods
    public void openForgotPasswordForm() {
        forgotPasswordLink.click();
    }

    public void forgotPasswordEnterEmail(String email) {
        forgotPasswordEmailInput.sendKeys(email);
    }

    public void forgotPasswordClickRestoreBtn() {
        restorePasswordBtn.click();
    }
    //endregion

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

    public void authCodeFormShouldBeVisible() {
        regFormConfirmationCodeInput.shouldBeVisible();
    }

    public void registerFormShouldNotBeVisible() {
        if (Config.isChrome()) {
            masterRegistrationForm.withTimeoutOf(Duration.ofSeconds(30)).waitUntilNotVisible();
        } else {
            new WebDriverWait(getDriver(), 30)
                    .until(driver -> driver.findElements(By.xpath(registrationFormXpath)).size() == 0);
        }

    }

    public void registerFormShouldBeVisible() {
        regMasterNameInput.shouldBeVisible();
    }

    public void orderFormUsernameInputShouldBeVisible() {
        orderFormUsernameInput.shouldBeVisible();
    }

    public void orderFormPhoneInputShouldBeVisible() {
        orderFormPhoneInput.shouldBeVisible();
    }

    public void orderFormServiceSelectorShouldBeVisible() {
        orderFormServiceSelector.shouldBeVisible();
    }

    public void orderFormSubmitBtnShouldBeVisible() {
        orderFormSubmitBtn.shouldBeVisible();
    }

    public void verifyFaqItemTextIsVisible() {
        faqOpenedItemText.shouldBeVisible();
    }

    public void verifyMobileViewCountriesMenu() {
        mobileCountryList.forEach(WebElementState::shouldBeVisible);
    }

    public void verifyMobileViewLanguagesMenu() {
        mobileMenuLangsList.forEach(WebElementState::shouldBeVisible);
    }

    public void verifyMobileViewSitesMenu() {
        mobileMenuSitesList.forEach(WebElementFacade::shouldBeVisible);
    }

    public void verifyMobileViewPhoneFormText(String text) {
        mobileMenuPhonePopup.shouldContainText(text);
    }

    public void waitUntilFeedbackPopupIsVisible() {
        leaveFeedbackBtn.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
    }

    public void loginErrorWithTextShouldBeVisible(String text) {
        assertThat(loginError.getText()).isEqualTo(text);
    }
    //endregion

    //region Mobile view methods
    public void openMobileViewCountryMenu() {
        mobileMenuCountryBtn.click();
    }

    public void openMobileViewLangMenu() {
        mobileMenuLangBtn.click();
    }

    public void openMobileViewSitesMenu() {
        mobileMenuSitesBtn.click();
    }

    public void openMobileViewPhoneForm() {
        mobileMenuPhoneBtn.click();
    }

    public void openMobileViewCustomerRegistrationForm() {
        mobileMenuCustomerRegistrationBtn.click();
    }
    //endregion


    public void clickCustomerBtn() {
        iAmCustomerBtn.click();
    }

    public void clickMasterBtn() {
        iAmMasterBtn.click();
        waitForLoaderDisappears();
    }

    public void clickCatalogBtn() {
        if (Config.isChrome()) {
            viewFullCatalogBtn.click();
        } else {
            clickElementJS(viewFullCatalogBtn);
        }
    }

    public boolean isLoggedIn() {
        return isLogoutBtnVisible();
    }

    public void clickLeaveFeedback() {
        leaveFeedbackBtn.click();
    }

    public void openRandomCategory() {
        var category = categoriesList.get(new Random().nextInt(categoriesList.size()));
        focusElementJS(category);
        category.click();
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

    public void openRandomFaqItem() {
        faqItemsList
                .get(new Random().nextInt(faqItemsList.size()))
                .findElement(By.xpath(".//i"))
                .click();
    }

    public void regFormSubmitBtnShouldBeVisible() {
        regFormSubmitBtn.shouldBeVisible();
    }

    public void regFormLoginInputShouldBeVisible() {
        regFormEmailInput.shouldBeVisible();
    }

    public void regFormPassInputShouldBeVisible() {
        regFormPasswordInput.shouldBeVisible();
    }

    public void regFormConfirmPassInputShouldBeVisible() {
        regFormConfirmPasswordInput.shouldBeVisible();
    }

    public void regFormConditionsLinkShouldBeVisible() {
        regFormRulesLink.shouldBeVisible();
    }

    public void clickBuilderTab() {
        builderTab.click();
    }

    public void openCategory(String category) {
        WebElementFacade foundCategory = categoriesList.stream()
                .filter(e -> e.getText().equals(category))
                .findAny()
                .orElse(null);

        if (foundCategory == null) {
            throw new NullPointerException(String.format("No element with text '%s' found", category));
        }

        focusElementJS(foundCategory);
        foundCategory.click();
    }

    public void loginErrorShouldBeVisible() {
        loginError.shouldBeVisible();
    }

    public void setLanguage(String lang) {
        if (getCurrentLang().toLowerCase().equals(lang)) {
            return;
        }

        openHeaderLangDropDown();
        selectLanguage(lang);
    }
}
