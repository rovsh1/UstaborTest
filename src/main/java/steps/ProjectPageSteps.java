package steps;

import entities.Master;
import entities.Project;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.ProjectPage;

public class ProjectPageSteps extends ScenarioSteps {

    private ProjectPage projectPage;

    @Step
    public void addProjectToFavorites() {
        projectPage.addProjectToFavorites();
    }

    @Step
    public void openCustomerProfilePage() {
        projectPage.openProfilePage();
    }

    @Step
    public void verifyProjectPageByMasterInfo(Master master) {
        projectPage.projectUrlShouldContain(master.getProjectUrl());
        projectPage.masterNameShouldContain(master.getFirstName());
        projectPage.masterCategoriesShouldContain(master.getCategoryName());
        projectPage.aboutMeShouldBeVisible();
        projectPage.projectImageShouldBeVisible();
    }

    @Step
    public void verifyProjectInfo(Project project, Master master) {
        projectPage.masterCategoriesShouldContain(project.getCategory());
        projectPage.projectDescriptionShouldBe(project.getDescription());
        projectPage.projectUrlShouldContain(project.getSystemId());
        projectPage.masterNameShouldContain(master.getLastName());
    }
}
