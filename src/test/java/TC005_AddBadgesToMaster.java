import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@WithTag("prod")

@RunWith(SerenityRunner.class)
public class TC005_AddBadgesToMaster extends ProdTestBase {

    @Test
    public void addBadgesToMaster() throws TimeoutException, InterruptedException {
        var master = data.getMasterRandomEmail(category);
        watcher.masters.add(master);

        user.registerAsMaster(master);
        admin.enablePromotion(master);

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMasterIfNeed(master.getEmail(), master.getPassword());

        user.atMasterProfilePage.open();
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
