import annotations.AddCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DataGenerator;
import utils.XmlParser;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true)
public class TC009_CustomerRequest extends TestBase {

    @Test
    public void verifyUserCanCreateCustomerRequest() throws TimeoutException, InterruptedException {
        var request = XmlParser.getTextByKey("Service");
        var question = XmlParser.getTextByKey("Question_0");
        var guest = DataGenerator.getGuestCustomer();

        watcher.users.add(guest);

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.selectBuildDomain();
        user.atPlaceOrderPage.selectCategory(category);
        user.atPlaceOrderPage.selectRequest(request);
        user.atPlaceOrderPage.selectQuestion(question);
        user.atPlaceOrderPage.selectStartDateImmediately();
        user.atPlaceOrderPage.selectStartTime();
        user.atPlaceOrderPage.fillContactInfo(guest, "Some request info");
        user.atPlaceOrderPage.clickPlaceOrder();

        user.atPlaceOrderPage.waitForCodeForm();
        var smsCode = user.atPlaceOrderPage.getSmsCode(guest.getPhoneNumber());
        user.atPlaceOrderPage.confirmPhoneNumber(smsCode, guest.getPhoneNumber());

        user.atCustomerProfilePersonalInfoPage.openCustomerProfilePage();
        user.atPlaceOrderPage.openRequestsPage();

        guest.setProfileId(user.atCustomerProfileRequestsPage.getCustomerProfileId());

        user.atCustomerProfileRequestsPage.newRequestShouldBeVisible();

        user.atCustomerProfilePersonalInfoPage
                .openPersonalInfo()
                .verifyProfileData(guest);
    }
}
