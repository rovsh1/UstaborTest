package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.MasterProfilePage;

public class MasterProfilePageSteps extends ScenarioSteps {

    private MasterProfilePage masterProfilePage;

    @Step
    public void open() {
        masterProfilePage.openProfilePage();
    }

    @Step
    public void masterProfilePagePageShouldBeVisible() {
        masterProfilePage.projectsTabShouldBeVisible();
    }

    @Step
    public void masterFullNameShouldContain(String firstName) {
        masterProfilePage.masterFullNameShouldContain(firstName);
    }

    @Step
    public void aboutMeShouldBe(String aboutMe) {
        masterProfilePage.aboutMeTextShould(aboutMe);
    }

    @Step
    public void logsOut() {
        masterProfilePage.logsOut();
    }

    @Step
    public void masterCityShouldBe(String city) {
        masterProfilePage.masterCityShouldBe(city);
    }
}
