package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.time.temporal.ChronoUnit;

public class AdminLoginPage extends BaseAdminPage {

    @FindBy(xpath = "//input[@id='form_data_login']")
    private WebElementFacade loginInput;

    @FindBy(xpath = "//input[@id='form_data_password']")
    private WebElementFacade passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade loginBtn;

    public void openPage() {
        var url = (Config.getAdminUrl() + "login?url=%2F");
        getDriver().get(url);
    }

    public void enterLogin(String login) {
        loginInput.sendKeys(login);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
