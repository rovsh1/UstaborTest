import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;

import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("test"),
        @WithTag("prod"),
        @WithTag("full")
})
public class UU222_VerifyDomainsChange extends TestBase {

    @Test
    public void verifyDomainsChangingWorksCorrectly() {

        if (user.atHomePage.isCountrySelectorAvailable()) {
            List<String> countriesList = user.atHomePage.getCountriesList();
            for (String country: countriesList) {
                user.atHomePage.setCountry(country);
                user.atHomePage.currentDomainNameShouldBe(country);
            }
        }
    }
}
