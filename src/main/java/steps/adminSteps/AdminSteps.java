package steps.adminSteps;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;


public class AdminSteps extends ScenarioSteps {

    @Steps
    public HomePageSteps atAdminHomePage;

    @Steps
    public MastersPageSteps atMastersPage;

    @Steps
    public CategoriesPageSteps atCategoriesPage;

    @Steps
    public PromotionPageSteps atPromotionPage;

    @Steps
    public ProjectsPageSteps atProjectsPage;
}
