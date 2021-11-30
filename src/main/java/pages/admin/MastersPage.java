package pages.admin;

import entities.Master;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.List;
import java.util.stream.Collectors;

public class MastersPage extends BaseAdminPage {

    @FindBy(xpath = "//a[contains(@href,'master/view')]")
    private List<WebElementFacade> mastersList;

    @FindBy(xpath = "//a[@id='btn-master-balance']")
    private WebElementFacade addMoneyBnt;

    @FindBy(xpath = "//input[@id='form_balance_sum']")
    private WebElementFacade amountInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade submitBtn;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElementFacade saveBtn;

    @FindBy(xpath = "//div[@title='Actions']")
    private WebElementFacade actionsBtn;
    
    @FindBy(xpath = "//a[@class='edit']")
    private WebElementFacade editMasterBtn;

    @FindBy(xpath = "//input[contains(@id, 'form_data_badges')]")
    private List<WebElementFacade> badges;

    @FindBy(xpath = "//input[@id='quicksearch']")
    private WebElementFacade searchForm;

    @FindBy(xpath = "//form[@class='quicksearch']/button")
    private WebElementFacade searchButton;

    @FindBy(xpath = "//table//tr/td[2]")
    private List<WebElementFacade> mastersIds;

    private final String VIEWMASTERURL = "master/user/view/";
    private final String EDITMASTERURL = "master/user/edit/";

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "master");
    }

    public void openMasterPageByDirectUrl(String masterId) {
        getDriver().get(Config.getAdminUrl() + VIEWMASTERURL + masterId);
    }

    public void openEditMasterPageByDirectUrl(String masterId) {
        getDriver().get(Config.getAdminUrl() + EDITMASTERURL + masterId);
    }

    public void openMasterProfileByName(String masterLastName) {
        mastersList.stream()
                .filter(master -> master.containsText(masterLastName))
                .findFirst()
                .get()
                .click();
    }

    public void addMoneyToAccount(int amount) {
        scrollPageToBottom();
        scrollIntoView(addMoneyBnt);
        addMoneyBnt.click();
        amountInput.sendKeys(String.valueOf(amount));
        submitBtn.click();
        waitForLoaderDisappears();
    }

    public void openEditMasterPage() {
        actionsBtn.click();
        editMasterBtn.click();
    }

    public void addAllBadgesToMaster(Master master) {
        badges.forEach(WebElementFacade::click);
        master.setCountOfBadges(badges.size() - 2);
        saveBtn.click();
    }

    public void deleteMaster(String profileId) {
        getDriver().get(Config.getAdminUrl() + "master/delete/" + profileId);
    }

    public void performSearch(String text) {
        openPage();
        searchForm.sendKeys(text);
        searchButton.click();
    }

    public List<String> getMastersIds() {
        return mastersIds.stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }
}
