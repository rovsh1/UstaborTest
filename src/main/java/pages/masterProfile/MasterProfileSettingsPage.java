package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class MasterProfileSettingsPage extends BasePage {

    @FindBy(xpath = "//a[@id='button-password']")
    private WebElementFacade changePasswordBtn;

    @FindBy(xpath = "//form[@id='form-password']//input[@id='form_password_password']")
    private WebElementFacade newPasswordInput;

    @FindBy(xpath = "//form[@id='form-password']//input[@id='form_password_password_confirm']")
    private WebElementFacade confirmPasswordInput;

    @FindBy(xpath = "//form[@id='form-password']//button[@type='submit']")
    private WebElementFacade saveNewPasswordBtn;

    @FindBy(xpath = "//div[@class='window-body']")
    private WebElementFacade successPopup;

    @FindBy(xpath = "//div[@class='button-close']")
    private WebElementFacade closePopupBtn;

    public void clickChangePasswordBtn() {
        changePasswordBtn.click();
    }

    public void enterNewPassword(String text) {
        newPasswordInput.sendKeys(text);
    }

    public void confirmNewPassword(String text) {
        confirmPasswordInput.sendKeys(text);
    }

    public void clickSaveNewPasswordBtn() {
        saveNewPasswordBtn.click();
    }

    public void successPopupShouldBeVisible() {
        successPopup.shouldBeVisible();
    }

    public void closePopup() {
        closePopupBtn.click();
    }
}
