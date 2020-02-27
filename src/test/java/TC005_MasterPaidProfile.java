import entities.User;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Pending;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

@RunWith(SerenityRunner.class)
public class TC005_MasterPaidProfile extends TestBase {

    @Test
    @Pending
    public void masterPaidProfile() {
//        User master = data.getFullInfoUserRandomEmail();
//
//        user.atHomePage.registerAsMaster(master);
//        user.atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        admin.atAdminHomePage.loginAsAdmin(
                Config.getUsers().getAdmin().getLogin(),
                Config.getUsers().getAdmin().getPassword()
        );
        admin.atMastersPage.addMoneyToMaster(1000, "");

    }
}

