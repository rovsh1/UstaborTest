import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
public class TC005_AddBadgesToMaster extends TestBase {

    private Master master;

    @Test
    public void addBadgesToMaster() throws TimeoutException, InterruptedException {
        master = data.getFullInfoMasterRandomEmail();
        user.atHomePage.registerAsMaster(master);

        master.setProfileId(user.atMasterProfilePage.getProfileId());
        var project = data.getProject(master);

        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(project, false, false);

        user.atHomePage.openHomePage();
        user.atHomePage.enterTextAndSearch(project.getName());
        user.atCatalogPage.verifyFoundProject(project.getSystemId());
        user.atCatalogPage.openProjectBySystemId(project.getSystemId());
        user.atProjectPage.verifyProjectInfo(project, master);

        admin.atAdminHomePage.loginAsAdmin();
        admin.atMastersPage.addAllBadgesToMaster(master);

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(project.getCategory());
        user.atCatalogPage.verifyProjectsWithBadge(project, master);
    }

    @After
    public void tearDown() {
        admin.atAdminHomePage.loginAsAdmin();
        if (master.getProfileId() != null) {
            admin.atMastersPage.deleteMaster(master);
        }
    }
}
