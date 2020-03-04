import entities.Master;
import entities.Project;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("test")
})
public class TC006_PromoteWithRecommendedPrice extends TestBase {

    private Project project;

    @Test
    public void promoteWithRecommendedPrice() throws TimeoutException {
        Master master = data.getFullInfoMasterRandomEmail();

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        project = data.getProject(master);

        admin.atAdminHomePage.loginAsAdmin();
        admin.atMastersPage.addMoneyToMaster(1000, master.getLastName());
        admin.atCategoriesPage.enablePromotionAndSetPrice(master.getCategoryId(), "100", "");

        user.atHomePage.open();
        user.atMasterProfilePage.open();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(project, true, false);
        user.atMasterProjectsPage.logsOut();

        admin.atAdminHomePage.loginAsAdmin();
        admin.atPromotionPage.approveProject(project.getSystemId());

        user.atHomePage.open();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(project.getCategory());
        user.atCatalogPage.verifyProjectPromoted(project);
    }

    @After
    public void tearDown() {
        if (project.getSystemId() != null) {
            admin.atAdminHomePage.loginAsAdmin();
            admin.atProjectsPage.deleteProject(project);
        }
    }
}

