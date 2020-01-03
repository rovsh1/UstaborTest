import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;
import utils.DataGenerator;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("test")
})
public class UU167_VerifyMasterBackOffice extends TestBase {

    private String projectName;

    @Test
    public void verifyMasterBackOffice() throws TimeoutException {

        projectName = String.format("Project#%d", data.getRandomNumber());

        user.atHomePage.loginAsMaster(
                Config.getUsers().getDefaultMaster().getLogin(),
                Config.getUsers().getDefaultMaster().getPassword(),
                true);
        user.atMasterProfilePage.open();

        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.addNewProjectWithoutPromotion(
                projectName, "This is project description");
        user.atMasterProjectsPage.verifyNewProjectAdded(projectName);

        user.atMasterPromotionPage.openPromotionTab();
        user.atMasterPromotionPage.promoteProjectWithRecommendedPrice(projectName);
    }

    @After
    public void tearDown() {
        user.atMasterProjectsPage.openProjectsTab();
        user.atMasterProjectsPage.deleteProjectByName(projectName);
    }
}
