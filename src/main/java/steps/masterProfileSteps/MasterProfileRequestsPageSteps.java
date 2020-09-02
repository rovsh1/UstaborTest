package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterProfileRequestsPage;

public class MasterProfileRequestsPageSteps extends MasterProfileSteps {

    private MasterProfileRequestsPage masterProfileRequestsPage;

    @Step
    public void open() {
        masterProfileRequestsPage.openPage();
    }

    @Step
    public void verifyRequestId(String requestId) {
        masterProfileRequestsPage.verifyRequestId(requestId);
    }

    @Step
    public void openRequest() {
        masterProfileRequestsPage.openRequest();
    }

    @Step
    public void clickPhoneButton() {
        masterProfileRequestsPage.phoneButtonShouldBeVisible();
    }

    @Step
    public void verifyRequestStatus(String requestStatus) {
        masterProfileRequestsPage.verifyRequestStatus(requestStatus);
    }

    @Step
    public void verifyRequestStatusSetByCustomer(String requestStatus) {
        masterProfileRequestsPage.verifyCustomerRequestStatus(requestStatus);
    }

    @Step
    public void verifyRequestsTableIsEmpty() {
        masterProfileRequestsPage.verifyRequestsTableIsEmpty();
    }
}
