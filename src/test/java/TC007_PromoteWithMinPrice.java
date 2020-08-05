import entities.TestCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

@WithTag("prod")

@RunWith(SerenityRunner.class)
public class TC007_PromoteWithMinPrice extends ProdTestBase {

    @Test
    public void promoteWithMinPrice() throws Exception {
//        var minimalPriceOnlyCategories = getTextByPredicate("DomainBuild_MinPromo_");
//        var nonProdCategoryName = minimalPriceOnlyCategories.get(new Random().nextInt(minimalPriceOnlyCategories.size()));
//        var category = new TestCategory();
//        category.setName(nonProdCategoryName);

        var master = data.getMasterRandomEmail(category);
        watcher.masters.add(master);

        user.registerAsMaster(master);

        admin.addMoneyToMaster(10000, master);
        admin.atCategoriesPage.enablePromotionAndSetPrice(master.getCategoryId(), "100", "500");

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMasterIfNeed(master.getEmail(), master.getPassword());
        user.atMasterProfilePage.open();
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
