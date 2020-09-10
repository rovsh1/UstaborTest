import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class UU293_MainPageUiTest extends TestBase {

    @Test
    public void mainPageUiTest() throws TimeoutException {

        if (!Config.isUstabor()) {
            user.atHomePage.verifyHeaderCountriesListIsVisible();
        }

        user.atHomePage.verifySubdomainDropDown();
        user.atHomePage.verifyPhonePopUpText(getText("PhoneHintPopupText_" + Config.getEnv()));
        user.atHomePage.verifyHeaderLanguagesListIsVisible();

        user.atHomePage.verifyLoginForm();
        user.atHomePage.verifyRegistrationForm();
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.verifyPage();

        user.atHomePage.verifyRandomFaqItem();

        if (!Config.isUstabor()) {
            user.atHomePage.verifyFooterCountriesListIsVisible();
        }
        user.atHomePage.verifyFooterLanguagesListIsVisible();

        setBrowserWindowSize(320, 800);
        user.atHomePage.openMobileViewMainMenu();
        user.atHomePage.verifyMobileViewSitesMenu();
        if (!Config.isUstabor()) {
            user.atHomePage.verifyMobileViewCountriesMenu();
        }
        user.atHomePage.verifyMobileViewLanguageMenu();

        user.atHomePage.openPlaceOrderPage();
        user.atHomePage.verifyMobileViewCustomerRegistrationForm();

        user.atHomePage.verifyMobileViewContactsForm(getText("PhoneHintPopupText_" + Config.getEnv()));
    }
}
