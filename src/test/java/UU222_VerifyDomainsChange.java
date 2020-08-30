import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
@Ignore
public class UU222_VerifyDomainsChange extends TestBase {

    @Test
    public void verifyDomainsChangingWorksCorrectly() {

        if (user.atHomePage.isCountrySelectorAvailable()) {
            List<String> countriesList = user.atHomePage.getCountriesList();
            for (String country: countriesList) {
                user.atHomePage.setCountryByCode(country);
                user.atHomePage.currentDomainNameShouldBe(country);
            }
        }
    }
}
