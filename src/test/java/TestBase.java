import annotations.AddCategory;
import annotations.AddMasters;
import entities.Master;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TestBase {

    public final TestCategory category;
    public List<Master> masters = new ArrayList<>();

    @Rule
    public Watcher watcher = new Watcher();

    @Steps
    UserSteps user;

    @Steps
    AdminSteps admin;

    @Managed
    private WebDriver driver;

    private final boolean isAddCategory;
    private final boolean isAddMasters;
    private int mastersCount = 0;

    TestBase() {
        isAddCategory = this.getClass().isAnnotationPresent(AddCategory.class);
        isAddMasters = this.getClass().isAnnotationPresent(AddMasters.class);
        if (isAddMasters) {
            mastersCount = this.getClass().getAnnotation(AddMasters.class).value();
        }
        category = new TestCategory();
    }

    @Before
    public void setUp() throws TimeoutException {
        if (isAddCategory) {
            watcher.category = category;
            admin.addTestCategory(category);
            admin.addTestCategoryRequestQuestions(category, getText("Question"), "100", "200");

            if (isAddMasters) {
                for (int i = 0; i < mastersCount; i++) {
                    var master = DataGenerator.getMasterRandomEmail(category);
                    watcher.users.add(master);
                    masters.add(master);

                    user.atHomePage.openHomePage();
                    user.atHomePage.registerAsMasterWithSpecifiedCategory(master);
                    user.atMasterProfilePage.waitForPageIsVisible();
                    master.setProfileId(user.atMasterProfilePage.getProfileId());

                    var project = new Project(master.getCategoryName());
                    user.atMasterProjectsPage.openProjectsTab();
                    user.atMasterProjectsPage.addNewProjectInCategory(project, false, false);
                    user.atHomePage.logsOut();
                }
            }
        }

        Serenity.throwExceptionsImmediately();

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
