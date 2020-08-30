package pages.customerProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class CustomerProfileRequestsPage extends CustomerProfileBasePage {

    @FindBy(xpath = "//div[contains(@class, 'requests-list')]/div")
    private List<WebElementFacade> requests;

    @FindBy(xpath = "//span[@class='id']")
    private WebElementFacade requestId;

    public void requestShouldBeVisible() {
        assertThat(requests).isNotEmpty();
    }

    public String getRequestId() {
        return requestId.getText().replaceAll("[^0-9.]", "");
    }
}
