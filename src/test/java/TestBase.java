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
            var annotation = this.getClass().getAnnotation(AddCategory.class);

            watcher.category = category;
            admin.addTestCategory(category);

            if (annotation.addServiceQuestion()) {
                admin.addCategoryService(category);
                admin.addServiceQuestions(category, getText("Question"));
                admin.setServicePrices(Config.getCountry(), "100", "200");
            }

            if (annotation.promotionAndClickPrice()) {
                admin.atCategoriesPage.setPromotionAndClickPrice(category.getSystemId(),"100", "200", "1000");
            }
        }

        if (this.getClass().isAnnotationPresent(AddMasters.class)) {
            var anno = this.getClass().getAnnotation(AddMasters.class);

            user.atHomePage.openHomePage();
            setCountryLanguageAndLocation();

            for (int i = 0; i < anno.masters(); i++) {
                var master = DataGenerator.getMaster(category);
                watcher.users.add(master);

                user.atHomePage.openHomePage();
                user.register(master, false);

                if (anno.addProject()) {
                    user.atMasterProjectsPage.openProjectsTab();
                    user.atMasterProjectsPage.addNewProjectInCategory(master.getCategory());
                }

                user.atHomePage.logsOut();
            }

            return;
        }

        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();
    }

    String getText(String key) {
        return XmlParser.getTextByKey(key);
    }

    void setBrowserMobileWindowSize() {
        driver.manage().window().setSize(new Dimension(320, 800));
    }

    private void setCountryLanguageAndLocation() {
        user.atHomePage.setLanguage(Config.getLang());

        if (Config.isUstabor()) {
            user.atHomePage.selectCity(getText(Config.getCountryCode() + "_city"));
            return;
        }

        user.atHomePage.selectLocation(Config.getCountry(), getText(Config.getCountryCode() + "_city"));
    }
}
