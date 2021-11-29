package pages.customerProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CustomerProfileBasePage extends BasePage {

    @FindBy(xpath = "//span[@class='ID']")
    private WebElementFacade customerProfileId;

    @FindBy(xpath = "//a[contains(@href, 'info')]")
    private WebElementFacade personalInfoBtn;

    public void openPersonalInfo() {
        personalInfoBtn.click();
    }

    public String getCustomerId() {
        return customerProfileId.getText().replaceAll("[^0-9.]", "");
    }
}
