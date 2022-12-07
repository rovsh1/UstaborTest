package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterProfileBasePage extends BasePage {

    @FindBy(xpath = "//a[contains(@class, 'info')]")
    private WebElementFacade personalInfoTab;

    @FindBy(xpath = "//a[contains(@class, 'projects')]")
    private WebElementFacade projectsTab;

    @FindBy(xpath = "//a[contains(@class, 'wallet')]")
    private WebElementFacade walletTab;

    @FindBy(xpath = "//a[contains(@class, 'notification')]")
    private WebElementFacade notificationsTab;

    @FindBy(xpath = "//a[contains(@class, 'promotion')]")
    private WebElementFacade promotionTab;

    @FindBy(xpath = "//a[contains(@class, 'faq')]")
    private WebElementFacade faqTab;

    @FindBy(xpath = "//span[@class='ID']")
    private WebElementFacade profileId;

    @FindBy(xpath = "//span[@class='price-value']")
    private WebElementFacade masterBalance;

    public void projectsTabShouldBeVisible() {
        projectsTab.shouldBeVisible();
    }

    public void openProjectsTab() {
        scrollPageUpJS();
        projectsTab.click();
    }

    public void openWalletTab() {
        scrollPageUpJS();
        walletTab.click();
    }

    public void openNotificationsTab() {
        scrollPageUpJS();
        notificationsTab.click();
    }

    public void openPromotionTab() {
        scrollPageUpJS();
        promotionTab.click();
    }

    public void openFaqTab() {
        scrollPageUpJS();
        faqTab.click();
    }

    public String getProfileId() {
        return profileId.getText().replaceAll("\\D", "");
    }

    public void verifyBalanceAmount(int amount) {
        assertThat(masterBalance.getText().replaceAll("[^0-9.-]", "")).isEqualTo(String.valueOf(amount));
    }
}
