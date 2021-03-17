import annotations.AddCategory;
import annotations.AddMasters;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.Email;

import java.util.concurrent.TimeoutException;

@WithTag("prod")

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 1)
@Ignore
public class TC005a_MasterFeedback extends TestBase {

    private User customer;
    private Email email;

    @Before
    public void setUp() throws TimeoutException {
        super.setUp();
        email = new Email();
        customer = DataGenerator.getCustomer(email.getEmailAddress());
        watcher.users.add(customer);

        user.atHomePage.openHomePage();
        user.atHomePage.registerAsCustomer(customer);
        user.atHomePage.enterAuthCodeAndSubmit(email.getAuthCode());
    }

    @Test
    public void verifyMasterFeedback() throws InterruptedException, TimeoutException {
        user.atCustomerProfilePersonalInfoPage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(watcher.getMaster().getCategory().getName());

        user.atCatalogPage.openMasterContactsAndVerify(watcher.getMaster().getLastName());

        user.atCustomerProfilePersonalInfoPage.logsOut();
        user.atHomePage.login(customer, true);

        user.atHomePage.waitForFeedbackProposalAndOpen();
        user.atFeedbackPage.leftFeedback(5, "Testing Review");
        user.atHomePage.pageShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atCustomerProfilePersonalInfoPage.verifyMyMastersListContains(watcher.getMaster().getLastName());

        user.atHomePage.openHomePage();
        user.atHomePage.openBuilderTab();
        user.atHomePage.openCategory(watcher.getMaster().getCategory().getName());
        user.atCatalogPage.loadAllResults();
        user.atCatalogPage.sortProjectsByRating();

        user.atCatalogPage.verifyProjectsSortedByRate(watcher.getMaster().getCategory().getProject(), 5);
    }
}
