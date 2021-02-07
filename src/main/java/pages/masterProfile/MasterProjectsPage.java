package pages.masterProfile;

import entities.Project;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MasterProjectsPage extends MasterProfileBasePage {

    //region Add new project form

    @FindBy(xpath = "//div[@id='master-projects']/div[@class='item new']")
    private WebElementFacade addProjectBtn;

    @FindBy(xpath = "//input[@id='form_project_name']")
    private WebElementFacade newProjectNameInput;

    @FindBy(xpath = "//textarea[@id='form_project_text']")
    private WebElementFacade newProjectDescriptionInput;

    @FindBy(xpath = "//select[@id='form_project_category_id']")
    private WebElementFacade projectCategorySelect;

    @FindBy(xpath = "//select[@id='form_project_category_id']/option")
    private List<WebElementFacade> projectCategories;

    @FindBy(xpath = "//label[@for='checkbox-free-input']")
    private WebElementFacade noPromotionCheckbox;

    @FindBy(xpath = "//div[contains(@class, 'recommended')]//div[@class='price']")
    private WebElementFacade recommendedPrice;

    @FindBy(xpath = "//div[@class='promotion-select']//div[contains(@class, 'item min')]//div[@class='price']")
    private WebElementFacade minimalPrice;

    @FindBy(xpath = "//div[contains(@class, 'project-form')]//button[@class='button-submit']")
    private WebElementFacade saveNewProjectBtn;

    @FindBy(xpath = "//input[@type='file' and @class=' field-image 1']")
    private WebElementFacade fileInput;

    //endregion

    @FindBy(xpath = "//div[@id='master-projects']/div[@class='item']")
    private List<WebElementFacade> projectsList;

    @FindBy(xpath = "//div[@id='master-projects']/a")
    private WebElementFacade projectBtn;

    public void openNewProjectForm() {
        addProjectBtn.click();
    }

    public void enterProjectName(String projectName) {
        newProjectNameInput.sendKeys(projectName);
    }

    public void enterProjectDescription(String description) {
        newProjectDescriptionInput.sendKeys(description);
    }

    public void selectCategory(String category) {
        projectCategorySelect.selectByVisibleText(category);
        waitForLoaderDisappears();
    }

    public void uploadProjectImage() {
        fileInput.sendKeys(new File("files/projectImage.jpg").getAbsolutePath());
    }

    public void addProjectImage() {
        try {
            String html = Files.readString(
                    new File("files/project_image.js").toPath());
            evaluateJavascript(html);
            fileInput.sendKeys(new File("files/projectImage.jpg").getAbsolutePath());
        } catch (IOException e) {
            Serenity.throwExceptionsImmediately();
            e.printStackTrace();
        }
    }

    public void saveNewProject() {
        saveNewProjectBtn.click();
    }

    public void selectNoPromotionCheckbox() {
        focusElementJS(noPromotionCheckbox);
        noPromotionCheckbox.click();
    }

    public void selectNoPromotionCheckboxIfPresent() {
        setTimeouts(3, ChronoUnit.SECONDS);
        if (noPromotionCheckbox.isPresent()) {
            focusElementJS(noPromotionCheckbox);
            noPromotionCheckbox.click();
        }
        resetTimeouts();
    }

    public int getCountOfProjects() {
        setTimeouts(1, ChronoUnit.SECONDS);
        var count =  projectsList.size();
        resetTimeouts();

        return count;
    }

    public void waitTillProjectsAreVisible() {
        if (Config.isUstabor()) {
            waitForLoaderDisappears();
        }
        projectBtn.waitUntilVisible().withTimeoutOf(Duration.ofSeconds(60));
        projectBtn.waitUntilClickable().withTimeoutOf(Duration.ofSeconds(60));
    }

    public void addProjectBtnShouldBeVisible() {
        addProjectBtn.shouldBeVisible();
    }

    public void selectRecommendedPrice() {
        recommendedPrice.click();
    }

    public void getProjectSystemId(Project project) {
        WebElementFacade element = projectsList.stream()
                .filter(x -> x.getText().contains(project.getName()))
                .findFirst()
                .get();

        project.setSystemId(element.getAttribute("href").replaceAll("\\D", ""));
    }

    public void selectMinimalPrice() {
//        focusElementJS(minimalPrice);
        minimalPrice.click();
    }
}
