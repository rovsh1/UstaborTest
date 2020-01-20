package steps;

import entities.MasterBaseInfo;
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
    public void verifyProjectPage(MasterBaseInfo baseInfo) {
        projectPage.projectUrlShouldBe(baseInfo.getProjectUrl());
        projectPage.masterNameShouldBe(baseInfo.getMasterName());
        projectPage.masterCategoriesShouldContain(baseInfo.getCategory());
        projectPage.aboutMeShouldBeVisible();
        projectPage.projectImageShouldBeVisible();
    }
}
