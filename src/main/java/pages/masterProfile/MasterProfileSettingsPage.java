package pages.masterProfile;

import entities.CategoryCheckbox;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MasterProfileSettingsPage extends MasterProfilePage {

    @FindBy(xpath = "//input[@id='form_user_presentation']")
    private WebElementFacade userNameInput;

    @FindBy(xpath = "//div[contains(@class, 'field-categories')]//div[contains(@class, 'field-checkbox')]")
    private List<WebElementFacade> categories;

    @FindBy(xpath = "//a[@id='button-password']")
    private WebElementFacade changePasswordBtn;

    @FindBy(xpath = "//form[@id='form-password']//input[@id='module_site_forms_profile_password_password_password']")
    private WebElementFacade newPasswordInput;

    @FindBy(xpath = "//form[@id='form-password']//input[@id='module_site_forms_profile_password_password_password_confirm']")
    private WebElementFacade confirmPasswordInput;

    @FindBy(xpath = "//form[@id='form-password']//button[@type='submit']")
    private WebElementFacade saveNewPasswordBtn;

    @FindBy(xpath = "//div[@class='window-body']")
    private WebElementFacade successPopup;

    @FindBy(xpath = "//form/h3//button[@type='submit']")
    private WebElementFacade saveChangesBtn;

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

    public void editUsername(String username) {
        userNameInput.clear();
        userNameInput.sendKeys(username);
    }

    public CategoryCheckbox enableOrDisableRandomCategory() {
        WebElementFacade category = categories.get(new Random().nextInt(5));
        category.findElement(By.xpath(".//label")).click();

        CategoryCheckbox checkbox = new CategoryCheckbox();
        checkbox.setName(category.getText());
        checkbox.setEnabled(category.findElement(By.xpath(".//input")).isSelected());

        return checkbox;
    }

    public void saveChanges() {
        saveChangesBtn.click();
    }
}
