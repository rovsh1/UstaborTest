package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MasterProfileRequestsPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, '/profile/requests')]")
    private WebElementFacade requestsTab;

    @FindBy(xpath = "//span[@class='id']")
    private WebElementFacade requestIdForm;

    public void openPage() {
        requestsTab.click();
    }

    public void verifyRequestId(String requestId) {
        assertThat(requestIdForm.getText().replaceAll("[^0-9.]", "")).isEqualTo(requestId);
    }
}
