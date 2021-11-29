package steps.customerProfileSteps;

import entities.User;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.customerProfile.CustomerProfilePersonalInfoPage;

public class CustomerProfilePersonalInfoPageSteps extends ScenarioSteps {

    private CustomerProfilePersonalInfoPage customerProfilePersonalInfoPage;

    @Step
    public void verifyCustomerProfilePageIsOpened() {
        customerProfilePersonalInfoPage.openPersonalInfo();
        customerProfilePersonalInfoPage.deleteBtnShouldBeVisible();
    }

    @Step
    public void openHomePage() {
        customerProfilePersonalInfoPage.openHomePage();
    }

    @Step
    public void verifyCountOfFavouriteProjectsEquals(int countOfProjects) {
        customerProfilePersonalInfoPage.verifyCountOfFavouriteProjectsEquals(countOfProjects);
    }

    @Step
    public void removeRandomAndVerifyCountOfProjects(int countOfProjects) {
        customerProfilePersonalInfoPage.removeRandomFavoriteProject();
        customerProfilePersonalInfoPage.verifyCountOfFavouriteProjectsEquals(countOfProjects);
    }

    @Step
    public void verifyMyMastersListContains(String projectName) {
        customerProfilePersonalInfoPage.verifyMyMastersListContains(projectName);
    }

    @Step
    public void logsOut(){
        customerProfilePersonalInfoPage.logsOut();
    }

    @Step
    public void openCustomerProfilePage() {
        customerProfilePersonalInfoPage.clickProfileBtn();
        customerProfilePersonalInfoPage.openPersonalInfo();
    }

    @Step
    public void deleteProfile() {
        customerProfilePersonalInfoPage.deleteProfile();
        customerProfilePersonalInfoPage.submitProfileDeleting();
    }

    @Step
    public CustomerProfilePersonalInfoPageSteps openPersonalInfo() {
        customerProfilePersonalInfoPage.openPersonalInfo();
        return this;
    }

    @Step
    public void verifyProfileData(User guest) {
        customerProfilePersonalInfoPage.verifyUserName(guest.getPhoneNumber());
        customerProfilePersonalInfoPage.verifyPhoneNumber(guest.getPhoneNumber());
    }

    public String getCustomerProfileId() {
        return customerProfilePersonalInfoPage.getCustomerId();
    }
}
