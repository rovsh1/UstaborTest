package pages.admin;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DefaultUrl("http://s660022689.onlinehome.us/master/")
public class MastersPage extends PageObject {

    @FindBy(xpath = "//a[contains(@href,'master/view')]")
    private List<WebElementFacade> mastersList;

    @FindBy(xpath = "//input[@id='form_balance_sum']")
    private WebElementFacade amountInput;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElementFacade addMoneyBtn;

    public void openMasterProfileByName(String masterLastName) {
        mastersList.stream()
                .filter(master -> master.containsText(masterLastName))
                .findFirst()
                .get()
                .click();
    }

    public void addMoneyToAccount(int amount) {
        amountInput.sendKeys(String.valueOf(amount));
        addMoneyBtn.click();
    }
}
