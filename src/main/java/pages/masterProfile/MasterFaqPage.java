package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MasterFaqPage extends MasterProfileBasePage{

    @FindBy(xpath = "//div[@class='master-faq']//div[@class='item']//a[@class='button-submit']")
    private List<WebElementFacade> faqItemsSubmitButtons;

    public void verifyFaqItems() {
        faqItemsSubmitButtons.forEach(WebElementFacade::shouldBeVisible);
    }
}
