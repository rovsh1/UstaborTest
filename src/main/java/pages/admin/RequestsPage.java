package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.List;

public class RequestsPage extends BaseAdminPage {

    @FindBy(xpath = "//a[@id='btn-master-add']")
    private WebElementFacade assignMasterBtn;

    @FindBy(xpath = "//div[contains(@class, 'field-master_id')]//input[@class='ui-autocomplete-input']")
    private WebElementFacade masterNameInput;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitMasterAssignBtn;

    @FindBy(xpath = "//ul[contains(@class, 'ui-autocomplete')]/li")
    private WebElementFacade autocompleteSuggestion;

    public void openPage(String requestId) {
        getDriver().get(Config.getAdminUrl() + String.format("request/view/%s/",requestId));
    }

    public void verifyCustomerLogin(String firstName) {
    }

    public void verifyCategory(String name) {
    }

    public void verifyWhatToDoQuestion(String question) {
    }

    public void verifyCustomerName(String firstName) {
    }

    public void verifyAddress(String city) {
    }

    public void verifyPhoneNumber(String phoneNumber) {
    }

    public void openAssignMasterForm() {
        assignMasterBtn.click();
    }

    public void findMaster(String masterId) {
        masterNameInput.sendKeys(masterId);
        autocompleteSuggestion.waitUntilPresent();
    }

    public void submitMasterAssign() {
        submitMasterAssignBtn.click();
    }
}
