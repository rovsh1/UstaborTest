import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UU293_MainPageUiTest extends TestBase {

    @Test
    public void mainPageUiTest() {

        user.atHomePage.verifyHeaderCountriesListIsVisible();
        user.atHomePage.verifySubdomainDropDown();
        user.atHomePage.verifyPhonePopUpText(getText("PhoneHintPopupText"));
        user.atHomePage.verifyHeaderLanguagesListIsVisible();

        user.atHomePage.verifyLoginForm();
        user.atHomePage.verifyRegistrationForm();
        user.atHomePage.verifyPlaceOrderForm();

        user.atHomePage.verifyRandomFaqItem();

        user.atHomePage.verifyFooterCountriesListIsVisible();
        user.atHomePage.verifyFooterLanguagesListIsVisible();

        setBrowserWindowSize(320, 800);
        user.atHomePage.openMobileViewMainMenu();
        user.atHomePage.verifyMobileViewSitesMenu();
        user.atHomePage.verifyMobileViewCountriesMenu();
        user.atHomePage.verifyMobileViewLanguageMenu();

        user.atHomePage.verifyPlaceOrderForm();
        user.atHomePage.verifyMobileViewCustomerRegistrationForm();

        user.atHomePage.verifyMobileViewContactsForm(getText("PhoneHintPopupText"));
    }
}
