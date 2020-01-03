import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TC001 extends TestBase {

    @Test
    public void masterRegistrationTest() {

        User master = data.getFullInfoUserRandomEmail();

        user.atHomePage.registerAsMaster(master);
        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();
        user.atMasterProfilePage.masterFullNameShouldContain(master.getLastName());
        user.atMasterProfilePage.aboutMeShouldBe(master.getAboutMe());
        user.atMasterProfilePage.masterCityShouldBe(master.getCity());
    }
}
