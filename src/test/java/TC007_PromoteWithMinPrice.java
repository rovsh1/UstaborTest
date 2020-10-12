import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

@WithTag("prod")

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters
public class TC007_PromoteWithMinPrice extends TestBase {

    @Test
    public void promoteWithMinPrice() throws Exception {
        var master = DataGenerator.getMasterWithRandomEmail(category);
        watcher.users.add(master);

        user.registerAsMaster(master);
        admin.addMoneyToMaster(10000, master);

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMasterIfNeed(master.getEmail(), master.getPassword());
        user.atMasterProfilePage.openProfilePage();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(master.getProject(), true, true);
        user.atMasterProjectsPage.logsOut();

        admin.atAdminHomePage.loginAsAdmin();
        admin.atPromotionPage.approveProject(master.getProject());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getProject().getCategory());
        user.atCatalogPage.verifyProjectPromoted(master.getProject());
    }
}
