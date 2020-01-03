package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.ProfilePage;

public class ProfilePageSteps extends ScenarioSteps {

    private ProfilePage profilePage;

    @Step
    public void VerifyProfileCitiesHave(String expectedCity) {
        profilePage.VerifyProfileCitiesHave(expectedCity);
    }
}
