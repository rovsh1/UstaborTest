import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class TC001 extends TestBase {

    @Test
    public void masterRegistrationTest() {
        var master = data.getMasterRandomEmail();

        user.atHomePage.registerAsMaster(master);
        watcher.masters.add(master);

        user.atMasterProfilePage.waitForPageIsVisible();
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(user.atMasterProfilePage.getProfileId());

        user.atMasterProfilePage.masterFullNameShouldContain(master.getLastName());
        user.atMasterProfilePage.aboutMeShouldBe(master.getAboutMe());
        user.atMasterProfilePage.masterCityShouldBe(master.getCity());
    }
}
