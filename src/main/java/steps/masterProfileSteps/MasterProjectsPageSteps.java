package steps.masterProfileSteps;

import entities.Category;
import entities.Project;
import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterProjectsPage;
import utils.WaitHelper;

import java.util.concurrent.TimeoutException;

public class MasterProjectsPageSteps extends MasterProfileSteps {

    private MasterProjectsPage masterProjectsPage;

    @Step
    public void addNewProjectInCategory(Category category) throws TimeoutException {
        int countOfProjects = masterProjectsPage.getCountOfProjects();
        masterProjectsPage.openNewProjectForm();
        masterProjectsPage.enterProjectName(category.getProject().getName());
        masterProjectsPage.selectCategory(category.getName());
        masterProjectsPage.addProjectImage();
        masterProjectsPage.saveNewProject();
        WaitHelper.pollingWait(10000, 500, () ->
                masterProjectsPage.getCountOfProjects() == countOfProjects + 1);
    }

    @Step
    public void pageShouldBeVisible() {
        masterProjectsPage.addProjectBtnShouldBeVisible();
    }

    @Step
    public void logsOut() {
        masterProjectsPage.logsOut();
    }
}
