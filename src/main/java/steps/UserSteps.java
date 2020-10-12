package steps;

import entities.Master;
import entities.Project;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import pages.masterProfile.MasterProfileRequestsPage;
import steps.customerProfileSteps.CustomerProfilePersonalInfoPageSteps;
import steps.customerProfileSteps.CustomerProfileRequestsPageSteps;
import steps.masterProfileSteps.*;
import utils.Config;

public class UserSteps extends ScenarioSteps {

    @Steps
    public HomePageSteps atHomePage;

    @Steps
    public CatalogPageSteps atCatalogPage;

    @Steps
    public CustomerProfilePersonalInfoPageSteps atCustomerProfilePersonalInfoPage;

    @Steps
    public ProjectPageSteps atProjectPage;

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

    public void registerAsMaster(Master master) {
        atHomePage.registerAsMasterWithSpecifiedCategory(master);
        atMasterProfilePage.waitForPageIsVisible();
        master.setProfileId(atMasterProfilePage.getProfileId());
        master.setProject(new Project(master.getCategoryName()));
    }
}
