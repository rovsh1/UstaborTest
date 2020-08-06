package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.PlaceOrderPage;

public class PlaceOrderPageSteps extends ScenarioSteps {

    private PlaceOrderPage placeOrderPage;

    @Step
    public void verifyPage() {
        placeOrderPage.nameInputShouldBeVisible();
        placeOrderPage.domainDropdownShouldBeVisible();
        placeOrderPage.categoryDropdownShouldBeVisible();
        placeOrderPage.photoFormShouldBeVisible();
        placeOrderPage.additionalInfoFormShouldBeVisible();
    }
}
