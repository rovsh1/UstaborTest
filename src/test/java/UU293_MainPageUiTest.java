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

        if (!Config.isUstabor() && !Config.isFixListKg()) {
            user.atHomePage.verifyHeaderCountriesListIsVisible();
        }

        if (!Config.isFixListKg()) {
            user.atHomePage.verifySubdomainDropDown();
            user.atHomePage.verifyHeaderLanguagesListIsVisible();
        }

        user.atHomePage.verifyPhonePopUpText(getText("PhoneHintPopupText_" + Config.getEnv()));

        user.atHomePage.verifyLoginForm();
        user.atHomePage.verifyRegistrationForm();
        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.verifyPage();

        user.atHomePage.verifyRandomFaqItem();

        if (!Config.isUstabor() && !Config.isFixListKg()) {
            user.atHomePage.verifyFooterCountriesListIsVisible();
        }

        if (!Config.isFixListKg()) {
            user.atHomePage.verifyFooterLanguagesListIsVisible();
        }

        setBrowserWindowSize(320, 800);
        user.atHomePage.openMobileViewMainMenu();

        if (!Config.isFixListKg()) {
            user.atHomePage.verifyMobileViewSitesMenu();
            user.atHomePage.verifyMobileViewLanguageMenu();
        }

        if (!Config.isUstabor() && !Config.isFixListKg()) {
            user.atHomePage.verifyMobileViewCountriesMenu();
        }

        user.atHomePage.openPlaceOrderPage();
        user.atHomePage.verifyMobileViewCustomerRegistrationForm();
        user.atHomePage.verifyMobileViewContactsForm(getText("PhoneHintPopupText_" + Config.getEnv()));
    }
}
