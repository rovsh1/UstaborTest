import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC001_MasterRegistration extends TestBase {

    @Test
    public void verifyMasterCanCreateProfile() {
        var master = DataGenerator.getMasterRandomEmail();
        watcher.users.add(master);

        user.atHomePage.registerAsMaster(master);

        user.atMasterProfilePage.waitForPageIsVisible();
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(user.atMasterProfilePage.getProfileId());

        user.atMasterProfilePage.masterFullNameShouldContain(master.getLastName());
        user.atMasterProfilePage.aboutMeShouldBe(master.getAboutMe());
        user.atMasterProfilePage.masterCityShouldBe(master.getCity());
    }
}
