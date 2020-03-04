package pages.admin;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("http://s660022689.onlinehome.us/promotion/")
public class PromotionPage extends PageObject {

    private static String approveXpath = "//tr[./td[text()='%s']]//a[contains(@href, 'enable')]";

    public void approvePromotionByProjectSystemId(String systemId) {
        getDriver().findElement(By.xpath(String.format(approveXpath, systemId))).click();
    }
}
