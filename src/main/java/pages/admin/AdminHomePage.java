package pages.admin;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.time.temporal.ChronoUnit;

@DefaultUrl("http://s660022689.onlinehome.us/")
public class AdminHomePage extends PageObject {

    @FindBy(xpath = "//input[@id='form_auth_login']")
    private WebElementFacade loginInput;

    @FindBy(xpath = "//input[@id='form_auth_password']")
    private WebElementFacade passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade loginBtn;

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
        setImplicitTimeout(2, ChronoUnit.SECONDS);
        boolean result = loginInput.isPresent();
        resetImplicitTimeout();
        return result;
    }
}
