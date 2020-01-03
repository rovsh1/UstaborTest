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
    public void search(String text) {
        enterSearchText(text);
        homePage.ClickSearchBtn();
    }

    @Step
    public void open() {
        homePage.openPageWithConfigUrl();
    }

    @Step
    public void registerAsCustomer(String login, String password) {
        homePage.clickRegistrationBtn();
        homePage.clickCustomerBtn();
        homePage.enterRegistrationLogin(login);
        homePage.enterRegistrationPassword(password);
        homePage.sendRegisterForm();
    }

    @Step
    public void enterAuthCodeAndSubmit(String code) {
        homePage.enterAuthCode(code);
        homePage.clickSubmitAuthCode();
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
        homePage.enterLogin(login);
        homePage.enterPassword(password);
        homePage.clickLoginBtn();
        return homePage.isLoginSuccessful();
    }

    @Step
    public boolean loginAsCustomer(String login, String password) {
        homePage.openLoginForm();
        homePage.clickCustomerBtn();
        homePage.enterLogin(login);
        homePage.enterPassword(password);
        homePage.clickLoginBtn();
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
        homePage.clickRegistrationBtn();
        homePage.clickMasterBtn();
        homePage.registerFormShouldBeVisible();
        homePage.uploadAvatar();
        homePage.selectRandomSubDomain();
        homePage.enterRegistrationName(master.getFirstName());
        homePage.enterRegistrationSurname(master.getLastName());
        homePage.enterRegistrationLogin(master.getLogin());
        homePage.enterRegistrationPassword(master.getPassword());
        homePage.enterAboutMe(master.getAboutMe());
        homePage.selectRandomCategory();
        homePage.enterPhoneNumber(master.getPhoneNumber());
        homePage.selectExperience();
        master.setCity(homePage.selectCity());
        homePage.sendRegisterForm();
        homePage.registerFormShouldNotBeVisible();
    }

    @Step
    public void clickForgotPassword() {
        homePage.clickForgotPassword();
    }

    @Step
    public void requestNewPasswordAtEmail(String email) {
        homePage.forgotPasswordEnterEmail(email);
        homePage.clickRestoreBtn();
    }

    @Step
    public boolean isCountrySelectorAvailable() {
        return homePage.isCountrySelectorVisible();
    }
}
