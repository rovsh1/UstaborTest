import annotations.AddCategory;
import annotations.AddMasters;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.XmlParser;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true, addServicePrice = true)
@AddMasters(masters = 2, useAdminSite = true)
public class TC014_CustomerRequestMastersFilter extends TestBase {

    @Test
    public void verifyMastersCount() throws TimeoutException {
        var request = XmlParser.getTextByKey("Service");
        var master = watcher.getMaster();

        user.atHomePage.openHomePage();
        user.atHomePage.login(master, true);
        user.atMasterProfilePage.openProfilePage();
        user.atMasterProfilePage.selectService();
        user.atMasterProfilePage.logsOut();

        user.atHomePage.openHomePage();
        setCountryLanguageAndLocation();

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.selectBuildDomain();
        user.atPlaceOrderPage.selectCategory(category);
        user.atPlaceOrderPage.verifyMastersCount(2);
        user.atPlaceOrderPage.selectRequest(request);
        user.atPlaceOrderPage.verifyMastersCount(1);
    }
}
