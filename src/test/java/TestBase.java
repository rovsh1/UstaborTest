import annotations.AddCategory;
import annotations.AddMasters;
import entities.Category;
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

import java.util.concurrent.TimeoutException;

public class TestBase {

    public final Category category = new Category();

    @Rule
    public Watcher watcher = new Watcher();

    @Steps
    UserSteps user;

    @Steps
    AdminSteps admin;

    @Managed
    public WebDriver driver;

    @Before
    public void setUp() throws TimeoutException {
        Serenity.throwExceptionsImmediately();
        if (!Config.isChrome()) {
            driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
            driver.manage().window().fullscreen();
            driver.manage().window().maximize();
        }

        if (this.getClass().isAnnotationPresent(AddCategory.class)) {
            watcher.category = category;
            admin.addTestCategory(category);

            if (this.getClass().getAnnotation(AddCategory.class).addRequestQuestion()) {
                admin.addCategoryService(category);
                admin.setRequestPrices(category, Config.getCountry(), "100", "200");
                admin.addServiceRequestQuestions(category, getText("Question"));
            }

            if (this.getClass().getAnnotation(AddCategory.class).promotionAndClickPrice()) {
                admin.atCategoriesPage.setPromotionAndClickPrice(
                        category.getSystemId(),
                        "100",
                        "200",
                        "1000");
            }

            if (this.getClass().isAnnotationPresent(AddMasters.class)) {
                user.atHomePage.openHomePage();
                setCountryAndLanguage();

                var mastersCount = this.getClass().getAnnotation(AddMasters.class).masters();
                for (int i = 0; i < mastersCount; i++) {
                    var master = DataGenerator.getMaster(category);
                    watcher.users.add(master);

                    user.atHomePage.openHomePage();
                    user.registerAsMaster(master);

                    if (this.getClass().getAnnotation(AddMasters.class).addProject()) {
                        user.atMasterProjectsPage.openProjectsTab();
                        user.atMasterProjectsPage.addNewProjectInCategory(master.getCategory());
                    }

                    user.atHomePage.logsOut();
                }
            }
        } else {
            user.atHomePage.openHomePage();
            user.atHomePage.homePageShouldBeVisible();
            setCountryAndLanguage();
        }
    }

    String getText(String key) {
        return XmlParser.getTextByKey(key);
    }

    void setBrowserWindowSize(int width, int height){
        driver.manage().window().setSize(new Dimension(width, height));
    }

    private void setCountryAndLanguage() {
        if (!Config.isFixListKg()) {
            user.atHomePage.setLanguage(Config.getLang());
        }

        if (!Config.isUstabor() && !Config.isFixListKg()) {
            user.atHomePage.setCountry(Config.getCountry());
        }
    }
}
