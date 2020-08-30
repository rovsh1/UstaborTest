import entities.Master;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.Email;

import java.util.concurrent.TimeoutException;

@WithTag("prod")

@RunWith(SerenityRunner.class)
public class UU161_MasterFeedback extends TestBase {

    private Master master;
    private User customer;
    private Email email;

    @Before
    public void setUp() throws TimeoutException {
        email = new Email();
        customer = DataGenerator.getCustomer(email.getEmailAddress());
        master = DataGenerator.getMasterRandomEmail(category);
        watcher.users.add(customer);
        watcher.users.add(master);

        user.atHomePage.openHomePage();

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
        user.atCustomerProfilePersonalInfoPage.openHomePage();
        user.atHomePage.openCatalog();
        user.atCatalogPage.openRandomProjectWithNameNot(master.getLastName());
        user.atProjectPage.addProjectToFavorites();

        user.atCustomerProfilePersonalInfoPage.openHomePage();
        user.atHomePage.enterTextAndSearch(master.getProject().getName());
        user.atCatalogPage.openProjectContactsAndVerify(master.getLastName());

        user.atProjectPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.verifyCountOfFavouriteProjectsEquals(1);
        user.atCustomerProfilePersonalInfoPage.removeRandomAndVerifyCountOfProjects(0);

        user.atCustomerProfilePersonalInfoPage.logsOut();
        user.atHomePage.loginAsCustomer(customer.getEmail(), customer.getPassword());

        user.atHomePage.waitForFeedbackProposalAndOpen();
        user.atFeedbackPage.leftFeedback(5, "Testing Review");
        user.atHomePage.pageShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.verifyMyMastersListContains(master.getLastName());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(master.getProject().getCategory());
        user.atCatalogPage.loadAllResults();
        user.atCatalogPage.sortProjectsByRating();

        user.atCatalogPage.verifyProjectsSortedByRate(master.getProject(), 5);
    }
}
