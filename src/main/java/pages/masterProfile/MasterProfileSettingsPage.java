package pages.masterProfile;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class MasterProfileSettingsPage extends MasterProfilePage {

    @FindBy(xpath = "//input[@id='form_user_presentation']")
    private WebElementFacade userNameInput;


    @FindBy(xpath = "//a[@id='button-password']")
    private WebElementFacade changePasswordBtn;

    @FindBy(xpath = "//form[@id='form-password']//input[@name='password[password]']")
    private WebElementFacade newPasswordInput;

    @FindBy(xpath = "//form[@id='form-password']//input[@name='password[password_confirm]']")
    private WebElementFacade confirmPasswordInput;

    @FindBy(xpath = "//form[@id='form-password']//button[@type='submit']")
    private WebElementFacade saveNewPasswordBtn;

    @FindBy(xpath = "//div[@class='window-body']")
    private WebElementFacade successPopup;

    @FindBy(xpath = "//form/h3//button[@type='submit']")
    private WebElementFacade saveChangesBtn;

    public void openPasswordFormIfNeeded() {
        setTimeouts(3, SECONDS);
        if (newPasswordInput.isVisible()) {
            resetTimeouts();
            return;
        }

        resetTimeouts();
        scrollPageToBottom();
        focusElementJS(changePasswordBtn);
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

    public void editUsername(String username) {
        userNameInput.clear();
        userNameInput.sendKeys(username);
    }

    public void saveChanges() {
        saveChangesBtn.click();
    }
}
