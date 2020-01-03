package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.MasterProjectsPage;
import utils.WaitHelper;

import java.util.concurrent.TimeoutException;

public class MasterProjectsPageSteps extends ScenarioSteps {

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
    public void openProjectsTab() {
        masterProjectsPage.clickProjectsTab();
    }

    @Step
    public void deleteProjectByName(String projectName) {
        masterProjectsPage.deleteProjectByName(projectName);
        masterProjectsPage.submitProjectDeleting();
    }
}
