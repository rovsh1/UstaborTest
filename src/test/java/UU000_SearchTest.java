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
        user.atHomePage.search(getText("SearchRequestArch"));
        user.atCatalogPage.verifySelectedCategoryEquals("test");
        user.atCatalogPage.verifyAllFoundProjectsHaveCategory(getText("CategoryArch"));

    }
}
