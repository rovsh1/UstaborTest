import annotations.AddCategory;
import annotations.AddMasters;
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
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 1)
public class UU161_MasterFeedback extends TestBase {

    private User customer;
    private Email email;

    @Before
    public void setUp() throws TimeoutException {
        super.setUp();
        email = new Email();
        customer = DataGenerator.getCustomer(email.getEmailAddress());
        watcher.users.add(customer);

        user.atHomePage.openHomePage();
        user.atHomePage.registerAsCustomer(customer.getEmail(), customer.getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());
    }

    @Test
    public void verifyMasterFeedback() throws InterruptedException {
        user.atCustomerProfilePersonalInfoPage.openHomePage();
        user.atHomePage.openCatalog();
        user.atCatalogPage.openRandomProjectWithNameNot(watcher.getMaster().getLastName());
        user.atProjectPage.addProjectToFavorites();

        user.atCustomerProfilePersonalInfoPage.openHomePage();
        user.atHomePage.enterTextAndSearch(watcher.getMaster().getProject().getName());
        user.atCatalogPage.openProjectContactsAndVerify(watcher.getMaster().getLastName());

        user.atProjectPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.verifyCountOfFavouriteProjectsEquals(1);
        user.atCustomerProfilePersonalInfoPage.removeRandomAndVerifyCountOfProjects(0);

        user.atCustomerProfilePersonalInfoPage.logsOut();
        user.atHomePage.loginAsCustomer(customer.getEmail(), customer.getPassword());

        user.atHomePage.waitForFeedbackProposalAndOpen();
        user.atFeedbackPage.leftFeedback(5, "Testing Review");
        user.atHomePage.pageShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.verifyMyMastersListContains(watcher.getMaster().getLastName());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(watcher.getMaster().getProject().getCategory());
        user.atCatalogPage.loadAllResults();
        user.atCatalogPage.sortProjectsByRating();

        user.atCatalogPage.verifyProjectsSortedByRate(watcher.getMaster().getProject(), 5);
    }
}
