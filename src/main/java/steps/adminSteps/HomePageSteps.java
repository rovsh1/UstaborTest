package steps.adminSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AdminLoginPage;
import utils.Config;

public class HomePageSteps extends ScenarioSteps {

    private AdminLoginPage homePage;

    @Step
    public void loginAsAdmin() {
        homePage.openPage();
        homePage.closeChromeWarningIfPresent();

        homePage.enterLogin(Config.getUsers().getAdmin().getLogin());
        homePage.enterPassword(Config.getUsers().getAdmin().getPassword());
        homePage.clickLoginBtn();
    }
}
