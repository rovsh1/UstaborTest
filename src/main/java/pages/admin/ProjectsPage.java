package pages.admin;

import entities.Project;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

public class ProjectsPage extends BaseAdminPage {

    @FindBy(xpath = "//textarea[@id='form_data_text']")
    private WebElementFacade deleteReason;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElementFacade submitBtn;

    public void deleteProject(Project project) {
        this.openUrl(Config.getAdminUrl() + "/project/delete/" + project.getSystemId());
        deleteReason.sendKeys("Test");
        submitBtn.click();
    }
}
