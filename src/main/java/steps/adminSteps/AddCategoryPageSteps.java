package steps.adminSteps;

import entities.TestCategory;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddCategoryPage;

public class AddCategoryPageSteps extends ScenarioSteps {

    private AddCategoryPage addCategoryPage;

    public void addTestCategory(TestCategory category) {
        addCategoryPage.openPage();
        addCategoryPage.enterName(category.getName());
        addCategoryPage.enterUrl(category.getUrl());
        addCategoryPage.clickSubmit();
    }
}
