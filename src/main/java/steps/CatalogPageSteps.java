package steps;

import entities.FavProject;
import entities.MasterBaseInfo;
import net.thucydides.core.annotations.Step;
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
    public void OpenRandomProfile() {
        catalogPage.OpenRandomProfile();
    }

    @Step
    public void VerifyHeaderText(String text) {
        catalogPage.VerifyHeaderText(text);
    }

    @Step
    public FavProject addRandomProjectToFavorites() {
        return catalogPage.addRandomProjectToFavorites();
    }

    @Step
    public void openProjectContactsAndVerify(String projectName) {
        catalogPage.openProjectContactsByName(projectName);
        catalogPage.projectContactPopupShouldBeVisible();
        catalogPage.closeContactPopup();
        catalogPage.contactPopupShouldNotBeVisible();
    }

    @Step
    public void openRandomProjectWithNameNot(String projectName) {
        catalogPage.openRandomFavProjectWithNameNot(projectName);
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
    public void verifyFilterDoesNotContainsDefaultValues() {
        catalogPage.openFilter();
        catalogPage.verifyCategoryFilterBtnTextIsNot(XmlParser.getTextByKey("FilterCategoriesDefault"));
        catalogPage.verifyCityFilterTextIsNot(XmlParser.getTextByKey("FilterCityDefault"));
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
    }

    @Step
    public MasterBaseInfo openRandomProject() {
        return catalogPage.openRandomProject();
    }

    @Step
    public MasterBaseInfo openRandomMasterProfile() {
        return catalogPage.openRandomMasterProfile();
    }
}
