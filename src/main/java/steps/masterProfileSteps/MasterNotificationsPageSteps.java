package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterNotificationsPage;

public class MasterNotificationsPageSteps extends MasterProfileSteps {

    private MasterNotificationsPage masterNotificationsPage;

    @Step
    public void pageShouldBeVisible() {
        masterNotificationsPage.notificationsBtnShpuldBeVisible();
        masterNotificationsPage.feedbacksBtnShouldBeVisible();
    }
}
