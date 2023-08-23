import annotations.AddCategory;
import annotations.AddMasters;
import entities.Category;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import steps.UserSteps;
import steps.adminSteps.AdminSteps;
import utils.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeoutException;

import static net.serenitybdd.core.Serenity.getDriver;


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
    public void setUp() throws TimeoutException, InterruptedException {
        Config.setAgentNeeded(this.toString().contains("UU293"));
        Serenity.throwExceptionsImmediately();
        Admin.getInstance();

        if (this.getClass().isAnnotationPresent(AddCategory.class)) {
            var annotation = this.getClass().getAnnotation(AddCategory.class);

            watcher.category = category;
            admin.addTestCategory(category);

            if (annotation.addServiceQuestion()) {
                admin.addCategoryService(category, annotation.addServicePrice());

                for(int i = 0; i < annotation.questionsCount(); i++) {
                    if (i == 0) {
                        admin.addServiceQuestions(category, getText("Question_0"));
                        continue;
                    }
                    var question = String.format("Question_%d", i);
                    admin.addSubQuestion(getText(question));
                    Thread.sleep(500);
                    admin.addSubQuestion(getText(question));
                    Thread.sleep(500);
                }

                admin.setServicePrices(Config.getCountry(), "100", "200");
                admin.atServicePricesPage.addServicePrice(category);
            }

            if (annotation.promotionAndClickPrice()) {
                admin.atCategoriesPage.setPromotionAndClickPrice(
                        category.getSystemId(),
                        "100",
                        "200",
                        "1000");
            }
        }

        if (this.getClass().isAnnotationPresent(AddMasters.class)) {
            var anno = this.getClass().getAnnotation(AddMasters.class);

            if (anno.useAdminSite()) {
                for (int i = 0; i < anno.masters(); i++) {
                    var master = DataGenerator.getMaster(category);
                    watcher.users.add(master);
                    admin.addMaster(master);
                    Thread.sleep(500);
                }
                return;
            }

            user.atHomePage.openHomePage();

            for (int i = 0; i < anno.masters(); i++) {
                setCountryLanguageAndLocation();
                var master = DataGenerator.getMaster(category);
                watcher.users.add(master);

                user.atHomePage.openHomePage();
                user.register(master, false);

                getDriver().manage().deleteAllCookies();
                user.atHomePage.logsOut();
            }

            setCountryLanguageAndLocation();
            return;
        }

        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();
    }

    String getText(String key) {
        return XmlParser.getTextByKey(key);
    }

    int getTashkentHour() {
        var date = new Date();
        var df = new SimpleDateFormat("HH");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Tashkent"));
        return Integer.parseInt(df.format(date));
    }

    void setBrowserMobileWindowSize() {
        driver.manage().window().setSize(new Dimension(375, 667));
        driver.navigate().refresh();
    }

    void setCountryLanguageAndLocation() {
        user.atHomePage.setLanguage(Config.getLang());

        if (Config.isUstabor() || Config.isBildrlist()) {
            user.atHomePage.selectCity(getText(Config.getCountryCode() + "_city"));
            return;
        }

        user.atHomePage.selectLocation(Config.getCountry(), getText(Config.getCountryCode() + "_city"));
    }
}
