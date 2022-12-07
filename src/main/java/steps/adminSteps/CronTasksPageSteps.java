package steps.adminSteps;

import net.thucydides.core.steps.ScenarioSteps;

public class CronTasksPageSteps extends ScenarioSteps {

    private CronTasksPage cronTasksPage;

    public void waitForCronTaskCompleted(String taskId, int timeout) {
        cronTasksPage.openPage();
        cronTasksPage.waitForCronTaskCompleted(taskId, timeout);
    }
}
