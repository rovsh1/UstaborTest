import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory()
@AddMasters(masters = 2, useAdminSite = true)
public class TC005_AddBadgesToMaster extends TestBase {

    @Test
    public void verifyBadgesEnableMasterPromotion() throws TimeoutException {
        var master = (Master) watcher.users.get(1);

        admin.atMastersPage.addAllBadgesToMaster(master);

        Admin.getInstance().runCron("1");
        admin.waitForCronTaskCompleted("1", 200);

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getCategory().getName());
        user.atCatalogPage.verifyMasterWithBadgesPromoted(master);
    }
}
