import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import steps.UserSteps;
import utils.Config;
import utils.DataGenerator;
import utils.XmlParser;

class TestBase {

    DataGenerator data;

    @Managed
    private WebDriver driver;

    @Steps
    UserSteps user;

    @Before
    public void setUp() {
        data = new DataGenerator();
        driver.manage().window().maximize();
        user.atHomePage.open();

        user.atHomePage.homePageShouldBeVisible();

        if (Config.getCountry() != null) {
            user.atHomePage.goToDomainWithName(Config.getCountry());
        }
    }

    String getText(String key) {
        return XmlParser.getTextByKey(key);
    }
}
