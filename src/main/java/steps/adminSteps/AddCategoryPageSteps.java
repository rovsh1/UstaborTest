package steps.adminSteps;

import entities.Category;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddCategoryPage;

public class AddCategoryPageSteps extends ScenarioSteps {

    private AddCategoryPage addCategoryPage;

    public void addTestCategory(Category category) {
        addCategoryPage.openPage();
        addCategoryPage.enterName(category.getName());
        addCategoryPage.enterUrl(category.getUrl());
        addCategoryPage.clickSubmit();
    }
}
