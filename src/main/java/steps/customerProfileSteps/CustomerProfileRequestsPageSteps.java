package steps.customerProfileSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.customerProfile.CustomerProfileRequestsPage;

public class CustomerProfileRequestsPageSteps extends ScenarioSteps {

    private CustomerProfileRequestsPage customerProfileRequestsPage;

    @Step
    public void open() {
        customerProfileRequestsPage.openPage();
    }

    @Step
    public void newRequestShouldBeVisible() {
        customerProfileRequestsPage.requestShouldBeVisible();
    }

    public String getRequestId() {
        return customerProfileRequestsPage.getRequestId();
    }

    public String getCustomerProfileId() {
        return customerProfileRequestsPage.getCustomerId();
    }

    @Step
    public void deleteRequest() {
        customerProfileRequestsPage.deleteRequest();
        customerProfileRequestsPage.waitForLoaderDisappears();
    }

    @Step
    public void verifyRequestsTabIsEmpty() {
        customerProfileRequestsPage.verifyRequestsTabIsEmpty();
    }
}
