import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import steps.UserSteps;
import steps.adminSteps.AdminSteps;
import utils.Config;
import utils.DataGenerator;
import utils.XmlParser;

import java.util.ArrayList;
import java.util.List;

class TestBase {

    DataGenerator data;

    @Managed
    private WebDriver driver;

    @Steps
    UserSteps user;

    @Steps
    AdminSteps admin;

    @Before
    public void setUp() {
        Serenity.throwExceptionsImmediately();
        data = new DataGenerator();
        driver.manage().window().maximize();

        user.atHomePage.open();

        user.atHomePage.homePageShouldBeVisible();

        if (!Config.isUstabor() && Config.getCountry() != null) {
            user.atHomePage.goToDomainWithName(Config.getCountry());
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
