package pages;

import entities.User;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CustomerRequestPage extends BasePage {

    @FindBy(xpath = "//a[@class='btn-more']")
    private WebElementFacade openRequestBtn;

    @FindBy(xpath = "//div[@class='nav']//a[contains(@href, 'masters')]")
    private WebElementFacade assignedMastersBtn;

    @FindBy(xpath = "//div[contains(@class, 'request-masters')]//div[@class='item']//a[@class='phone']")
    private List<WebElementFacade> assignedMasters;

    public void openRequest() {
        openRequestBtn.click();
    }

    public void openAssignedMasters() {
        assignedMastersBtn.click();
    }

    public void verifyMasterAssigned(User master) {
        var mastersPhones = assignedMasters.stream().map(WebElementFacade::getText).collect(Collectors.toList());
        assertThat(mastersPhones).contains(master.getPhoneNumber());
    }
}
