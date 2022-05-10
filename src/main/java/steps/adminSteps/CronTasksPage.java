package steps.adminSteps;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.admin.BaseAdminPage;
import utils.Config;

public class CronTasksPage extends BaseAdminPage {

    private static final Logger logger = LoggerFactory.getLogger(CronTasksPage.class);

    @FindBy(xpath = "//a[@data-id='1' and not(@style='display: none;')]")
    private WebElementFacade task;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "system/cron");
    }

    public void waitForCronTaskCompleted(String taskId, int timeout) {
        var limit = timeout * 1000 / 15000;

        for (int i = 0; i < limit; i++) {
            if (task.isVisible()) {
                logger.info(String.format("Cron task %s completed", taskId));
                return;
            }
            getDriver().navigate().refresh();
        }

        logger.info(String.format("Cron task %s failed", taskId));
    }
}
