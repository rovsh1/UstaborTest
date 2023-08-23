package steps.adminSteps;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.admin.BaseAdminPage;
import utils.Config;

public class ServicePricePage extends BaseAdminPage {

    @FindBy(xpath = "//select[@id='form_data_category_id']")
    private WebElementFacade selectCategory;

    @FindBy(xpath = "//a[contains(@href, 'price-services/create')]")
    private WebElementFacade addNew;

    @FindBy(xpath = "//input[@id='form_data_name']")
    private WebElementFacade nameInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade saveButton;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "price-services");
    }

    public void selectCategory(String id) {
        selectCategory.selectByValue(id);
    }

    public void clickAdd() {
        addNew.click();
    }

    public void enterServiceName(String name) {
        nameInput.sendKeys(name);
    }

    public void clickSave() {
        saveButton.click();
    }
}
