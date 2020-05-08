import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.Config;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WithTag("smoke")

@RunWith(SerenityRunner.class)
public class UU201_CatalogTest extends TestBase {

    @Test
    public void verifyCatalogCategoriesAndSearch() {

        user.atHomePage.enterSearchText(getText("SearchRequestFurniture"));
        user.atHomePage.selectSuggestionCategoryAndSearch(getText("SearchRequestSuggestion"));
        user.atCatalogPage.verifySelectedCategoryEquals(getText("CategoryBathroom"));
        user.atCatalogPage.verifyAllFoundProjectsHaveCategory(getText("CategoryBathroom"));

        user.atCatalogPage.enterSearchText(getText("SearchRequestFurnitureShort"));
        user.atCatalogPage.suggestionDropdownShouldBeVisible();
        user.atHomePage.openHomePage();

        List<String> sitesNamesList = user.atCatalogPage.getSitesNames();
        for (String siteName : sitesNamesList) {
            user.atHomePage.openHomePage();
            user.atCatalogPage.openSiteWithName(siteName);
            user.atCatalogPage.correctSiteShouldBeVisible(siteName);
        }

        user.atCatalogPage.openSiteWithName(getText("SiteDomainBuild_Short1"));
        user.atCatalogPage.openSiteMap();
        user.atSiteMapPage.openRandomUrl();
        user.atCatalogPage.verifyFilterDoesNotContainsDefaultValues();
        int projectsCount = user.atCatalogPage.getProjectsCounterValue();

        String city = getText("FilterCity_" + Config.getCountryCode() + "_" + Config.getEnv());
        String district = getText("FilterDistrict_" + Config.getCountryCode());

        user.atCatalogPage.selectFilterCityAndDistrict(city, district);
        assertThat(user.atCatalogPage.getProjectsCounterValue()).isNotEqualTo(projectsCount);

        user.atCatalogPage.verifyFilterContainsValues(city, district);

        for (int i = 0; i < 3; i++) {
            user.atHomePage.openHomePage();
            user.atHomePage.openRandomCategory();
            if (!user.atCatalogPage.isSearchResultEmpty()) {
                assertThat(user.atCatalogPage.getProjectsCounterValue()).isGreaterThan(0);
            }
        }
    }
}
