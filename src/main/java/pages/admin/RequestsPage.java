package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class RequestsPage extends BaseAdminPage {

    @FindBy(xpath = "//a[@id='btn-master-find']")
    private WebElementFacade assignMasterBtn;

    @FindBy(xpath = "//form//input[@class='ui-autocomplete-input']")
    private WebElementFacade masterNameInput;

    @FindBy(xpath = "//div[@class='exists-block']//div[@class='menu-btn']")
    private WebElementFacade actionsBtn;

    @FindBy(xpath = "//a[@data-action='add']")
    private WebElementFacade freeAdd;


    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitMasterAssignBtn;

    @FindBy(xpath = "//ul[contains(@class, 'ui-autocomplete')]/li")
    private WebElementFacade autocompleteSuggestion;

    @FindBy(xpath = "//select[@id='form_data_type']")
    private WebElementFacade assignToDropDown;

    @FindBy(xpath = "//input[@id='form_data_payment']")
    private WebElementFacade withdrawContactPrice;

    @FindBy(xpath = "//div[@class='btn-menu']")
    private WebElementFacade menuBtn;

    @FindBy(xpath = "//a[@class='delete']")
    private WebElementFacade deleteBtn;

    @FindBy(xpath = "//a[@id='btn-request-delete']")
    private WebElementFacade prodDeleteBtn;

    @FindBy(xpath = "//textarea[@id='form_data_note']")
    private WebElementFacade prodDeleteReason;

    @FindBy(xpath = "//button[contains(@class, 'button-submit')]")
    private WebElementFacade submitBtn;

    public void openPage(String requestId) {
        getDriver().get(Config.getAdminUrl() + String.format("request/%s",requestId));
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
    }

    public void submitMasterAssign() {
        submitMasterAssignBtn.click();
    }

    public void setAssignToAllMasters() {
        assignToDropDown.selectByValue("category");
    }

    public void setWithdrawContactPrice() {
        withdrawContactPrice.click();
    }

    public void deleteRequest() {
        prodDeleteBtn.click();
        prodDeleteReason.sendKeys("test");
        submitBtn.click();
    }

    public void assignForFree() {
        actionsBtn.click();
        freeAdd.click();
    }
}
