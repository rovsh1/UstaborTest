package steps;

import entities.Master;
import entities.User;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.HomePage;
import utils.Admin;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class HomePageSteps extends ScenarioSteps {

    private HomePage homePage;

    @Step
    public void openHomePage() throws TimeoutException {
        homePage.openPageWithConfigUrl();
    }

    @Step
    public void registerAsCustomer(User customer) {
        homePage.openRegistrationForm();
        homePage.clickCustomerBtn();
        homePage.regFormEnterUserName(customer.getFirstName());

        customer.setPhoneCode(homePage.getCustomerPhoneCountryCode());

        homePage.regFormEnterUserPhone(customer.getPhoneNumber());
        homePage.regFormClickSubmit();
        homePage.waitForSubmitCodeForm();
    }

    @Step
    public void enterAuthCodeAndSubmit(String code, String phoneNumber) {
        homePage.regFormEnterAuthCode(code);
        homePage.regFormClickSubmitAuthCode();

        if (homePage.isRefreshLinkVisible()) {
            retryEnterCode(phoneNumber);
        }

        homePage.waitForLoaderDisappears();
    }

    @Step
    public void retryEnterCode(String phoneNumber) {
        homePage.resendCode();
        homePage.waitForLoaderDisappears();
        var smsCode = Admin.getInstance().getSmsCode(phoneNumber);
        homePage.regFormEnterAuthCode(smsCode);
        homePage.regFormClickSubmitAuthCode();
    }

    @Step
    public void loginIfNeeded(User user) {
        if (!homePage.isLoggedIn()) {
            login(user.getLogin(), user.getPassword(), true);
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
        homePage.waitForLoaderDisappears();
        return homePage.isLoggedIn();
    }

    @Step
    public boolean login(User user, boolean openLoginForm) {
        return login(user.getLogin(), user.getPassword(), openLoginForm);
    }

    @Step
    public void waitForFeedbackProposalAndOpen() {
        homePage.waitUntilFeedbackPopupIsVisible();
        homePage.clickLeaveFeedback();
    }

    @Step
    public void selectSuggestionCategoryAndSearch(String suggestion) {
        homePage.suggestionListShouldBeVisible();
        homePage.selectSuggestion(suggestion);
    }

    @Step
    public List<String> getCountriesList() {
        return homePage.getCountries();
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
    public void openLoginFormAndVerify() {
        homePage.openLoginForm();
        homePage.loginInputShouldBeVisible();
        homePage.passwordInputShouldBeVisible();
        homePage.forgotPasswordLinkShouldBeVisible();
        homePage.loginBtnShouldBeVisible();
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
    public void registerAsMaster(Master master, boolean randomCategory) {
        homePage.openRegistrationForm();
        homePage.clickMasterBtnRegister();
        homePage.regMasterFormEnterFirstName(master.getFirstName());
        homePage.regMasterFormEnterLastName(master.getLastName());
        homePage.regMasterFormEnterPhoneNumber(master.getPhoneNumber());
        master.setPhoneCode(homePage.getMasterPhoneCountryCode());
        homePage.regFormEnterPassword(master.getPassword());

        homePage.regMasterClickSubmit();

        homePage.regMasterFormEnterAboutMe(master.getAboutMe());
        homePage.regMasterFormSelectExperience();
        master.setCity(homePage.regMasterFormSelectCity());

        homePage.regMasterClickSubmit();

        homePage.regMasterFormSelectBuildSubDomain();

        if (randomCategory) {
            homePage.regMasterFormSelectRandomCategory(master);
        } else {
            homePage.regMasterFormSelectCategory(master);
        }

        homePage.regMasterClickSubmit();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        homePage.waitForSubmitCodeForm();
    }

    @Step
    public void clickForgotPassword() {
        homePage.openForgotPasswordForm();
    }

    @Step
    public void requestNewPassword(String phoneNumber) {
        homePage.forgotPasswordEnterPhone(phoneNumber);
        homePage.forgotPasswordClickRestoreBtn();
        homePage.waitForLoaderDisappears();
    }

    @Step
    public void verifyPhonePopUpText(String text) {
        homePage.clickHeaderPhoneBtn();
        homePage.verifyHeaderPhonePopupText(text);
    }

    @Step
    public void verifyFooterCountriesListIsVisible() {
        homePage.scrollPageToBottom();
        homePage.openFooterCountriesDropDown();
        homePage.verifyFooterCountriesPopup();
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
    public void openMobileViewMainMenu() {
        homePage.openMobileViewMainMenu();
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

    @Step
    public void resendCode() {
        homePage.resendCode();
    }

    @Step
    public void setLanguage(String lang) {
        homePage.setLanguage(lang);
    }

    public void waitForLoaderDisappears() {
        homePage.waitForLoaderDisappears();
    }

    @Step
    public void openLocationPopupAndVerifyCountries() {
        homePage.openLocationPopup();
        homePage.verifyHeaderCountriesPopup();
        homePage.clickCloseBtn();
    }

    @Step
    public void selectLocation(String country, String city) {
        homePage.selectLocation(country, city);
    }

    @Step
    public void selectCity(String city) {
        homePage.selectCity(city);
    }
}
