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
import utils.*;

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
        Admin.getInstance();

        if (!Config.isChrome()) {
            driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
            driver.manage().window().fullscreen();
            driver.manage().window().maximize();
        }

        if (this.getClass().isAnnotationPresent(AddCategory.class)) {
            watcher.category = category;
            admin.addTestCategory(category);
        }

        if (this.getClass().getAnnotation(AddCategory.class).addServiceQuestion()) {
            admin.addCategoryService(category);
            admin.addServiceQuestions(category, getText("Question"));
            admin.setServicePrices(Config.getCountry(), "100", "200");
        }

        if (this.getClass().getAnnotation(AddCategory.class).promotionAndClickPrice()) {
            admin.atCategoriesPage.setPromotionAndClickPrice(category.getSystemId(),"100", "200", "1000");
        }

        if (this.getClass().isAnnotationPresent(AddMasters.class)) {
            user.atHomePage.openHomePage();
            setCountryLanguageAndLocation();

            var mastersCount = this.getClass().getAnnotation(AddMasters.class).masters();
            for (int i = 0; i < mastersCount; i++) {
                var master = DataGenerator.getMaster(category);
                watcher.users.add(master);

                user.atHomePage.openHomePage();
                user.register(master, false);

                if (this.getClass().getAnnotation(AddMasters.class).addProject()) {
                    user.atMasterProjectsPage.openProjectsTab();
                    user.atMasterProjectsPage.addNewProjectInCategory(master.getCategory());
                }

                user.atHomePage.logsOut();
            }
        } else {
            user.atHomePage.openHomePage();
            setCountryLanguageAndLocation();
        }
    }

    String getText(String key) {
        return XmlParser.getTextByKey(key);
    }

    void setBrowserMobileWindowSize() {
        driver.manage().window().setSize(new Dimension(320, 800));
    }

    private void setCountryLanguageAndLocation() {
        if (!Config.isFixListKg()) {
            user.atHomePage.setLanguage(Config.getLang());
        }

        if (!Config.isUstabor() && !Config.isFixListKg()) {
            user.atHomePage.setCountry(Config.getCountry());
        }

        user.atHomePage.selectDefaultLocation();
    }
}
