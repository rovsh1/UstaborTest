package steps.masterProfileSteps;

import entities.CategoryCheckbox;
import entities.Master;
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
    public void verifyProfilePage(Master master) {
        masterProfilePage.profilePageUrlShouldBeEqual(master.getProfileUrl());
        masterProfilePage.masterFullNameShouldContain(master.getFirstName());
        masterProfilePage.masterCategoriesShouldContain(master.getCategory());
    }

    @Step
    public void openProfileSettings() {
        masterProfilePage.openProfileSettings();
    }

    @Step
    public void verifyCategoriesListValue(CategoryCheckbox category) {
        masterProfilePage.verifyCategories(category);
    }
}