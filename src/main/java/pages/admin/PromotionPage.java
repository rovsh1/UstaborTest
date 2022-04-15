package pages.admin;

import org.openqa.selenium.By;
import utils.Config;

public class PromotionPage extends BaseAdminPage {

    private static final String approveXpath = "//tr[./td[text()='%s']]//a[contains(@href, 'enable')]";
    private static final String promoIdXpath = "//tr[./td[text()='%s']]//td[contains(@class, 'column-id')]";

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "master/promotion");
    }

    public void approvePromotionByCategoryName(String categoryName) {
        getDriver().findElement(By.xpath(String.format(approveXpath, categoryName))).click();
    }

    public String getCategoryPromoId(String categoryName) {
        return getDriver().findElement(By.xpath(String.format(promoIdXpath, categoryName))).getText();
    }
}
