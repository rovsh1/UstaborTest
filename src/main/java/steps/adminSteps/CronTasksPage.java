package steps.adminSteps;

import pages.admin.BaseAdminPage;
import utils.Config;

import java.time.Duration;

public class CronTasksPage extends BaseAdminPage {

    private String cronTaskXpath = "//a[@data-id='%s' and not(@style='display: none;')]";

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "system/cron");
    }

    public void waitForCronTaskCompleted(String taskId) {
        withTimeoutOf(Duration.ofSeconds(600)).waitFor(String.format(cronTaskXpath, taskId)).isPresent();
    }
}
