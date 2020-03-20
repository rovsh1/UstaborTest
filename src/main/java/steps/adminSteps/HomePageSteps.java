package steps.adminSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AdminHomePage;
import utils.Config;

public class HomePageSteps extends ScenarioSteps {

    private AdminHomePage homePage;

    @Step
    public void loginAsAdmin() {
        homePage.open();
        if (homePage.isLoginInputVisible()) {
            homePage.enterLogin(Config.getUsers().getAdmin().getLogin());
            homePage.enterPassword(Config.getUsers().getAdmin().getPassword());
            homePage.clickLoginBtn();
        }
    }
}
