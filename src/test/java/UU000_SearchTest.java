import annotations.AddCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
@AddCategory(addTags = true)
public class UU000_SearchTest extends TestBase {

    @Test
    public void findProfilesWithCategory() throws TimeoutException {
        user.atHomePage.openHomePage();
        user.atHomePage.enterTextAndSearch(getText("Tag"));
        user.atPlaceOrderPage.verifyDomain(getText("SiteDomainBuild_Short2"));
        user.atPlaceOrderPage.verifyCategory(category.getName());

        user.atHomePage.openHomePage();
        var tag = getText("Tag_" + Config.getEnv());
        var domain = getText("Domain_" + Config.getEnv());
        var category = getText("Category_" + Config.getEnv());

        user.atHomePage.enterTextAndSearch(tag);
        user.atPlaceOrderPage.verifyDomain(domain);
        user.atPlaceOrderPage.verifyCategory(category);
    }
}
