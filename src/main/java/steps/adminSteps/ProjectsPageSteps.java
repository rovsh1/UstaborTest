package steps.adminSteps;

import entities.Project;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.ProjectsPage;

public class ProjectsPageSteps extends ScenarioSteps {

    private ProjectsPage projectsPage;

    public void deleteProject(Project project) {
        projectsPage.deleteProject(project);
    }
}
