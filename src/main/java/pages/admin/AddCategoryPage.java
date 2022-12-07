package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class AddCategoryPage extends BaseAdminPage {

    @FindBy(xpath = "//input[contains(@name, 'data[name]')]")
    private WebElementFacade name;

    @FindBy(xpath = "//input[contains(@name, 'data[urlname]')]")
    private WebElementFacade url;

    @FindBy(xpath = "//select[@id='form_data_site_id']")
    private WebElementFacade domainSelect;

    @FindBy(xpath = "//li[@data-tab='tab-content']")
    private WebElementFacade contentTab;

    @FindBy(xpath = "//input[contains(@name, 'meta[title]')]")
    private WebElementFacade title;

    @FindBy(xpath = "//form//button[@type='submit']")
    private WebElementFacade submit;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "reference/category/create");
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

    public void selectDomain() {
        domainSelect.selectByValue("1");
    }

    public void openContentTab() {
        contentTab.click();
    }

    public void enterTitle(String name) {
        title.sendKeys(name);
    }
}
