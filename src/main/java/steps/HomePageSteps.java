package steps;

import entities.User;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.HomePage;

import java.util.List;

public class HomePageSteps extends ScenarioSteps {

    private HomePage homePage;

    @Step
    public void enterSearchText(String text) {
        homePage.enterSearchText(text);
    }

    @Step
    public void enterTextAndSearch(String text) {
        enterSearchText(text);
        homePage.ClickSearchBtn();
    }

    @Step
    public void open() {
        homePage.openPageWithConfigUrl();
    }

    @Step
    public void registerAsCustomer(String login, String password) {
        homePage.openRegistrationForm();
        homePage.clickCustomerBtn();
        homePage.regFormEnterLogin(login);
        homePage.regFormEnterPassword(password);
        homePage.regFormClickSubmit();
        homePage.authCodeFormShouldBeVisible();
    }

    @Step
    public void enterAuthCodeAndSubmit(String code) {
        homePage.regFormEnterAuthCode(code);
        homePage.regFormClickSubmitAuthCode();
    }

    @Step
    public void openCatalog() {
        homePage.clickCatalogBtn();
    }

    @Step
    public boolean loginAsMaster(String login, String password, boolean openLoginForm) {
        if (openLoginForm) {
            homePage.openLoginForm();
        }
        homePage.clickMasterBtn();
        homePage.signInFormEnterLogin(login);
        homePage.signInFormEnterPassword(password);
        homePage.signInFormClickLoginBtn();
        return homePage.isLoginSuccessful();
    }

    @Step
    public boolean loginAsCustomer(String login, String password) {
        homePage.openLoginForm();
        homePage.clickCustomerBtn();
        homePage.signInFormEnterLogin(login);
        homePage.signInFormEnterPassword(password);
        homePage.signInFormClickLoginBtn();
        return homePage.isLoginSuccessful();
    }

    @Step
    public void waitForFeedbackProposalAndOpen() {
        homePage.waitUntilFeedbackPopupIsVisible();
        homePage.clickLeaveFeedback();
    }

    @Step
    public void pageShouldBeVisible() {
        homePage.viewFullCatalogBtnShouldBeVisible();
    }


    @Step
    public void selectSuggestionCategoryAndSearch(String suggestion) {
        homePage.suggestionListShouldBeVisible();
        homePage.selectSuggestion(suggestion);
    }

    @Step
    public void openRandomCategory() {
        homePage.openRandomCategory();
    }

    @Step
    public List<String> getCountriesList() {
        return homePage.getCountries();
    }

    @Step
    public void goToDomainWithName(String country) {
        homePage.selectCountryWithName(country);
    }

    @Step
    public void currentDomainNameShouldBe(String country) {
        homePage.currentDomainNameShouldBe(country);
    }

    @Step
    public void homePageShouldBeVisible() {
        homePage.pageShouldBeVisible();
    }

    @Step
    public void openLoginFormAndVerify() {
        homePage.openLoginForm();
        homePage.iAmCustomerBtnShouldBeVisible();
        homePage.iAmMasterBtnShouldBeVisible();
        homePage.registerLinkShouldBeVisible();
    }

    @Step
    public void selectIamMasterAndVerify() {
        homePage.clickMasterBtn();
        homePage.loginInputShouldBeVisible();
        homePage.passwordInputShouldBeVisible();
        homePage.loginBtnShouldBeVisible();
        homePage.forgotPasswordLinkShouldBeVisible();
        homePage.registerLinkShouldBeVisible();
    }

    @Step
    public void verifyUserIsLoggedIn() {
        homePage.logoutBtnShouldBeVisible();
    }

    @Step
    public void loginErrorWithTextShouldBeVisible(String text) {
        homePage.loginErrorWithTextShouldBeVisible(text);
    }

    @Step
    public void registerAsMaster(User master) {
        homePage.openRegistrationForm();
        homePage.clickMasterBtn();
        homePage.registerFormShouldBeVisible();
        homePage.uploadAvatar();
        homePage.regMasterFormSelectRandomSubDomain();
        homePage.regMasterFormEnterFirstName(master.getFirstName());
        homePage.regMasterFormEnterLastName(master.getLastName());
        homePage.regFormEnterLogin(master.getLogin());
        homePage.regFormEnterPassword(master.getPassword());
        homePage.regMasterFormEnterAboutMe(master.getAboutMe());
        homePage.regMasterFormSelectRandomCategory();
        homePage.regMasterFormEnterPhoneNumber(master.getPhoneNumber());
        homePage.regMasterFormSelectExperience();
        master.setCity(homePage.regMasterFormSelectCity());
        homePage.regFormClickSubmit();
        homePage.registerFormShouldNotBeVisible();
    }

