import annotations.AddCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import enums.RequestPages;
import utils.Admin;
import utils.DataGenerator;
import utils.XmlParser;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true)
@WithTag("new")
public class TC009_CustomerRequest extends TestBase {

    @Test
    public void verifyUserCanCreateCustomerRequest() throws TimeoutException {
        var guest = DataGenerator.getGuestCustomer();
        watcher.users.add(guest);

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.fillInFirstPage(
                guest.getFirstName(),
                category,XmlParser.getTextByKey("Service"),
                XmlParser.getTextByKey("Question"),
                "test request");
        user.atPlaceOrderPage.clickNextButton(RequestPages.First);
        user.atPlaceOrderPage.priceRangeShouldBeVisible("100", "200")
                .fillInSecondPage()
                .clickNextButton(RequestPages.Second);
        user.atPlaceOrderPage.fillInThirdPage(guest.getCity(), guest.getPhoneNumber());
        user.atPlaceOrderPage.clickNextButton(RequestPages.Last);

        var smsCode = new Admin().getSmsCode(guest.getPhoneNumber());

        user.atPlaceOrderPage.confirmPhoneNumber(smsCode)
                .successPageShouldBeVisible();

        user.atPlaceOrderPage.openRequestsPage();

        guest.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());

        user.atCustomerProfileRequestsPage.newRequestShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage.openPersonalInfo()
                .verifyProfileData(guest);
    }
}
