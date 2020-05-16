import entities.Project;
import entities.TestCategory;
import org.junit.Before;
import utils.Config;

import java.util.concurrent.TimeoutException;

public class ProdTestBase extends TestBase {

    public final TestCategory category = new TestCategory();

    @Before
    public void setUp() throws TimeoutException {
        super.setUp();
        if (Config.isProdEnv()) {
            watcher.category = category;
            admin.atAdminHomePage.loginAsAdmin();
            admin.atAddCategoryPage.addTestCategory(category);
            admin.atCategoriesPage.getCategoryIdByName(category);

            for (int i = 0;i<2;i++) {
                var master = data.getMasterRandomEmail(category);
                watcher.masters.add(master);

                user.atHomePage.openHomePage();
                user.atHomePage.registerAsMasterWithSpecifiedCategory(master);
                user.atMasterProfilePage.waitForPageIsVisible();
                master.setProfileId(user.atMasterProfilePage.getProfileId());

                var project = new Project(master.getCategoryName());
                user.atMasterProjectsPage.openProjectsTab();
                user.atMasterProjectsPage.addNewProjectInCategory(project, false, false);
                user.atHomePage.logsOut();
            }
        }
    }
}
