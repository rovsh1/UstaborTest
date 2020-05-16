import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@WithTag("prod")

@RunWith(SerenityRunner.class)
public class TC006_PromoteWithRecommendedPrice extends ProdTestBase {

    @Test
    public void promoteWithRecommendedPrice() throws TimeoutException, InterruptedException {
        var master = data.getMasterRandomEmail(category);
        watcher.masters.add(master);

        user.registerAsMaster(master);
        admin.addMoneyToMaster(10000, master);
        admin.atCategoriesPage.enablePromotionAndSetPrice(master.getCategoryId(), "100", "500");

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMasterIfNeed(master.getEmail(), master.getPassword());

        user.atMasterProfilePage.open();
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