package pages.admin;

import org.openqa.selenium.By;
import utils.Config;

public class PromotionPage extends BaseAdminPage {

    private static String approveXpath = "//tr[./td[text()='%s']]//a[contains(@href, 'enable')]";
    private static String promoIdXpath = "//tr[./td[text()='%s']]//td[contains(@class, 'column-id')]";

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "promotion");
    }

    public void approvePromotionByProjectSystemId(String systemId) {
        getDriver().findElement(By.xpath(String.format(approveXpath, systemId))).click();
    }

    public String getProjectPromoId(String systemId) {
        return getDriver().findElement(By.xpath(String.format(promoIdXpath, systemId))).getText();
    }
}
