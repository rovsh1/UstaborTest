package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class MastersPage extends BaseAdminPage {

    @FindBy(xpath = "//a[@id='btn-master-balance']")
    private WebElementFacade addMoneyBnt;

    @FindBy(xpath = "//input[@id='form_data_sum']")
    private WebElementFacade amountInput;

    @FindBy(xpath = "//div[./button[@data-action='close']]//button[@type='submit']")
    private WebElementFacade submitBtn;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade saveBtn;

    @FindBy(xpath = "//div[@class='multiselect']")
    private WebElementFacade badgesSelector;

    @FindBy(xpath = "//div[contains(@class, 'multiselect')]//span[@class='select']")
    private WebElementFacade selectAll;


    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "master");
    }

    public void openMasterPageByDirectUrl(String masterId) {
        getDriver().get(Config.getAdminUrl() + String.format("master/%s", masterId));
    }

    public void openEditMasterPage(String masterId) {
        getDriver().get(Config.getAdminUrl() + String.format("master/%s/edit", masterId));
    }

    public void addMoneyToAccount(int amount) {
        scrollPageToBottom();
        scrollIntoView(addMoneyBnt);
        addMoneyBnt.click();
        amountInput.sendKeys(String.valueOf(amount));
        submitBtn.click();
        waitForLoaderDisappears();
    }

    public void addAllBadgesToMaster() {
        badgesSelector.click();
        selectAll.click();
        saveBtn.click();
    }
}
