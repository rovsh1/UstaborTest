package pages.admin;

import org.openqa.selenium.By;
import utils.Config;

public class PromotionPage extends BaseAdminPage {

    private static String approveXpath = "//tr[./td[text()='%s']]//a[contains(@href, 'enable')]";
    private static String promoIdXpath = "//tr[./td[text()='%s']]//td[contains(@class, 'column-id')]";

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "catalog/promotion/");
    }

    public void approvePromotionByProjectSystemId(String systemId) {
        getDriver().findElement(By.xpath(String.format(approveXpath, systemId))).click();
    }

    public void approvePromotionByCategoryName(String categoryName) {
        getDriver().findElement(By.xpath(String.format(approveXpath, categoryName))).click();
    }

    public String getProjectPromoId(String systemId) {
        return getDriver().findElement(By.xpath(String.format(promoIdXpath, systemId))).getText();
    }

    public String getCategoryPromoId(String categoryName) {
        return getDriver().findElement(By.xpath(String.format(promoIdXpath, categoryName))).getText();
    }
}
