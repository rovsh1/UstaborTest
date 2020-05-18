package steps;

import entities.Master;
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
    public void openHomePage() {
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
        homePage.waitForLoaderDisappears();
    }

    @Step
    public void openCatalog() {
        homePage.clickCatalogBtn();
    }

    @Step
    public void loginAsMasterIfNeed(String login, String password) {
        if (!homePage.isLoggedIn()) {
            homePage.openLoginForm();
            homePage.clickMasterBtnLogin();
            homePage.signInFormEnterLogin(login);
            homePage.signInFormEnterPassword(password);
            homePage.signInFormClickLoginBtn();
            homePage.loginErrorShouldBeVisible();
            homePage.clickMasterBtnLogin();
            homePage.signInFormClickLoginBtn();
            homePage.waitForLoaderDisappears();
        }
    }

    @Step
    public boolean loginAsMaster(String login, String password, boolean openLoginForm) {
        if (openLoginForm) {
            homePage.openLoginForm();
        }
        homePage.clickMasterBtnLogin();
        homePage.signInFormEnterLogin(login);
        homePage.signInFormEnterPassword(password);
        homePage.signInFormClickLoginBtn();
        return homePage.isLoggedIn();
    }

    @Step
    public boolean loginAsCustomer(String login, String password) {
        homePage.openLoginForm();
        homePage.clickCustomerBtn();
        homePage.signInFormEnterLogin(login);
        homePage.signInFormEnterPassword(password);
        homePage.signInFormClickLoginBtn();
        return homePage.isLoggedIn();
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
    public void setCountry(String country) {
        homePage.setCountry(country);
    }

    @Step
    public void setCountryByCode(String countryCode) {
        homePage.setCountryByCode(countryCode);
    }

    @Step
    public void currentDomainNameShouldBe(String country) {
        homePage.currentDomainNameShouldBe(country);
    }

    @Step
    public void homePageShouldBeVisible() {
        homePage.openLoginFormBtnShouldBeVisible();
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
        homePage.clickMasterBtnLogin();
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
    public void enterSelectExperienceAndCity(Master master) {
        homePage.regMasterFormEnterPhoneNumber(master.getPhoneNumber());
        homePage.regMasterFormSelectExperience();
        master.setCity(homePage.regMasterFormSelectCity());
    }

    @Step
    public void registerAsMasterWithSpecifiedCategory(Master master) {
        homePage.openRegistrationForm();
        homePage.clickMasterBtnRegister();
        //homePage.uploadAvatar();
        homePage.regMasterFormSelectBuildSubDomain();
        homePage.regMasterFormEnterFirstName(master.getFirstName());
        homePage.regMasterFormEnterLastName(master.getLastName());
        homePage.regFormEnterLogin(master.getEmail());
        homePage.regFormEnterPassword(master.getPassword());
        homePage.regMasterFormEnterAboutMe(master.getAboutMe());
        homePage.regMasterFormSelectCategory(master);
        homePage.regMasterFormEnterPhoneNumber(master.getPhoneNumber());
        homePage.regMasterFormSelectExperience();
        master.setCity(homePage.regMasterFormSelectCity());
        homePage.regFormClickSubmit();
    }

    @Step
    public void registerAsMaster(Master master) {
        homePage.openRegistrationForm();
        homePage.clickMasterBtnRegister();
        homePage.registerFormShouldBeVisible();
//        homePage.uploadAvatar();
        homePage.regMasterFormSelectBuildSubDomain();
        homePage.regMasterFormEnterFirstName(master.getFirstName());
        homePage.regMasterFormEnterLastName(master.getLastName());
        homePage.regFormEnterLogin(master.getEmail());
        homePage.regFormEnterPassword(master.getPassword());
        homePage.regMasterFormEnterAboutMe(master.getAboutMe());
        homePage.regMasterFormSelectRandomCategory(master);
        enterSelectExperienceAndCity(master);
        homePage.regFormClickSubmit();
//        homePage.registerFormShouldNotBeVisible();
    }

    @Step
    public void registerAsMasterWithCategory(Master master, String category) {
        homePage.openRegistrationForm();
        homePage.clickMasterBtnRegister();
        homePage.registerFormShouldBeVisible();
        //homePage.uploadAvatar();
        homePage.regMasterFormSelectBuildSubDomain();
        homePage.regMasterFormEnterFirstName(master.getFirstName());
        homePage.regMasterFormEnterLastName(master.getLastName());
        homePage.regFormEnterLogin(master.getEmail());
        homePage.regFormEnterPassword(master.getPassword());
        homePage.regMasterFormEnterAboutMe(master.getAboutMe());
        homePage.regMasterFormSelectCategory(master, category);
        homePage.regMasterFormEnterPhoneNumber(master.getPhoneNumber());
        homePage.regMasterFormSelectExperience();
        master.setCity(homePage.regMasterFormSelectCity());
        homePage.regFormClickSubmit();
//        homePage.registerFormShouldNotBeVisible();
    }

    @Step
    public void clickForgotPassword() {
        homePage.openForgotPasswordForm();
    }

    @Step
    public void requestNewPasswordAtEmail(String email) {
        homePage.forgotPasswordEnterEmail(email);
        homePage.forgotPasswordClickRestoreBtn();
        homePage.waitForLoaderDisappears();
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
        openHomePage();
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
        openHomePage();
        homePage.openRegistrationForm();
        homePage.iAmCustomerBtnShouldBeVisible();
        homePage.iAmMasterBtnShouldBeVisible();
    }

    @Step
    public void verifyMobileViewCustomerRegistrationForm() {
        openHomePage();
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
        openHomePage();
        homePage.openPlaceOrderForm();
        homePage.orderFormUsernameInputShouldBeVisible();
        homePage.orderFormPhoneInputShouldBeVisible();
        homePage.orderFormServiceSelectorShouldBeVisible();
        homePage.orderFormSubmitBtnShouldBeVisible();
    }

    @Step
    public void verifyRandomFaqItem() {
        openHomePage();
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
        openHomePage();
        openMobileViewMainMenu();
        homePage.openMobileViewPhoneForm();
        homePage.verifyMobileViewPhoneFormText(text);
    }

    @Step
    public void openBuilderTab() {
        homePage.clickBuilderTab();
    }

    @Step
    public void openCategory(String category) {
        homePage.openCategory(category);
    }

    @Step
    public void logsOut() {
        homePage.logsOut();
    }

    public void setLanguage(String lang) {
        homePage.setLanguage(lang);
    }
}
