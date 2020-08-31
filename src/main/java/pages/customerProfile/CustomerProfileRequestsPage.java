package pages.customerProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.Config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class CustomerProfileRequestsPage extends CustomerProfileBasePage {

    @FindBy(xpath = "//div[contains(@class, 'requests-list')]/div")
    private List<WebElementFacade> requests;

    @FindBy(xpath = "//span[@class='id']")
    private WebElementFacade requestId;

    @FindBy(xpath = "//div[@class='btn-menu menu-wrap']")
    private WebElementFacade additionalOptionsBtn;

    @FindBy(xpath = "//a[@class='delete']")
    private WebElementFacade deleteBtn;

    public void requestShouldBeVisible() {
        assertThat(requests).isNotEmpty();
    }

    public String getRequestId() {
        return requestId.getText().replaceAll("[^0-9.]", "");
    }

    public void openPage() {
        getDriver().get(Config.getFullUrl() + "/customer/requests/");
    }

    public void deleteRequest() {
        var actions = new Actions(getDriver());
        actions.moveToElement(additionalOptionsBtn).moveToElement(deleteBtn).click();
    }

    public void verifyRequestsTabIsEmpty() {
        assertThat(requests).isEmpty();
    }
}
