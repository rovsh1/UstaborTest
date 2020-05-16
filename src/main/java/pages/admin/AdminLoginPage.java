package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class AdminLoginPage extends BaseAdminPage {

    @FindBy(xpath = "//input[@id='form_auth_login']")
    private WebElementFacade loginInput;

    @FindBy(xpath = "//input[@id='form_auth_password']")
    private WebElementFacade passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade loginBtn;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "account/auth/");
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

    public void openPageFromConfigUrl() {
        getDriver().get(Config.getAdminUrl());
    }

    public boolean isLoginInputVisible() {
        setImplicitTimeout(1, ChronoUnit.SECONDS);
        boolean result = loginInput.isPresent();
        resetImplicitTimeout();
        return result;
    }
}
