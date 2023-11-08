import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

@WithTag("smoke")
@Ignore
@RunWith(SerenityRunner.class)
public class UU201_CatalogTest extends TestBase {

    @Test
    public void verifyCatalogCategoriesAndSearch() throws TimeoutException {

        user.atCatalogPage.openMastersCatalog();
        user.atCatalogPage.enterSearchText(getText("SearchRequestFurniture"));
        user.atHomePage.selectSuggestionCategoryAndSearch(getText("SearchRequestSuggestion"));
        user.atCatalogPage.verifySelectedCategoryEquals(getText("CategoryBathroom"));

        user.atCatalogPage.enterSearchText(getText("SearchRequestFurnitureShort"));
        user.atCatalogPage.suggestionDropdownShouldBeVisible();
        user.atHomePage.openHomePage();

        var sitesNamesList = user.atCatalogPage.getSitesNames();
        for (String siteName : sitesNamesList) {
            user.atHomePage.openHomePage();
            user.atCatalogPage.openSiteWithName(siteName);
            user.atCatalogPage.correctSiteShouldBeVisible(siteName);
        }

        user.atCatalogPage.openSiteWithName(getText("SiteDomainBuild_Short1"));
        user.atCatalogPage.openSiteMap();

        if (!user.atSiteMapPage.isMapEmpty()) {
            user.atSiteMapPage.openRandomUrl();
            user.atCatalogPage.verifyFilterValues();

            var city = getText("FilterCity_" + Config.getCountryCode() + "_" + Config.getEnv());
            var district = getText("FilterDistrict_" + Config.getCountryCode());

            user.atCatalogPage.selectFilterCityAndDistrict(city, district);
            user.atCatalogPage.verifyFilterContainsValues(city, district);
        }

        for (int i = 0; i < 3; i++) {
            user.atHomePage.openHomePage();
            user.atCatalogPage.openMastersCatalog();
            if (!user.atCatalogPage.isSearchResultEmpty()) {
                assertThat(user.atCatalogPage.getProjectsCounterValue()).isGreaterThan(0);
            }
        }
    }
}
