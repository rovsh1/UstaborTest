import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import steps.UserSteps;
import steps.adminSteps.AdminSteps;
import utils.Config;
import utils.DataGenerator;
import utils.Watcher;
import utils.XmlParser;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class TestBase {

    DataGenerator data;

    @Managed
    private WebDriver driver;

    @Steps
    UserSteps user;

    @Steps
    AdminSteps admin;

    @Rule
    public Watcher watcher = new Watcher();

    @Before
    public void setUp() throws TimeoutException {
        Serenity.throwExceptionsImmediately();
        data = new DataGenerator();

        if (!Config.isChrome()) {
            driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
            driver.manage().window().fullscreen();
        }

        user.atHomePage.openHomePage();
        user.atHomePage.homePageShouldBeVisible();

        user.atHomePage.setLanguage(Config.getLang());
        if (!Config.isUstabor()) {
            user.atHomePage.setCountry(Config.getCountry());
        }
    }

    String getText(String key) {
        return XmlParser.getTextByKey(key);
    }

    List<String> getTextByPredicate(String predicate) {
        return XmlParser.getAllValuesByPredicate(predicate);
    }

    void setBrowserWindowSize(int width, int height){
        driver.manage().window().setSize(new Dimension(width, height));
    }

    void browserGoBack() {
        driver.navigate().back();
    }
}
