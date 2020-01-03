package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SiteMapPage extends BasePage{

    @FindBy(xpath = "//div[@class='page-sitemap']/div//a[text()]")
    private List<WebElementFacade> urlsList;

    public void openRandomUrl() {
        urlsList.get(new Random().nextInt(10)).click();
    }
}
