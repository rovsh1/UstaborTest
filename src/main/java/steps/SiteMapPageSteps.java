package steps;

import net.thucydides.core.annotations.Step;
import pages.SiteMapPage;

public class SiteMapPageSteps extends CommonSteps{

    private SiteMapPage siteMapPage;

    @Step
    public void openRandomUrl() {
        siteMapPage.openRandomUrl();
    }

    public boolean isMapEmpty() {
        return siteMapPage.isMapListEmpty();
    }
}
