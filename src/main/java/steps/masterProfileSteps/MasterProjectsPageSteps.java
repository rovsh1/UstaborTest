package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterProjectsPage;
import utils.WaitHelper;

import java.util.concurrent.TimeoutException;

public class MasterProjectsPageSteps extends MasterProfileSteps {

    private MasterProjectsPage masterProjectsPage;

    @Step
    public void addNewProjectWithoutPromotion(String name, String description) throws TimeoutException {
        int countOfProjects = masterProjectsPage.getCountOfProjects();
        masterProjectsPage.openNewProjectForm();
        masterProjectsPage.enterProjectName(name);
        masterProjectsPage.addProjectImage();
        masterProjectsPage.enterProjectDescription(description);
        masterProjectsPage.selectProjectCategoryWithPromotion();
        masterProjectsPage.selectNoPromotionCheckbox();
        masterProjectsPage.saveNewProject();
        masterProjectsPage.waitTillProjectsAreVisible();
        WaitHelper.pollingWait(10000, 500, () ->
                masterProjectsPage.getCountOfProjects() == countOfProjects + 1);
    }

    @Step
    public void verifyNewProjectAdded(String name) {
        masterProjectsPage.verifyProjectsListContains(name);
    }

    @Step
    public void deleteProjectByName(String projectName) {
        masterProjectsPage.deleteProjectByName(projectName);
        masterProjectsPage.submitProjectDeleting();
    }

    @Step
    public void pageShouldBeVisible() {
        masterProjectsPage.addProjectBtnShouldBeVisible();
    }
}
