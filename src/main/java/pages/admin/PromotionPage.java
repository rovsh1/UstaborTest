package pages.admin;

import org.openqa.selenium.By;
import utils.Config;

public class PromotionPage extends BaseAdminPage {

    private static String approveXpath = "//tr[./td[text()='%s']]//a[contains(@href, 'enable')]";

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "promotion");
    }

    public void approvePromotionByProjectSystemId(String systemId) {
        getDriver().findElement(By.xpath(String.format(approveXpath, systemId))).click();
    }
}
