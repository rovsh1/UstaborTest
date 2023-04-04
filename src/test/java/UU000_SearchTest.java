import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@WithTag("smoke")
@RunWith(SerenityRunner.class)
@Ignore
public class UU000_SearchTest extends TestBase {

    @Test
    public void findProfilesWithCategory() {
        user.atCatalogPage.openMastersCatalog();
        user.atCatalogPage.enterTextAndSearch(getText("SearchRequestArch"));
        user.atCatalogPage.verifySelectedCategoryEquals(getText("CategoryArch"));
    }
}
