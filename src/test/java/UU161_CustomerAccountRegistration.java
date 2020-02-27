import entities.FavProject;
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
public class UU161_CustomerAccountRegistration extends RegistrationTestBase {

    @Test
    public void End2End_RegistrationTest() throws Exception {

        user.atHomePage.registerAsCustomer(
                Config.getUsers().getNewCustomer().getLogin(), Config.getUsers().getNewCustomer().getPassword());
        user.atHomePage.enterAuthCodeAndSubmit(emailUtils.getAuthorizationCode());
        user.atCustomerProfilePage.verifyCustomerProfilePageIsOpened();

        user.atCustomerProfilePage.openHomePage();
        user.atHomePage.openCatalog();

        FavProject project = user.atCatalogPage.addRandomProjectToFavorites();
        user.atCatalogPage.openProjectContactsAndVerify(project.getMaster());

        user.atCatalogPage.openRandomProjectWithNameNot(project.getCategory());
        user.atProjectPage.addProjectToFavorites();

        user.atProjectPage.openCustomerProfilePage();
        user.atCustomerProfilePage.verifyCountOfFavouriteProjectsEquals(2);
        user.atCustomerProfilePage.removeRandomAndVerifyCountOfProjects(1);

        user.atCustomerProfilePage.logsOut();
        user.atHomePage.loginAsCustomer(
                Config.getUsers().getNewCustomer().getLogin(), Config.getUsers().getNewCustomer().getPassword());

        user.atHomePage.waitForFeedbackProposalAndOpen();
        user.atFeedbackPage.leftFeedback(5, "Testing Review");
        user.atHomePage.pageShouldBeVisible();

        user.atCustomerProfilePage.open();
        user.atCustomerProfilePage.verifyMyMastersListContains(project.getMaster());

        user.atCustomerProfilePage.openHomePage();
        user.atHomePage.enterTextAndSearch(project.getCategory());
    }
}
