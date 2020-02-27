import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("test"),
        @WithTag("prod"),
        @WithTag("full")
})
public class UU000_SearchTest extends TestBase {

    @Test
    public void FindProfilesWithCategory() {
        user.atHomePage.enterTextAndSearch(getText("SearchRequestArch"));
        user.atCatalogPage.verifySelectedCategoryEquals(getText("CategoryArch"));
        user.atCatalogPage.verifyAllFoundProjectsHaveCategory(getText("CategoryArch"));

    }
}
