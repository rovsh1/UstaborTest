package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;
import utils.XmlParser;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BasePage extends PageObject {

    //region Header elements

    @FindBy(xpath = "//div[@class='header']//a[@class='logo']")
    private WebElementFacade logoBtn;

    @FindBy(xpath = "//a[@href='/profile/']")
    private WebElementFacade profileBtn;

    @FindBy(xpath = "//a[@href='/profile/logout/']")
    private WebElementFacade logoutBtn;

    @FindBy(xpath = "//a[@href='#signInForm']")
    private WebElementFacade openLoginFormBtn;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu sites']")
    private WebElementFacade sitesMenuBtn;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu sites']/div[@class='menu']/a")
    private List<WebElementFacade> sitesList;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu countries']/div[contains(@class, 'label')]")
    private WebElementFacade countriesMenuBtn;

    @FindBy(xpath = "//div[@class='header']//nav[@class='nav-menu countries']//a")
    private List<WebElementFacade> countriesList;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade popupOkBtn;

    //endregion

    @FindBy(xpath = "//nav[@class='footer']/a[@href='/sitemap/']")
    private WebElementFacade siteMapLink;

    @FindBy(xpath = "//div[contains(@class, 'loading')]")
    private WebElementFacade loader;

    public void waitForLoaderDisappears() {
        loader.withTimeoutOf(Duration.ofSeconds(15)).waitUntilNotVisible();
    }

    public void openPageWithConfigUrl() {
        getDriver().get(Config.getFullUrl());
    }

    public void goToUrl(String url) {
        getDriver().get(url);
    }

    public void openHomePage() {
        logoBtn.click();
    }

    public void openProfilePage() {
        profileBtn.click();
    }

    public void logsOut() {
        logoutBtn.click();
    }

    boolean isLogoutBtnVisible() {
        setImplicitTimeout(2, ChronoUnit.SECONDS);
        boolean result = logoutBtn.isVisible();
        resetImplicitTimeout();
        return result;
    }

    public void logoutBtnShouldBeVisible() {
        assertThat(isLogoutBtnVisible()).isTrue();
    }

    void clickPopupOkBtn() {
        popupOkBtn.click();
    }

    void focusElementJS(String className, int elementCount) {
        evaluateJavascript(
                "document.getElementsByClassName(arguments[0])[arguments[1]].scrollIntoView(false)",
                className,
                elementCount);
    }

    void scrollPageUpJS() {
        evaluateJavascript("window.scrollTo(0, 0)");
    }

    void clearInputJS(WebElementFacade input) {
        evaluateJavascript("arguments[0].value = ''", input);
    }

    public List<String> getSitesNames() {
        sitesMenuBtn.click();
        return sitesList.stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    public void openSiteWithName(String siteName) {
        sitesMenuBtn.click();
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

    public List<String> getCountries() {
        countriesMenuBtn.click();
        return countriesList
                .stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    public void selectCountryWithName(String country) {
        if (!isCountrySelectorVisible() || countriesMenuBtn.getText().equals(country)) {
            return;
        }

        countriesMenuBtn.click();
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

    public void currentDomainNameShouldBe(String country) {
        assertThat(countriesMenuBtn.getText()).isEqualTo(country);
    }

    public void clickProfileBtn() {
        profileBtn.click();
    }

    public boolean isOpenLoginFormBtnVisible() {
        return openLoginFormBtn.isVisible();
    }

    public void openLoginForm() {
        openLoginFormBtn.click();
    }

    public void reloadPage() {
        getDriver().navigate().refresh();
    }

    public boolean isCountrySelectorVisible() {
        setImplicitTimeout(2, ChronoUnit.SECONDS);
        boolean result =  countriesMenuBtn.isCurrentlyVisible();
        resetImplicitTimeout();
        return result;
    }
}
