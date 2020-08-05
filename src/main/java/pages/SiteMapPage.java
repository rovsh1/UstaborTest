package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SiteMapPage extends BasePage{

    @FindBy(xpath = "//div[@class='page-sitemap']/div//a[text()]")
    private List<WebElementFacade> urlsList;

    public void openRandomUrl() {
        WebElementFacade url;

        if (urlsList.size() <= 10) {
            url = urlsList.get(new Random().nextInt((urlsList.size())));
        } else {
            url = urlsList.get(new Random().nextInt((urlsList.size() - 10) + 1) + 10);
        }

        focusElementJS(url);
        url.click();
    }
}
