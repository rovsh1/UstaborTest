import annotations.AddCategory;
import annotations.AddMasters;
import entities.Project;
import entities.TestCategory;
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

    public final TestCategory category = new TestCategory();

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
        }

        if (this.getClass().isAnnotationPresent(AddCategory.class)) {
            watcher.category = category;
            admin.addTestCategory(category);

            if (this.getClass().getAnnotation(AddCategory.class).addRequestQuestion()) {
                admin.addTestCategoryRequestQuestions(category, getText("Question"), "100", "200");
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
                user.atHomePage.setLanguage(Config.getLang());
                if (!Config.isUstabor()) {
                    user.atHomePage.setCountry(Config.getCountry());
                }
                var mastersCount = this.getClass().getAnnotation(AddMasters.class).masters();
                for (int i = 0; i < mastersCount; i++) {
                    var master = DataGenerator.getMasterRandomEmail(category);
                    watcher.users.add(master);

                    user.atHomePage.openHomePage();
                    user.atHomePage.registerAsMasterWithSpecifiedCategory(master);
                    user.atMasterProfilePage.waitForPageIsVisible();
                    master.setProfileId(user.atMasterProfilePage.getProfileId());

                    if (this.getClass().getAnnotation(AddMasters.class).addProject()) {
                        var project = new Project(master.getCategoryName());
                        user.atMasterProjectsPage.openProjectsTab();
                        user.atMasterProjectsPage.addNewProjectInCategory(project, false, false);
                    }
                    user.atHomePage.logsOut();
                }
            }
        } else {
            user.atHomePage.openHomePage();
            user.atHomePage.homePageShouldBeVisible();

            user.atHomePage.setLanguage(Config.getLang());
            if (!Config.isUstabor()) {
                user.atHomePage.setCountry(Config.getCountry());
            }
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
