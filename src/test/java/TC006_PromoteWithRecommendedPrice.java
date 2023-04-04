import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.masterProfile.MasterPromotionPage;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 1, useAdminSite = true)
public class TC006_PromoteWithRecommendedPrice extends TestBase {

    @Test
    public void promoteWithRecommendedPrice() throws TimeoutException {
        var master = (Master) watcher.users.get(0);
        admin.addMoneyToMaster(10000, master, false);

        user.atHomePage.openHomePage();
        user.atHomePage.loginIfNeeded(master);

        user.atMasterProfilePage.openProfilePage();
        user.atMasterPromotionPage.openPromotionTab();
        user.atMasterPromotionPage.promoteCategory(
                master.getCategory().getName(),
                MasterPromotionPage.PromotionType.RecommendedPrice);
        user.atMasterProjectsPage.logsOut();

        admin.approvePromotion(master.getCategory());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.verifyMasterCategoryPromoted(master);
    }
}
