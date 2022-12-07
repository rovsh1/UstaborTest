package steps.adminSteps;

import entities.Category;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddCategoryPage;

public class AddCategoryPageSteps extends ScenarioSteps {

    private AddCategoryPage addCategoryPage;

    public void addTestCategory(Category category) {
        addCategoryPage.openPage();

        addCategoryPage.selectDomain();
        addCategoryPage.enterName(category.getName());
        addCategoryPage.enterUrl(category.getUrl());

        addCategoryPage.openContentTab();
        addCategoryPage.enterTitle(category.getName());

        addCategoryPage.clickSubmit();
    }
}
