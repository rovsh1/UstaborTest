package steps.adminSteps;

import net.thucydides.core.steps.ScenarioSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CatalogPage;
import pages.admin.AdminLoginPage;
import utils.Config;

public class HomePageSteps extends ScenarioSteps {

    private AdminLoginPage homePage;

    private static final Logger logger = LoggerFactory.getLogger(AdminLoginPage.class);


    public void loginAsAdmin() {
        homePage.openPage();
        homePage.enterLogin(Config.getUsers().getAdmin().getEmail());
        logger.info(Config.getUsers().getAdmin().getEmail());
        logger.info(Config.getUsers().getAdmin().getPassword());
        homePage.enterPassword(Config.getUsers().getAdmin().getPassword());
        homePage.clickLoginBtn();
        homePage.waitForLogin();
    }
}