    @Step
    public void clickForgotPassword() {
        homePage.openForgotPasswordForm();
    }

    @Step
    public void requestNewPasswordAtEmail(String email) {
        homePage.forgotPasswordEnterEmail(email);
        homePage.forgotPasswordClickRestoreBtn();
    }

    @Step
    public boolean isCountrySelectorAvailable() {
        return homePage.isCountrySelectorVisible();
    }

    @Step
    public void verifyPhonePopUpText(String text) {
        homePage.clickHeaderPhoneBtn();
        homePage.verifyHeaderPhonePopupText(text);
    }

    @Step
    public void verifyHeaderCountriesListIsVisible() {
        homePage.openHeaderCountriesDropDown();
        homePage.verifyHeaderCountriesPopup();
    }

    @Step
    public void verifyFooterCountriesListIsVisible() {
        homePage.scrollPageToBottom();
        homePage.openFooterCountriesDropDown();
        homePage.verifyFooterCountriesPopup();
    }

    @Step
    public void verifyFooterLanguagesListIsVisible() {
        homePage.scrollPageToBottom();
        homePage.openFooterLangDropDown();
        homePage.verifyFooterLanguagePopup();
    }

    @Step
    public void verifyHeaderLanguagesListIsVisible() {
        homePage.openHeaderLangDropDown();
        homePage.verifyLangPopupIsVisible();
    }

    @Step
    public void verifySubdomainDropDown() {
        homePage.openSubdomainDropDown();
        homePage.verifySubdomainsListIsVisible();
    }

    @Step
    public void verifyLoginForm() {
        open();
        homePage.openLoginForm();
        homePage.iAmCustomerBtnShouldBeVisible();
        homePage.iAmMasterBtnShouldBeVisible();
        homePage.loginInputShouldBeVisible();
        homePage.passwordInputShouldBeVisible();
        homePage.forgotPasswordLinkShouldBeVisible();
        homePage.loginBtnShouldBeVisible();
        homePage.registerLinkShouldBeVisible();
    }

    @Step
    public void verifyRegistrationForm() {
        open();
        homePage.openRegistrationForm();
        homePage.iAmCustomerBtnShouldBeVisible();
        homePage.iAmMasterBtnShouldBeVisible();
    }

    @Step
    public void verifyMobileViewCustomerRegistrationForm() {
        open();
        openMobileViewMainMenu();
        homePage.openMobileViewCustomerRegistrationForm();
        homePage.regFormLoginInputShouldBeVisible();
        homePage.regFormPassInputShouldBeVisible();
        homePage.regFormConfirmPassInputShouldBeVisible();
        homePage.regFormConditionsLinkShouldBeVisible();
        homePage.regFormSubmitBtnShouldBeVisible();
    }

    @Step
    public void verifyPlaceOrderForm() {
        open();
        homePage.openPlaceOrderForm();
        homePage.orderFormUsernameInputShouldBeVisible();
        homePage.orderFormPhoneInputShouldBeVisible();
        homePage.orderFormServiceSelectorShouldBeVisible();
        homePage.orderFormSubmitBtnShouldBeVisible();
    }

    @Step
    public void verifyRandomFaqItem() {
        open();
        homePage.openRandomFaqItem();
        homePage.verifyFaqItemTextIsVisible();
    }

    @Step
    public void verifyMobileViewCountriesMenu() {
        homePage.openMobileViewCountryMenu();
        homePage.verifyMobileViewCountriesMenu();
    }

    @Step
    public void verifyMobileViewLanguageMenu() {
        homePage.openMobileViewLangMenu();
        homePage.verifyMobileViewLanguagesMenu();
    }

    @Step
    public void verifyMobileViewSitesMenu() {
        homePage.openMobileViewSitesMenu();
        homePage.verifyMobileViewSitesMenu();
    }

    @Step
    public void openMobileViewMainMenu() {
        homePage.openMobileViewMainMenu();
    }

    @Step
    public void verifyMobileViewContactsForm(String text) {
        open();
        openMobileViewMainMenu();
        homePage.openMobileViewPhoneForm();
        homePage.verifyMobileViewPhoneFormText(text);
    }
}
