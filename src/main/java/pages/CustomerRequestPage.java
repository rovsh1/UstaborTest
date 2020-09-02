package pages;

import entities.User;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CustomerRequestPage extends BasePage {

    @FindBy(xpath = "//a[@class='btn-more']")
    private WebElementFacade openRequestBtn;

    @FindBy(xpath = "//div[@class='nav']//a[contains(@href, 'masters')]")
    private WebElementFacade assignedMastersBtn;

    @FindBy(xpath = "//div[contains(@class, 'request-masters')]//div[contains(@class,'item')]//a[@class='phone']")
    private List<WebElementFacade> mastersOffers;

    @FindBy(xpath = "//div[@class='msg icon']")
    private WebElementFacade masterMessage;

    @FindBy(xpath = "//div[@class='price icon']")
    private WebElementFacade masterOffer;

    @FindBy(xpath = "//div[@class='btn-menu']")
    private WebElementFacade menuBtn;

    @FindBy(xpath = "//a[@class='remove']")
    private WebElementFacade hideOffer;

    @FindBy(xpath = "//li[@data-tab='tab-hidden']")
    private WebElementFacade hiddenOffersBtn;

    public void openRequest() {
        openRequestBtn.click();
    }

    public void openAssignedMasters() {
        assignedMastersBtn.click();
    }

    public void verifyMasterAssigned(User master) {
        var mastersPhones = mastersOffers.stream().map(WebElementFacade::getText).collect(Collectors.toList());
        assertThat(mastersPhones).contains(master.getPhoneNumber());
    }

    public void verifySmsText(String smsTestMessage) {
        masterMessage.shouldContainText(smsTestMessage);
    }

    public void verifyMasterOffer(String offerPrice) {
        assertThat(masterOffer.getText().replaceAll("[^0-9.]", "")).isEqualTo(offerPrice);
    }

    public void clickHideMasterOffer() {
        evaluateJavascript("document.getElementsByClassName('remove')[0].click();");
    }

    public void verifyActiveOffersTableIsEmpty() {
        try {
            assertThat(mastersOffers.isEmpty()).isTrue();
        } catch (NoSuchElementException ignored) {

        }
    }

    public void clickHiddenOffersBtn() {
        hiddenOffersBtn.click();
    }

    public void verifyHiddenOfferIsPresent() {
        assertThat(mastersOffers).isNotEmpty();
    }
}
