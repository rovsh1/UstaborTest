package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class MasterWalletPage extends MasterProfileBasePage{

    @FindBy(xpath = "//div[@class='l']")
    private WebElementFacade transactionsHistorySpoiler;

    public void transactionsHistoryShouldBeVisible() {
        transactionsHistorySpoiler.shouldBeVisible();
    }
}
