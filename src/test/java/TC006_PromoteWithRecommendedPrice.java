import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.masterProfile.MasterPromotionPage;
import utils.DataGenerator;
import utils.Email;

import java.util.concurrent.TimeoutException;

@WithTag("prod")

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters
public class TC006_PromoteWithRecommendedPrice extends TestBase {

    @Test
    public void promoteWithRecommendedPrice() throws TimeoutException {
        var email = new Email();
        var master = DataGenerator.getMasterWithEmail(email);
        master.setCategory(category);
        watcher.users.add(master);

        user.registerAsMaster(master);

        admin.addMoneyToMaster(10000, master);

        user.atHomePage.openHomePage();
        user.atHomePage.loginIfNeeded(master);

        user.atMasterProfilePage.openProfilePage();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(master.getCategory());

        user.atMasterPromotionPage.openPromotionTab();
        user.atMasterPromotionPage.promoteCategory(master.getCategory().getName(), MasterPromotionPage.PromotionType.RecommendedPrice);
        user.atMasterProjectsPage.logsOut();

        admin.approvePromotion(master.getCategory());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.verifyMasterCategoryPromoted(master);
    }
}