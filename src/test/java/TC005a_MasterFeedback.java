import annotations.AddCategory;
import annotations.AddMasters;
import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@WithTag("prod")

@RunWith(SerenityRunner.class)
@AddCategory(promotionAndClickPrice = true)
@AddMasters(masters = 1)
public class TC005a_MasterFeedback extends TestBase {

    private User customer;

    @Before
    public void setUp() throws TimeoutException {
        super.setUp();
        customer = DataGenerator.getCustomer();
        watcher.users.add(customer);

        user.atHomePage.openHomePage();
        user.atHomePage.registerAsCustomer(customer);

        var smsCode = new Admin().getSmsCode(customer.getPhoneNumber());
        user.atHomePage.enterAuthCodeAndSubmit(smsCode);

        var password = new Admin().getSmsPassword(customer.getPhoneNumber());
        customer.setPassword(password);
    }

    @Test
    public void verifyMasterFeedback() throws TimeoutException {
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
