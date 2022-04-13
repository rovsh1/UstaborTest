package steps.adminSteps;

import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AdminLoginPage;
import utils.Config;

public class HomePageSteps extends ScenarioSteps {

    private AdminLoginPage homePage;

    public void loginAsAdmin() {
        homePage.openPage();
        homePage.enterLogin(Config.getUsers().getAdmin().getEmail());
        homePage.enterPassword(Config.getUsers().getAdmin().getPassword());
        homePage.clickLoginBtn();
        homePage.waitForLogin();
    }
}
