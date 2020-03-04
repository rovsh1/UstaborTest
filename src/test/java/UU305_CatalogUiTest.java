import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

@WithTags({
        @WithTag("mobile"),
        @WithTag("desktop"),
        @WithTag("interface")
})
@RunWith(SerenityRunner.class)
public class UU305_CatalogUiTest extends TestBase {

    @Test
    public void catalogUiTest() {
        if (Config.isMobileTag()) {
            setBrowserWindowSize(320, 800);
        }

        user.atHomePage.openCatalog();

        user.atCatalogPage.openFilterAndVerify();

        user.atCatalogPage.openFilterCategoriesAndVerify();
        user.atCatalogPage.closeFilterCategoryWindow();

        user.atCatalogPage.openFilterCityDropdownAndVerify();

        Master master = user.atCatalogPage.openRandomProjectAndGetMasterInfo();
        user.atProjectPage.verifyProjectPageByMasterInfo(master);

        browserGoBack();

        Master master1 = user.atCatalogPage.openRandomMasterProfile();
        user.atMasterProfilePage.verifyProfilePage(master1);

    }
}
