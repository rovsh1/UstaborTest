import annotations.AddCategory;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Admin;
import utils.DataGenerator;
import utils.XmlParser;

import java.util.concurrent.TimeoutException;

@RunWith(SerenityRunner.class)
@AddCategory(addServiceQuestion = true, questionsCount = 4)
public class TC013_CustomerRequestProgress extends TestBase {

    @Test
    public void verifyRequestFillProgress() throws TimeoutException {
        var request = XmlParser.getTextByKey("Service");
        var question = XmlParser.getTextByKey("Question_0");
        var question1 = XmlParser.getTextByKey("Question_1");
        var question2 = XmlParser.getTextByKey("Question_2");
        var question3 = XmlParser.getTextByKey("Question_3");
        var guest = DataGenerator.getGuestCustomer();

        watcher.users.add(guest);

        Admin.getInstance().runCron("10");
        admin.waitForCronTaskCompleted("10", 200);

        user.atHomePage.openPlaceOrderPage();
        user.atPlaceOrderPage.selectBuildDomain();
        user.atPlaceOrderPage.selectCategory(category);
        user.atPlaceOrderPage.verifyProgress(10);
        user.atPlaceOrderPage.selectRequest(request);
        user.atPlaceOrderPage.verifyProgress(24);
        user.atPlaceOrderPage.selectQuestion(question);
        user.atPlaceOrderPage.verifyProgress(38);
        user.atPlaceOrderPage.selectQuestion(question1);
        user.atPlaceOrderPage.verifyProgress(52);
        user.atPlaceOrderPage.selectQuestion(question2);
        user.atPlaceOrderPage.verifyProgress(66);
        user.atPlaceOrderPage.selectQuestion(question3);
        user.atPlaceOrderPage.verifyProgress(80);
        user.atPlaceOrderPage.selectStartDateImmediately();
        user.atPlaceOrderPage.verifyProgress(90);
        user.atPlaceOrderPage.selectStartTime();
        user.atPlaceOrderPage.verifyProgress(100);
    }
}
