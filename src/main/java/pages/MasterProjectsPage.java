package pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterProjectsPage extends BasePage {

    //region Add new project form

    @FindBy(xpath = "//div[@id='btn-project-add']")
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

    @FindBy(xpath = "//form[@id='project-form']//button[@type='submit']")
    private WebElementFacade saveNewProjectBtn;

    @FindBy(xpath = "//input[@type='file']")
    private WebElementFacade fileInput;

    //endregion

    @FindBy(xpath = "//div[@id='master-projects']/a")
    private List<WebElementFacade> projectsList;

    @FindBy(xpath = "//div[@id='master-projects']/a")
    private WebElementFacade projectBtn;

    @FindBy(xpath = "//a[@href='/profile/projects/']")
    private WebElementFacade projectsTab;

    public void clickProjectsTab() {
        scrollPageUpJS();
        projectsTab.click();
    }

    public void openNewProjectForm() {
        addProjectBtn.click();
    }

    public void enterProjectName(String projectName) {
        newProjectNameInput.sendKeys(projectName);
    }

    public void enterProjectDescription(String description) {
        newProjectDescriptionInput.sendKeys(description);
    }

    public void selectProjectCategoryWithPromotion() {
        WebElementFacade category = projectCategories.get(new Random().nextInt(projectCategories.size()));
        projectCategorySelect.selectByValue(category.getValue());
        waitForLoaderDisappears();
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
        noPromotionCheckbox.click();
    }

    public void verifyProjectsListContains(String name) {
        assertThat(projectsList
                .stream()
                .map(WebElementFacade::getText)
                .anyMatch(project -> project.contains(name)))
                .isTrue();
    }

    public void deleteProjectByName(String projectName) {
        WebElementFacade project = projectsList
                .stream()
                .filter(p -> p.getText().contains(projectName))
                .findFirst()
                .orElse(null);

        if (project == null) {
            throw new NullPointerException(String.format("No project with such name: %s ", projectName));
        }

        Actions builder = new Actions(getDriver());
        builder.moveToElement(project)
                .moveToElement(project.findElement(By.xpath("//div[@class='btn btn-remove']")))
                .click().build().perform();
    }

    public void submitProjectDeleting() {
        clickPopupOkBtn();
    }

    public int getCountOfProjects() {
        return projectsList.size();
    }

    public void waitTillProjectsAreVisible() {
        projectBtn.waitUntilVisible().withTimeoutOf(Duration.ofSeconds(10));
        projectBtn.waitUntilClickable().withTimeoutOf(Duration.ofSeconds(10));
    }
}
