package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class PlaceOrderPage extends BasePage {

    @FindBy(xpath = "//input[@id='form_order_username']")
    private WebElementFacade nameInput;

    @FindBy(xpath = "//select[@id='form_order_site_id']")
    private WebElementFacade domainDropdown;

    @FindBy(xpath = "//select[@id='form_order_category_id']")
    private WebElementFacade categoryDropdown;

    @FindBy(xpath = "//div[@class='dropzone-field']")
    private WebElementFacade photoForm;

    @FindBy(xpath = "//textarea[@id='form_order_text']")
    private WebElementFacade additionalInfoInput;

    public void nameInputShouldBeVisible() {
        nameInput.shouldBeVisible();
    }

    public void domainDropdownShouldBeVisible() {
        domainDropdown.shouldBeVisible();
    }

    public void categoryDropdownShouldBeVisible() {
        categoryDropdown.shouldBeVisible();
    }

    public void photoFormShouldBeVisible() {
        photoForm.shouldBeVisible();
    }

    public void additionalInfoFormShouldBeVisible() {
        additionalInfoInput.shouldBeVisible();
    }
}
