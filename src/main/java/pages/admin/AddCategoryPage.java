package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class AddCategoryPage extends BaseAdminPage {

    @FindBy(xpath = "//input[@id='form_data_name']")
    private WebElementFacade name;

    @FindBy(xpath = "//input[@id='form_data_urlname']")
    private WebElementFacade url;

    @FindBy(xpath = "//form//input[@type='submit']")
    private WebElementFacade submit;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "category/edit/new/");
    }

    public void enterName(String name) {
        this.name.sendKeys(name);
    }

    public void enterUrl(String url) {
        this.url.sendKeys(url);
    }

    public void clickSubmit() {
        submit.click();
    }
}
