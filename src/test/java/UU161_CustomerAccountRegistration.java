import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.*;
import org.junit.runner.RunWith;
import utils.Config;
import utils.EmailUtilities;

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

        String projectName = user.atCatalogPage.addRandomProjectToFavorites();
        user.atCatalogPage.openProjectContactsAndVerify(projectName);

        user.atCatalogPage.openRandomProjectWithNameNot(projectName);
        user.atProjectPage.addProjectToFavorites();

        user.atProjectPage.openCustomerProfilePage();
        user.atCustomerProfilePage.verifyCountOfFavouriteProjectsEquals(2);
        user.atCustomerProfilePage.removeRandomAndVerifyCountOfProjects(1);

        user.atCustomerProfilePage.verifyMyMastersListContains(projectName);

        user.atCustomerProfilePage.logsOut();
        user.atHomePage.loginAsCustomer(
                Config.getUsers().getNewCustomer().getLogin(), Config.getUsers().getNewCustomer().getPassword());

        user.atHomePage.waitForFeedbackProposalAndOpen();
        user.atFeedbackPage.leftFeedbackWithRandomGradeAndComment("Test comment");
        user.atHomePage.pageShouldBeVisible();
    }
}
