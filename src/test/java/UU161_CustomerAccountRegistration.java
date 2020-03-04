import entities.Master;
import entities.Project;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.*;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("test")
})
public class UU161_CustomerAccountRegistration extends RegistrationTestBase {

    private Project project;
    private Master master;

    @Before
    public void setup() throws TimeoutException {
        master = data.getFullInfoMasterRandomEmail();
        user.atHomePage.registerAsMaster(master);
        project = data.getProject(master);
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(project, false, false);
        user.atHomePage.logsOut();
    }

    @Test
    public void End2End_RegistrationTest() throws Exception {
        user.atHomePage.registerAsCustomer(
                Config.getUsers().getNewCustomer().getLogin(), Config.getUsers().getNewCustomer().getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(emailUtils.getAuthorizationCode());
        user.atCustomerProfilePage.verifyCustomerProfilePageIsOpened();

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
        user.atCatalogPage.verifyMasterInCatalogTop(project, 5);
    }

    @After
    public void teardown() {
        if (project.getSystemId() != null) {
            admin.atAdminHomePage.loginAsAdmin();
            admin.atProjectsPage.deleteProject(project);
        }
    }
}