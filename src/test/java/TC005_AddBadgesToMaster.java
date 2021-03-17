import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@WithTag("prod")

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters
public class TC005_AddBadgesToMaster extends TestBase {

    @Test
    public void verifyBadgesEnableMasterPromotion() throws TimeoutException {
        var master = DataGenerator.getMasterWithRandomEmail(category);
        watcher.users.add(master);

        user.registerAsMaster(master);
        user.atHomePage.openHomePage();
        user.atHomePage.loginIfNeeded(master);

        user.atMasterProfilePage.openProfilePage();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(master.getCategory());

        admin.atAdminHomePage.loginAsAdmin();
        admin.atMastersPage.addAllBadgesToMaster(master);

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.verifyMasterWithBadgesPromoted(master);
    }
}
