package steps.adminSteps;

import entities.Master;
import entities.Category;
import entities.User;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.RequestsPage;

public class RequestsPageSteps extends ScenarioSteps {

    private RequestsPage requestsPage;

    @Step
    public void openRequestById(String requestId) {
        requestsPage.openPage(requestId);
    }

    @Step
    public void verifyRequest(User customer, Category category, String question) {
        requestsPage.verifyCustomerLogin(customer.getFirstName());
        requestsPage.verifyCategory(category.getName());
        requestsPage.verifyWhatToDoQuestion(question);
        requestsPage.verifyCustomerName(customer.getFirstName());
        requestsPage.verifyAddress(customer.getCity());
        requestsPage.verifyPhoneNumber(customer.getPhoneNumber());
    }

    @Step
    public void assignRequestToMasterForFree(Master master) {
        requestsPage.openAssignMasterForm();
        requestsPage.findMaster(master.getFirstName());
        requestsPage.assignForFree();
        requestsPage.waitForLoaderDisappears(180000);
    }

    @Step
    public void assignRequestToMasterForPayment(Master master) {
        requestsPage.openAssignMasterForm();
        requestsPage.findMaster(master.getLastName());
        requestsPage.assignForPayment();
        requestsPage.waitForLoaderDisappears(180000);
    }

    @Step
    public void reassignRequestToMasterForPayment(Master master) {
        requestsPage.openAssignMasterForm();
        requestsPage.findMaster(master.getLastName());
        requestsPage.resetAssign();
        requestsPage.assignForPayment();
        requestsPage.waitForLoaderDisappears(180000);
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
        requestsPage.clickMenu();
        requestsPage.deleteRequest();
        requestsPage.waitForLoaderDisappears();
    }
}
