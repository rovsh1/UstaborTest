import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
public class UU000_SearchTest extends TestBase {

    @Test
    public void FindProfilesWithCategory() {
        user.atHomePage.enterTextAndSearch(getText("SearchRequestArch"));
        user.atCatalogPage.verifySelectedCategoryEquals(getText("CategoryArch"));
        user.atCatalogPage.verifyAllFoundProjectsHaveCategory(getText("CategoryArch"));

    }
}
