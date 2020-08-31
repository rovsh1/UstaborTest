package steps.masterProfileSteps;

import entities.TestCategory;
import entities.User;
import enums.RequestPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
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
    public void verifyClickPriceFirstPage(String clickPrice) {
        masterProfileRequestsPage.openRequestPage(RequestPages.First);
        masterProfileRequestsPage.verifyContactClientPrice(clickPrice);
    }

    @Step
    public void verifyClickPriceSecondPage(String clickPrice) {
        masterProfileRequestsPage.openRequestPage(RequestPages.Second);
        masterProfileRequestsPage.verifyContactClientPrice(clickPrice);
    }

    @Step
    public void verifyClickPriceLastPage(String clickPrice) {
        masterProfileRequestsPage.openRequestPage(RequestPages.Last);
        masterProfileRequestsPage.verifyContactClientPrice(clickPrice);
    }

    @Step
    public void clickConnectClientButton() {
        masterProfileRequestsPage.clickConnectClientButton();
    }

    @Step
    public void verifyErrorMessage(String withdrawError) {
        masterProfileRequestsPage.verifyErrorPopupText(withdrawError);
    }

    @Step
    public void verifyCustomerInfo(User customer) {
        masterProfileRequestsPage.verifyCustomerNumber(customer.getPhoneNumber());
        masterProfileRequestsPage.verifyCustomerName(customer.getFirstName());
    }

    @Step
    public void clickPhoneButton() {
        masterProfileRequestsPage.phoneButtonShouldBeVisible();
    }

    @Step
    public void verifyCustomerConnectButtonHasNoPrice() {
        masterProfileRequestsPage.verifyCustomerConnectButtonHasNoPrice();
    }
}
