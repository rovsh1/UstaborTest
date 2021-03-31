package steps;

import entities.Master;
import entities.User;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.HomePage;

import java.util.List;
import java.util.concurrent.TimeoutException;

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
    public void openHomePage() throws TimeoutException {
        homePage.openPageWithConfigUrl();
    }

    @Step
    public void registerAsCustomer(User customer) {
        homePage.openRegistrationForm();
        homePage.clickCustomerBtn();
        homePage.regFormEnterUserName(customer.getFirstName());
        homePage.regFormEnterUserPhone(customer.getPhoneNumber());
        homePage.regFormClickSubmit();
        homePage.waitForLoaderDisappears();
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
    public void loginIfNeeded(User user) {
        if (!homePage.isLoggedIn()) {
            login(user.getEmail(), user.getPassword(), true);
        }
    }

    @Step
    public boolean login(String login, String password, boolean openLoginForm) {
        if (openLoginForm) {
            homePage.openLoginForm();
        }
        homePage.signInFormEnterLogin(login);
        homePage.signInFormEnterPassword(password);
        homePage.signInFormClickLoginBtn();
        return homePage.isLoggedIn();
    }

    @Step
    public boolean login(User user, boolean openLoginForm) {
        return login(user.getEmail(), user.getPassword(), openLoginForm);
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
        homePage.loginInputShouldBeVisible();
        homePage.passwordInputShouldBeVisible();
        homePage.forgotPasswordLinkShouldBeVisible();
        homePage.loginBtnShouldBeVisible();
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
        homePage.regMasterClickSubmit();
    }

    @Step
    public void registerAsMaster(Master master) {
        homePage.openRegistrationForm();
        homePage.clickMasterBtnRegister();
        homePage.registerFormShouldBeVisible();
        homePage.regMasterFormSelectBuildSubDomain();
        homePage.regMasterFormEnterFirstName(master.getFirstName());
        homePage.regMasterFormEnterLastName(master.getLastName());
        homePage.regFormEnterLogin(master.getEmail());
        homePage.regFormEnterPassword(master.getPassword());
        homePage.regMasterFormEnterAboutMe(master.getAboutMe());
        homePage.regMasterFormSelectRandomCategory(master);
        enterSelectExperienceAndCity(master);
        homePage.regMasterClickSubmit();
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
    public void verifyLoginForm() throws TimeoutException {
        openHomePage();
        homePage.openLoginForm();
        homePage.loginInputShouldBeVisible();
        homePage.passwordInputShouldBeVisible();
        homePage.forgotPasswordLinkShouldBeVisible();
        homePage.loginBtnShouldBeVisible();
        homePage.registerLinkShouldBeVisible();
    }

    @Step
    public void verifyRegistrationForm() throws TimeoutException {
        openHomePage();
        homePage.openRegistrationForm();
        homePage.iAmCustomerBtnShouldBeVisible();
        homePage.iAmMasterBtnShouldBeVisible();
    }

    @Step
    public void verifyMobileViewCustomerRegistrationForm() throws TimeoutException {
        openHomePage();
        openMobileViewMainMenu();
        homePage.openMobileViewCustomerRegistrationForm();
        homePage.regFormUserNameInputShouldBeVisible();
        homePage.regFormPhoneNumberInputShouldBeVisible();
        homePage.regFormConditionsLinkShouldBeVisible();
        homePage.regFormSubmitBtnShouldBeVisible();
    }

    @Step
    public void openPlaceOrderPage() throws TimeoutException {
        openHomePage();
        homePage.openPlaceOrderForm();
    }

    @Step
    public void verifyRandomFaqItem() throws TimeoutException {
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
    public void verifyMobileViewContactsForm(String text) throws TimeoutException {
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
    public void logsOut() throws TimeoutException {
        homePage.logsOut();
        homePage.longWaitForDocument();
    }

    public void setLanguage(String lang) {
        homePage.setLanguage(lang);
    }
}
