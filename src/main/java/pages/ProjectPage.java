package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class ProjectPage extends BasePage {

    @FindBy(xpath = "//div[@id='button-favorite']")
    private WebElementFacade addToFavoriteBtn;

    public void addProjectToFavorites() {
        addToFavoriteBtn.click();
    }
}
