package steps.adminSteps;

import entities.Project;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.ProjectsPage;

public class ProjectsPageSteps extends ScenarioSteps {

    private ProjectsPage projectsPage;

    @Step
    public void deleteProject(Project project) {
        projectsPage.deleteProject(project);
    }
}
