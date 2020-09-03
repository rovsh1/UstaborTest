package steps.adminSteps;

import entities.Master;
import entities.TestCategory;
import entities.User;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.RequestsPage;

public class RequestsPageSteps extends ScenarioSteps {

    private RequestsPage requestsPage;

    @Step
    public void openRequestById(String requestId) {
        requestsPage.openPage(requestId);
    }

    @Step
    public void verifyRequest(User customer, TestCategory category, String question) {
        requestsPage.verifyCustomerLogin(customer.getFirstName());
        requestsPage.verifyCategory(category.getName());
        requestsPage.verifyWhatToDoQuestion(question);
        requestsPage.verifyCustomerName(customer.getFirstName());
        requestsPage.verifyAddress(customer.getCity());
        requestsPage.verifyPhoneNumber(customer.getPhoneNumber());
    }

    @Step
    public void addAssignRequestToMaster(Master master) {
        requestsPage.openAssignMasterForm();
        requestsPage.findMaster(master.getProfileId());
        requestsPage.submitMasterAssign();
        requestsPage.waitForLoaderDisappears();
    }

    @Step
    public void assignRequestToAllMastersOfCategory() {
        requestsPage.openAssignMasterForm();
        requestsPage.setAssignToAllMasters();
        requestsPage.setWithdrawContactPrice();
        requestsPage.submitMasterAssign();
        requestsPage.waitForLoaderDisappears();
    }

    @Step
    public void deleteRequest(String requestId) {
        openRequestById(requestId);
        requestsPage.deleteRequest();
        requestsPage.waitForLoaderDisappears();
    }
}
