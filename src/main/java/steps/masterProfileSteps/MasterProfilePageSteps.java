package steps.masterProfileSteps;

import entities.MasterBaseInfo;
import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterProfilePage;

public class MasterProfilePageSteps extends MasterProfileSteps {

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
    public void verifyProfilePage(MasterBaseInfo masterBaseInfo) {
        masterProfilePage.profilePageUrlShouldBeEqual(masterBaseInfo.getProfileUrl());
        masterProfilePage.masterFullNameShouldContain(masterBaseInfo.getMasterName());
        masterProfilePage.masterCategoriesShouldContain(masterBaseInfo.getCategory());
    }
}
