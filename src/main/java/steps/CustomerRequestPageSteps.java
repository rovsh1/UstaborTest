package steps;

import entities.User;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.CustomerRequestPage;

public class CustomerRequestPageSteps extends ScenarioSteps {

    private CustomerRequestPage customerRequestPage;

    @Step
    public void openRequest() {
        customerRequestPage.openRequest();
    }

    @Step
    public void openAssignedMasters() {
        customerRequestPage.openAssignedMasters();
    }

    @Step
    public void verifyMasterAssigned(User master) {
        customerRequestPage.verifyMasterAssigned(master);
    }

    @Step
    public void verifyMasterSmsText(String smsTestMessage) {
        customerRequestPage.verifySmsText(smsTestMessage);
    }

    @Step
    public void verifyMasterOffer(String offerPrice) {
        customerRequestPage.verifyMasterOffer(offerPrice);
    }

    @Step
    public void hideMasterOffer() {
        customerRequestPage.clickHideMasterOffer();
    }

    @Step
    public void verifyOfferIsHidden() {
        customerRequestPage.verifyActiveOffersTableIsEmpty();
        customerRequestPage.clickHiddenOffersBtn();
        customerRequestPage.verifyHiddenOfferIsPresent();
    }
}
