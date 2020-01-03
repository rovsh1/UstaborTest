package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.CustomerProfilePage;

import java.util.concurrent.TimeoutException;

public class CustomerProfilePageSteps extends ScenarioSteps {

    private CustomerProfilePage customerProfilePage;

    @Step
    public void verifyCustomerProfilePageIsOpened() {
        customerProfilePage.deleteBtnShouldBeVisible();
    }

    @Step
    public void openHomePage() {
        customerProfilePage.openHomePage();
    }

    @Step
    public void verifyCountOfFavouriteProjectsEquals(int countOfProjects) {
        customerProfilePage.verifyCountOfFavouriteProjectsEquals(countOfProjects);
    }

    @Step
    public void removeRandomAndVerifyCountOfProjects(int countOfProjects) throws TimeoutException {
        customerProfilePage.removeRandomFavoriteProject();
        customerProfilePage.verifyCountOfFavouriteProjectsEquals(countOfProjects);
    }

    @Step
    public void verifyMyMastersListContains(String projectName) {
        customerProfilePage.verifyMyMastersListContains(projectName);
    }

    @Step
    public void logsOut(){
        customerProfilePage.logsOut();
    }

    @Step
    public void open() {
        customerProfilePage.clickProfileBtn();
    }

    @Step
    public void deleteProfile() {
        customerProfilePage.deleteProfile();
        customerProfilePage.submitProfileDeleting();
    }
}
