package steps;

import entities.Master;
import net.serenitybdd.annotations.Step;
import pages.CatalogPage;
import utils.XmlParser;

public class CatalogPageSteps extends CommonSteps {

    private CatalogPage catalogPage;

    @Step
    public void verifySelectedCategoryEquals(String expectedCategory) {
        catalogPage.openFilter();
        catalogPage.verifySelectedCategoryEquals(expectedCategory);
        catalogPage.closeFilter();
    }

    @Step
    public void verifyAllFoundProjectsHaveCategory(String expectedCategory) {
        catalogPage.verifyAllFoundProfilesHaveCategory(expectedCategory);
    }

    @Step
    public void openMasterContactsAndVerify(String projectName) {
        catalogPage.openMasterContactsByName(projectName);
        catalogPage.projectContactPopupShouldBeVisible();
        catalogPage.closeContactPopup();
        catalogPage.contactPopupShouldNotBeVisible();
    }

    @Step
    public void enterSearchText(String text) {
        catalogPage.cleanSearchInput();
        catalogPage.enterSearchText(text);
    }

    @Step
    public void suggestionDropdownShouldBeVisible() {
        catalogPage.suggestionListShouldBeVisible();
    }

    @Step
    public void verifyFilterValues() {
        catalogPage.openFilter();
        catalogPage.verifyCategoryFilterBtnTextIsNot(XmlParser.getTextByKey("FilterCategoriesDefault"));
        if (catalogPage.isBreadcrumbsLongEnough()) {
            catalogPage.verifyCityFilterTextIsNot(XmlParser.getTextByKey("FilterCityDefault"));
        }
    }

    @Step
    public void selectFilterCityAndDistrict(String cityName, String district) {
        catalogPage.openFilterCityDropdown();
        catalogPage.selectCity(cityName);

        if (!district.isEmpty()) {
            catalogPage.openDistrictDropdown();
            catalogPage.selectDistrict(district);

        }
        catalogPage.ClickSearchBtn();
    }

    @Step
    public int getProjectsCounterValue() {
        return catalogPage.getProjectsCounterValue();
    }

    @Step
    public void verifyFilterContainsValues(String city, String district) {
        catalogPage.openFilter();
        catalogPage.verifyCityFilterText(city);
        if (!district.isEmpty()) {
            catalogPage.verifyDistrictFilterText(district);
        }

    }

    public boolean isSearchResultEmpty() {
        return catalogPage.isSearchCatalogEmpty();
    }

    @Step
    public void openFilterAndVerify() {
        catalogPage.openFilter();
        catalogPage.filterCategoryBtnShouldBeVisible();
        catalogPage.filterCityBtnShouldBeVisible();
        catalogPage.filterSortingBtnShouldBeVisible();
        catalogPage.filterSearchBtnShouldBeVisible();
        catalogPage.filterResetBtnShouldBeVisible();
        catalogPage.filterCloseBtnShouldBeVisible();
    }

    @Step
    public void openFilterCategoriesAndVerify() {
        catalogPage.openFilterCategoryPopup();
        catalogPage.filterCategoryWindowShouldBeVisible();
    }

    @Step
    public void closeFilterCategoryWindow() {
        catalogPage.closeFilterCategoryWindow();
    }

    @Step
    public void openFilterCityDropdownAndVerify() {
        catalogPage.openFilterCityDropdown();
        catalogPage.filterCityDropdownShouldBeVisible();
        catalogPage.closeFilter();
    }

    @Step
    public Master openRandomMasterProfile() {
        return catalogPage.openRandomMasterProfile();
    }

    @Step
    public void verifyMasterWithBadgesPromoted(Master master) {
        catalogPage.verifyMasterWithBadges(master);
    }

    @Step
    public void verifyMasterCategoryPromoted(Master master) {
        catalogPage.verifyMasterAtFirstPosition(master);
    }

    @Step
    public void verifyMastersSortedByRate(Master master, int rating) {
        catalogPage.verifyMastersSortedByRate(master, rating);
    }

    @Step
    public void sortMastersByRating() {
        catalogPage.openFilter();
        catalogPage.applyFilter();
    }

    public void openMastersCatalog() {
        catalogPage.openPage();
    }

    @Step
    public void enterTextAndSearch(String text) {
        enterSearchText(text);
        catalogPage.ClickSearchBtn();
    }
}
