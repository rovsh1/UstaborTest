package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class MasterWalletPage extends MasterProfileBasePage{

    @FindBy(xpath = "//div[@class='value master-balance-value']")
    private WebElementFacade balanceText;

    @FindBy(xpath = "//input[@id='form_wallet_sum']")
    private WebElementFacade amountInput;

    @FindBy(xpath = "//select[@id='form_wallet_payment']")
    private WebElementFacade paymentTypeSelector;

    @FindBy(xpath = "//div[@class='l']")
    private WebElementFacade transactionsHistorySpoiler;

    @FindBy(xpath = "//input[@id='wallet_button']")
    private WebElementFacade submitBtn;

    public void balanceAmountShouldBeVisible() {
        balanceText.shouldBeVisible();
    }

    public void amountInputShouldBeVisible() {
        amountInput.shouldBeVisible();
    }

    public void paymentTypeSelectorShouldBeVisible() {
        paymentTypeSelector.shouldBeVisible();
    }

    public void submitBtnShouldBeVisible() {
        submitBtn.shouldBeVisible();
    }

    public void transactionsHistoryShouldBeVisible() {
        transactionsHistorySpoiler.shouldBeVisible();
    }
}
