import entities.Master;
import entities.Project;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("test")
})
public class TC007_PromoteWithMinPrice extends TestBase {

    private Master master;

    @Test
    public void promoteWithMinPrice() throws TimeoutException {
        var minimalPriceOnlyCategories = getTextByPredicate("DomainBuild_MinPromo_");
        var category = minimalPriceOnlyCategories.get(new Random().nextInt(minimalPriceOnlyCategories.size()));
        var promoPrice = new Random().nextInt( 300);

        master = data.getFullInfoMasterRandomEmail();

        user.atHomePage.registerAsMasterWithCategory(master, category);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();
        master.setProfileId(user.atMasterProfilePage.getProfileId());

        var project = data.getProject(master);

        admin.atAdminHomePage.loginAsAdmin();
        admin.atMastersPage.addMoneyToMaster(1000, master.getLastName());
        admin.atCategoriesPage.enablePromotionAndSetPrice(master.getCategoryId(), String.valueOf(promoPrice), "");

        user.atHomePage.open();
        user.atMasterProfilePage.open();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(project, true, true);
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
        admin.atAdminHomePage.loginAsAdmin();
        if (master.getProfileId() != null) {
            admin.atMastersPage.deleteMaster(master);
        }
    }
}