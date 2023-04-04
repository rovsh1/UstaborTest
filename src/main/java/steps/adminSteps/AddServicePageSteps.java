package steps.adminSteps;

import entities.Category;
import net.thucydides.core.steps.ScenarioSteps;
import pages.admin.AddServicePage;

public class AddServicePageSteps extends ScenarioSteps {

    private AddServicePage servicePage;

    public void openPage() {
        servicePage.openPage();
    }

    public void fillAddServiceForm(Category category) {
        servicePage.enterName("Autotest");
        servicePage.selectCategory(category.getName());
        servicePage.enterHeader("Test");
    }

    public void saveService() {
        servicePage.saveService();
    }

    public void setPrices() {
        servicePage.setPrices();
    }
}
