package steps;

import entities.Master;
import entities.User;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import steps.customerProfileSteps.CustomerProfilePersonalInfoPageSteps;
import steps.customerProfileSteps.CustomerProfileRequestsPageSteps;
import steps.masterProfileSteps.*;
import utils.Admin;
import utils.DataGenerator;
import utils.Watcher;

public class UserSteps extends ScenarioSteps {

    @Steps
    public HomePageSteps atHomePage;

    @Steps
    public CatalogPageSteps atCatalogPage;

    @Steps
    public CustomerProfilePersonalInfoPageSteps atCustomerProfilePersonalInfoPage;

    @Steps
    public FeedbackPageSteps atFeedbackPage;

    @Steps
    public MasterProfilePageSteps atMasterProfilePage;

    @Steps
    public MasterProjectsPageSteps atMasterProjectsPage;

    @Steps
    public MasterPromotionPageSteps atMasterPromotionPage;

    @Steps
    public SiteMapPageSteps atSiteMapPage;

    @Steps
    public MasterProfileSettingsPageSteps atMasterProfileSettingsPage;

    @Steps
    public MasterWalletPageSteps atMasterWalletPage;

    @Steps
    public MasterNotificationsPageSteps atMasterNotificationsPage;

    @Steps
    public MasterFaqPageSteps atMasterFaqPage;

    @Steps
    public PlaceOrderPageSteps atPlaceOrderPage;

    @Steps
    public CustomerProfileRequestsPageSteps atCustomerProfileRequestsPage;

    @Steps
    public MasterProfileRequestsPageSteps atMasterProfileRequestsPage;

    @Steps
    public CustomerRequestPageSteps atCustomerRequestPage;

    @Steps
    public MasterRequestPageSteps atMasterRequestPage;

    @Step
    public void register(Master master, boolean randomCategory) throws InterruptedException {
        atHomePage.registerAsMaster(master, randomCategory);

        var smsCode = Admin.getInstance().getSmsCode(master.getPhoneNumber());

        atHomePage.enterAuthCodeAndSubmit(smsCode, master.getPhoneNumber());
        atMasterProfilePage.masterProfilePagePageShouldBeVisible();

        master.setProfileId(atMasterProfilePage.getProfileId());
    }

    @Step
    public String getSmsCode(User user) throws InterruptedException {
        var smsCode = Admin.getInstance().getSmsCode(user.getPhoneNumber());

        if (smsCode == null) {
            atHomePage.resendCode();
            Thread.sleep(5000);
        }

        return Admin.getInstance().getSmsCode(user.getPhoneNumber());
    }

}
