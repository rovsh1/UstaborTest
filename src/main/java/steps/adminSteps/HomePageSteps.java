package steps.adminSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AdminHomePage;

public class HomePageSteps extends ScenarioSteps {

    private AdminHomePage homePage;


    @Step
    public void loginAsAdmin(String login, String password) {
        homePage.open();
        homePage.enterLogin(login);
        homePage.enterPassword(password);
        homePage.clickLoginBtn();
    }
}
