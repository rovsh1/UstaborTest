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
    public void verifyBadgesEnableMasterPromotion() throws TimeoutException, InterruptedException {
        var master = DataGenerator.getMasterWithRandomEmail(category);
        watcher.users.add(master);

        user.registerAsMaster(master);
        user.atHomePage.openHomePage();
        user.atHomePage.loginIfNeeded(master);

        user.atMasterProfilePage.openProfilePage();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(master.getProject(), false, false);

        user.atHomePage.openHomePage();
        user.atHomePage.enterTextAndSearch(master.getProject().getName());
        user.atCatalogPage.verifyFoundProject(master.getProject().getSystemId());
        user.atCatalogPage.openProjectBySystemId(master.getProject().getSystemId());
        user.atProjectPage.verifyProjectInfo(master.getProject(), master);

        admin.atAdminHomePage.loginAsAdmin();
        admin.atMastersPage.addAllBadgesToMaster(master);

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getProject().getCategory());
        user.atCatalogPage.verifyProjectsWithBadge(master.getProject(), master);
    }
}
