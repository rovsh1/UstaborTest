package steps.adminSteps;

import entities.Category;
import net.thucydides.core.steps.ScenarioSteps;
import utils.XmlParser;

public class ServicePricePageSteps extends ScenarioSteps {

    private ServicePricePage servicePricePage;

    public void addServicePrice(Category category) {
        servicePricePage.openPage();
        servicePricePage.clickAdd();
        servicePricePage.selectCategory(category.getSystemId());
        servicePricePage.enterServiceName("Autotest");
        servicePricePage.clickSave();
    }
}
