package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.BasePage;

import java.util.List;

public class CommonSteps extends ScenarioSteps {

    private BasePage basePage;

    @Step
    public void openSiteWithName(String siteName) {
        basePage.openSiteWithName(siteName);
    }

    @Step
    public void correctSiteShouldBeVisible(String siteName) {
        basePage.correctSiteShouldBeVisible(siteName);
    }

    @Step
    public void openSiteMap() {
        basePage.openSiteMap();
    }

    @Step
    public List<String> getSitesNames() {
        return basePage.getSitesNames();
    }
}
