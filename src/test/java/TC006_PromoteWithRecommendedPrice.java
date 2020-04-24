import entities.Master;
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
public class TC006_PromoteWithRecommendedPrice extends TestBase {

    private Master master;

    @Test
    public void promoteWithRecommendedPrice() throws TimeoutException, InterruptedException {
        master = data.getFullInfoMasterRandomEmail();

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(user.atMasterProfilePage.getProfileId());
        var project = data.getProject(master);

        admin.atAdminHomePage.loginAsAdmin();
        admin.atMastersPage.addMoneyToMaster(10000, master.getProfileId());
        admin.atCategoriesPage.enablePromotionAndSetPrice(master.getCategoryId(), "100", "500");

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMasterIfNeed(master.getLogin(), master.getPassword());

        user.atMasterProfilePage.open();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(project, true, false);
        user.atMasterProjectsPage.logsOut();

        admin.atAdminHomePage.loginAsAdmin();
        admin.atPromotionPage.approveProject(project.getSystemId());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(project.getCategory());
        user.atCatalogPage.verifyProjectPromoted(project);
    }

    @After
    public void tearDown() {
        admin.atAdminHomePage.loginAsAdmin();
        if (master.getProfileId() != null) {
            admin.atMastersPage.deleteMaster(master);
        }
    }
}

