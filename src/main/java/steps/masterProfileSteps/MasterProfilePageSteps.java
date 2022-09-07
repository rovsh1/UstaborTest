package steps.masterProfileSteps;

import entities.Master;
import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterProfilePage;

public class MasterProfilePageSteps extends MasterProfileSteps {

    private MasterProfilePage masterProfilePage;

    @Step
    public void openProfilePage() {
        masterProfilePage.openMasterProfilePage();
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
        masterProfilePage.aboutMeTextShouldBeEqual(aboutMe);
    }

    @Step
    public void logsOut() {
        masterProfilePage.logsOut();
    }

    @Step
    public void masterCityShouldBe(String city) {
        masterProfilePage.masterCityShouldBeEqual(city);
    }

    @Step
    public void verifyProfilePage(Master master) {
        masterProfilePage.masterFullNameShouldContain(master.getFirstName());
        masterProfilePage.masterRatingShouldBe(master.getRating());
        if (!master.getFeedback().isBlank()) {
            masterProfilePage.masterFeedbackShouldContain(master.getFeedback());
        }
    }

    @Step
    public void openProfileSettings() {
        masterProfilePage.openProfileSettings();
    }

    public String getProfileId() {
        return masterProfilePage.getProfileId();
    }
}
