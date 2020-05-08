package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import utils.XmlParser;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BasePage extends PageObject {

    private String loaderXpath = "//div[contains(@class, 'loading')]";

    //region Header elements
    @FindBy(xpath = "//div[@class='header']//a[@class='logo']")
    private WebElementFacade logoBtn;

    @FindBy(xpath = "//a[contains(@href, 'profile')]")
    private WebElementFacade profileBtn;

    @FindBy(xpath = "//a[contains(@href, 'profile/logout')]")
    private WebElementFacade logoutBtn;

    @FindBy(xpath = "//a[@href='#signInForm']")
    private WebElementFacade openLoginFormBtn;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu sites']")
    private WebElementFacade sitesMenuBtn;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu sites']/div[@class='menu']/a")
    private List<WebElementFacade> sitesList;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu countries']/div[contains(@class, 'label')]")
    private WebElementFacade headerCountriesBtn;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu countries']//a")
    private List<WebElementFacade> countriesList;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade popupOkBtn;

    @FindBy(xpath = "//div[@id='header-phone-button']")
    private WebElementFacade phoneBtn;

    @FindBy(xpath = "//div[@class='header-phone']//div[@class='hint']")
    private WebElementFacade phonePopup;

    @FindBy(xpath = "//div[@class='header-menu']//nav[@class='nav-menu language']/div[contains(@class, 'label')]")
    private WebElementFacade headerLangBtn;

    @FindBy(xpath = "//div[@class='header-menu']//nav[@class='nav-menu language']//a")
    private List<WebElementFacade> langsList;

    @FindBy(xpath = "//div[@class='header-menu']//button[@id='button-order']")
    private WebElementFacade placeOrderBtn;
    //endregion

    //region Footer elements
    @FindBy(xpath = "//div[@class='footer']//nav[@class='nav-menu language']//div[contains(@class, 'label')]")
    private WebElementFacade footerLangBtn;

    @FindBy(xpath = "//div[@class='footer']//nav[@class='nav-menu language']//a")
    private List<WebElementFacade> footerLangsList;

    @FindBy(xpath = "//div[@class='footer']//nav[@class='nav-menu countries']//div[contains(@class, 'label')]")
    private WebElementFacade footerCountryBtn;

    @FindBy(xpath = "//div[@class='footer']//nav[@class='nav-menu countries']//a")
    private List<WebElementFacade> footerCountriesList;
    //endregion

    //region Mobile view elements
    @FindBy(xpath = "//div[@class='header']//div[@id='button-sitemap']")
    private WebElementFacade mobileViewMenuBtn;
    //endregion

    @FindBy(xpath = "//nav[@class='footer']/a[contains(@href,'/sitemap/')]")
    private WebElementFacade siteMapLink;

    public void setTimeouts(int duration, TemporalUnit timeUnit) {
        setImplicitTimeout(duration, timeUnit);
        setWaitForTimeout(duration * 1000);
    }

    public void resetTimeouts() {
        resetImplicitTimeout();
        setWaitForTimeout(15000);
    }

    public void waitForLoaderDisappears() {
        if (Config.isChrome()) {
            withTimeoutOf(25, ChronoUnit.SECONDS)
                    .waitForCondition()
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loaderXpath)));
        }
    }

    public void openPageWithConfigUrl() {
        getDriver().get(Config.getFullUrl());
    }

    public void goToUrl(String url) {
        getDriver().get(url);
    }

    public void openHomePage() {
        logoutBtn.waitUntilClickable();
        logoBtn.click();
    }

    public void openProfilePage() {
        getDriver().get(Config.getFullUrl() + "profile");
    }

    public void logsOut() {
        logoutBtn.waitUntilClickable();
        logoutBtn.click();
    }

    boolean isLogoutBtnVisible() {
        setTimeouts(1, ChronoUnit.SECONDS);
        boolean result = logoutBtn.isVisible();
        resetTimeouts();
        return result;
    }

    public void logoutBtnShouldBeVisible() {
        assertThat(isLogoutBtnVisible()).isTrue();
    }

    protected void clickPopupOkBtn() {
        popupOkBtn.click();
    }

    void focusElementJS(String className, int elementCount) {
        evaluateJavascript(
                "document.getElementsByClassName(arguments[0])[arguments[1]].scrollIntoView(false)",
                className,
                elementCount);
    }

    protected void focusElementJS(WebElementFacade element) {
        evaluateJavascript("arguments[0].focus();", element);
    }

    protected void scrollPageUpJS() {
        evaluateJavascript("window.scrollTo(0, 0)");
    }

    void clearInputJS(WebElementFacade input) {
        evaluateJavascript("arguments[0].value = ''", input);
    }

    void clickElementJS(WebElementFacade element) {
        evaluateJavascript("arguments[0].click();", element);
    }

    public List<String> getSitesNames() {
        sitesMenuBtn.click();
        return sitesList.stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    public void openSubdomainDropDown() {
        sitesMenuBtn.click();
    }

    public void openSiteWithName(String siteName) {
        openSubdomainDropDown();
        WebElementFacade site = sitesList
                .stream()
                .filter(s -> s.getText().contains(siteName))
                .findFirst()
                .orElse(null);

        if (site == null) {
            throw new NullPointerException();
        }

        site.click();
    }

    public void correctSiteShouldBeVisible(String siteName) {
        String expectedUrl = Config.getFullUrl();
        if (expectedUrl.endsWith("/ru/")) {
            expectedUrl = expectedUrl.replace("/ru/", "");
        }

        if (siteName.equals(Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainBuild_Short2"))) ||
                siteName.equals(Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainBuild_Full")))) {
            assertThat(getDriver().getCurrentUrl()).contains(expectedUrl);
        } else if (siteName.contains(
                Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainAuto")))) {
            assertThat(getDriver().getCurrentUrl()).contains("//auto.");
        } else if (siteName.contains(
                Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainTech")))) {
            assertThat(getDriver().getCurrentUrl()).contains("//tech.");
        } else if (siteName.contains(
                Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainHome")))) {
            assertThat(getDriver().getCurrentUrl()).contains("//home.");
        } else if (siteName.contains(
                Objects.requireNonNull(XmlParser.getTextByKey("SiteDomainMaterials")))) {
            assertThat(getDriver().getCurrentUrl()).contains("//materials.");
        } else {
            throw new IllegalArgumentException(String.format("No such site with name %s found", siteName));
        }
        logoBtn.shouldBeVisible();
    }

    public void openSiteMap() {
        siteMapLink.click();
    }

    public void openHeaderCountriesDropDown() {
        headerCountriesBtn.click();
    }

    public void openFooterCountriesDropDown() {
        footerCountryBtn.click();
    }

    public List<String> getCountries() {
        openHeaderCountriesDropDown();
        return countriesList
                .stream()
                .map(x -> x.getAttribute("class"))
                .map(x -> x.substring(0, x.lastIndexOf(" ")))
                .collect(Collectors.toList());
    }

    public void setCountry(String country) {
        if (!isCountrySelectorVisible() || headerCountriesBtn.getText().equals(country)) {
            return;
        }

        openHeaderCountriesDropDown();
        WebElementFacade item = countriesList
                .stream()
                .filter(s -> s.getText().equals(country))
                .findFirst()
                .orElse(null);

        if (item == null) {
            throw new NullPointerException();
        }

        item.click();
    }

    public void setCountryByCode(String countryCode) {
        if (!isCountrySelectorVisible() || headerCountriesBtn.getAttribute("class").contains(countryCode)) {
            return;
        }

        openHeaderCountriesDropDown();
        WebElementFacade item = countriesList
                .stream()
                .filter(s -> s.getAttribute("class").contains(countryCode))
                .findFirst()
                .orElse(null);

        if (item == null) {
            throw new NullPointerException();
        }

        item.click();
    }

    public void currentDomainNameShouldBe(String country) {
        assertThat(headerCountriesBtn.getAttribute("class")).contains(country);
    }

    public void clickProfileBtn() {
        profileBtn.click();
    }

    public boolean isOpenLoginFormBtnVisible() {
        return openLoginFormBtn.isVisible();
    }

    public void openLoginFormBtnShouldBeVisible() {
        openLoginFormBtn.shouldBeVisible();
    }

    public void openLoginForm() {
        openLoginFormBtn.click();
    }

    public boolean isCountrySelectorVisible() {
        setTimeouts(1, ChronoUnit.SECONDS);
        boolean result =  headerCountriesBtn.isCurrentlyVisible();
        resetTimeouts();
        return result;
    }

    public void clickHeaderPhoneBtn() {
        phoneBtn.click();
    }

    public void verifyHeaderPhonePopupText(String text) {
        phonePopup.shouldBeVisible();
        phonePopup.shouldContainText(text);
    }

    public void verifyHeaderCountriesPopup() {
        countriesList.forEach(WebElementState::shouldBeVisible);
    }

    public void openHeaderLangDropDown() {
        headerLangBtn.click();
    }

    public String getCurrentLang() {
        return headerLangBtn.getText();
    }

    public void openFooterLangDropDown() {
        footerLangBtn.click();
    }

    public void verifyLangPopupIsVisible() {
        langsList.forEach(WebElementState::shouldBeVisible);
    }

    public void selectLanguage(String lang) {
        var language = langsList.stream().filter(x -> x.getText().contains(lang)).findFirst().orElse(null);
        language.click();
    }

    public void verifySubdomainsListIsVisible() {
        sitesList.forEach(WebElementState::shouldBeVisible);
    }

    public void openPlaceOrderForm() {
        placeOrderBtn.click();
    }

    public void verifyFooterCountriesPopup() {
        footerCountriesList.forEach(WebElementState::shouldBeVisible);
    }

    public void verifyFooterLanguagePopup() {
        footerLangsList.forEach(WebElementState::shouldBeVisible);
    }

    public void scrollPageToBottom() {
        evaluateJavascript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void openMobileViewMainMenu() {
        mobileViewMenuBtn.click();
    }
}
