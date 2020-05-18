import entities.Master;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@WithTag("prod")

@RunWith(SerenityRunner.class)
public class UU161_MasterFeedback extends RegistrationTestBase {

    private Master master;
    private User customer;

    @Before
    public void setUp() throws TimeoutException {
        super.setUp();
        customer = data.getCustomer(email.getEmailAddress());
        master = data.getMasterRandomEmail(category);
        watcher.masters.add(master);

        user.registerAsMaster(master);
        admin.enablePromotion(master);

        user.atHomePage.openHomePage();
        user.atHomePage.loginAsMasterIfNeed(master.getEmail(), master.getPassword());
        user.atMasterProfilePage.open();
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectInCategory(master.getProject(), false, false);
        user.atHomePage.logsOut();

        user.atHomePage.registerAsCustomer(customer.getEmail(), customer.getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());
    }

    @Test
    public void verifyMasterFeedback() throws InterruptedException {
        user.atCustomerProfilePage.openHomePage();
        user.atHomePage.openCatalog();
        user.atCatalogPage.openRandomProjectWithNameNot(master.getLastName());
        user.atProjectPage.addProjectToFavorites();

        user.atCustomerProfilePage.openHomePage();
        user.atHomePage.enterTextAndSearch(master.getProject().getName());
        user.atCatalogPage.openProjectContactsAndVerify(master.getLastName());

        user.atProjectPage.openCustomerProfilePage();
        user.atCustomerProfilePage.verifyCountOfFavouriteProjectsEquals(1);
        user.atCustomerProfilePage.removeRandomAndVerifyCountOfProjects(0);

        user.atCustomerProfilePage.logsOut();
        user.atHomePage.loginAsCustomer(customer.getEmail(), customer.getPassword());

        user.atHomePage.waitForFeedbackProposalAndOpen();
        user.atFeedbackPage.leftFeedback(5, "Testing Review");
        user.atHomePage.pageShouldBeVisible();

        user.atCustomerProfilePage.openCustomerProfilePage();
        user.atCustomerProfilePage.verifyMyMastersListContains(master.getLastName());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getProject().getCategory());
        user.atCatalogPage.loadAllResults();
        user.atCatalogPage.sortProjectsByRating();

        user.atCatalogPage.verifyProjectsSortedByRate(master.getProject(), 5);
    }
}
