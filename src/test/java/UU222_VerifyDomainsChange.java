import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.List;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class UU222_VerifyDomainsChange extends TestBase {

    @Test
    public void verifyDomainsChangingWorksCorrectly() {

        if (!Config.isNewTest()) {
            List<String> countriesList = user.atHomePage.getCountriesList();
            for (String country: countriesList) {
                user.atHomePage.setCountryByCode(country);
                user.atHomePage.currentDomainNameShouldBe(country);
            }
        }
    }
}
