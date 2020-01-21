package steps;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import steps.masterProfileSteps.*;

public class UserSteps extends ScenarioSteps {

    @Steps
    public HomePageSteps atHomePage;

    @Steps
    public CatalogPageSteps atCatalogPage;

    @Steps
    public ProfilePageSteps atProfilePage;

    @Steps
    public CustomerProfilePageSteps atCustomerProfilePage;

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
}
