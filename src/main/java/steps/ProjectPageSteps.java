package steps;

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
        projectPage.openCustomerProfilePage();
    }
}
