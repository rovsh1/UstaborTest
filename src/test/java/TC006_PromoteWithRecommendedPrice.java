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
public class TC006_PromoteWithRecommendedPrice extends TestBase {

    @Test
    public void promoteWithRecommendedPrice() throws TimeoutException, InterruptedException {
        var master = DataGenerator.getMasterWithRandomEmail(category);
        watcher.users.add(master);

        user.registerAsMaster(master);
        admin.addMoneyToMaster(10000, master);

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMasterIfNeed(master.getEmail(), master.getPassword());

        user.atMasterProfilePage.openProfilePage();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(master.getProject(), true, false);
        user.atMasterProjectsPage.logsOut();

        admin.approveProject(master.getProject());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getProject().getCategory());
        user.atCatalogPage.verifyProjectPromoted(master.getProject());
    }
}