import entities.Master;
import entities.Project;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.*;
import org.junit.runner.RunWith;
import utils.Config;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("test")
})
public class UU161_MasterFeedback extends RegistrationTestBase {

    private Project project;
    private Master master;

    @Before
    public void setup() throws Exception {
        master = data.getFullInfoMasterRandomEmail();
        user.atHomePage.registerAsMaster(master);
        project = data.getProject(master);
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(project, false, false);
        user.atHomePage.logsOut();

        user.atHomePage.registerAsCustomer(
                Config.getUsers().getNewCustomer().getLogin(), Config.getUsers().getNewCustomer().getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(emailUtils.getAuthorizationCode());
    }

    @Test
    public void verifyMasterFeedback() throws Exception {
        user.atCustomerProfilePage.openHomePage();
        user.atHomePage.openCatalog();
        user.atCatalogPage.openRandomProjectWithNameNot(master.getLastName());
        user.atProjectPage.addProjectToFavorites();

        user.atCustomerProfilePage.openHomePage();
        user.atHomePage.enterTextAndSearch(project.getName());
        user.atCatalogPage.openProjectContactsAndVerify(master.getLastName());

        user.atProjectPage.openCustomerProfilePage();
        user.atCustomerProfilePage.verifyCountOfFavouriteProjectsEquals(1);
        user.atCustomerProfilePage.removeRandomAndVerifyCountOfProjects(0);

        user.atCustomerProfilePage.logsOut();
        user.atHomePage.loginAsCustomer(
                Config.getUsers().getNewCustomer().getLogin(),
                Config.getUsers().getNewCustomer().getPassword());

        user.atHomePage.waitForFeedbackProposalAndOpen();
        user.atFeedbackPage.leftFeedback(5, "Testing Review");
        user.atHomePage.pageShouldBeVisible();

        user.atCustomerProfilePage.open();
        user.atCustomerProfilePage.verifyMyMastersListContains(master.getLastName());

        user.atHomePage.open();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(project.getCategory());
        user.atCatalogPage.loadAllResults();
        user.atCatalogPage.sortProjectsByRating();

        user.atCatalogPage.verifyProjectsSortedByRate(project, 5);
    }

    @After
    public void tearDown() {
        admin.atAdminHomePage.loginAsAdmin();
        if (master.getProfileId() != null) {
            admin.atMastersPage.deleteMaster(master);
        }
    }
}