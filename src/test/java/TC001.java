import entities.Master;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TC001 extends TestBase {

    private Master master;

    @Test
    public void masterRegistrationTest() {
        master = data.getFullInfoMasterRandomEmail();

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(user.atMasterProfilePage.getProfileId());

        user.atMasterProfilePage.masterFullNameShouldContain(master.getLastName());
        user.atMasterProfilePage.aboutMeShouldBe(master.getAboutMe());
        user.atMasterProfilePage.masterCityShouldBe(master.getCity());
    }

    @After
    public void tearDown() {
        admin.atAdminHomePage.loginAsAdmin();
        if (master.getProfileId() != null) {
            admin.atMastersPage.deleteMaster(master);
        }
    }
}
