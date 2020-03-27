import entities.Master;
import entities.Project;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("test")
})
public class TC005_AddBadgesToMaster extends TestBase {

    private Master master;

    @Test
    public void addBadgesToMaster() throws TimeoutException, InterruptedException {
        Master master = data.getFullInfoMasterRandomEmail();
        user.atHomePage.registerAsMaster(master);
        master.setProfileId(user.atMasterProfilePage.getProfileId());

        var project = data.getProject(master);

        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(project, false, false);

        user.atHomePage.open();
        user.atHomePage.enterTextAndSearch(project.getName());
        user.atCatalogPage.verifyFoundProject(project.getSystemId());
        user.atCatalogPage.openProjectBySystemId(project.getSystemId());
        user.atProjectPage.verifyProjectInfo(project, master);

        user.atHomePage.open();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(project.getCategory());
        user.atCatalogPage.loadAllResults();
        user.atCatalogPage.verifyLastAddedProject(project);

        admin.atAdminHomePage.loginAsAdmin();
        admin.atMastersPage.addAllBadgesToMaster(master.getLastName());

        user.atHomePage.open();
        user.atHomePage.openCatalog();
        user.atCatalogPage.verifyProjectsWithBadge(project.getSystemId());
    }

    @After
    public void tearDown() {
        admin.atAdminHomePage.loginAsAdmin();
        if (master.getProfileId() != null) {
            admin.atMastersPage.deleteMaster(master);
        }
    }
}
