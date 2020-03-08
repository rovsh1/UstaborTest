package pages;

import entities.FavProject;
import entities.MasterBaseInfo;
import freemarker.template.utility.NullArgumentException;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class CatalogPage extends SearchBlock {

    private static String projectCategoryXpath = ".//div[@class='category']";
    private static String projectMasterNameXpath = ".//div[@class='presentation']";
    private static String projectMasterAvatarXpath = ".//div[@class='bottom']/div[@class='image']";

    @FindBy(xpath = "//h1")
    private WebElementFacade pageHeader;

    @FindBy(xpath = "//div[contains(@class, 'category selected')]")
    private WebElementFacade selectedCategory;

    @FindBy(xpath = "//div[@id='projects-gallery']/a")
    private List<WebElementFacade> projectsList;

    @FindBy(xpath = "//div[@id='projects-gallery']/a[.//i]")
    private List<WebElementFacade> projectsWithFavoriteMark;

    @FindBy(xpath = "//div[contains(@class,'window-contacts')]")
    private WebElementFacade projectContactPopup;

    @FindBy(xpath = "//div[contains(@class,'window-contacts')]//div[@class='button-close']")
    private WebElementFacade closeContactPopup;

    @FindBy(xpath = "//div[contains(@class, 'district')]//div[@class='item']")
    private WebElementFacade firstDistrict;

    @FindBy(xpath = "//div[@class='results']/span")
    private WebElementFacade projectsCounter;

    @FindBy(xpath = "//div[@class='catalog-search-empty']//div[@class='text']")
    private WebElementFacade emptyCatalogMessage;

    //region Filter elements
    @FindBy(xpath = "//div[@id='catalog-search-menu']")
    private WebElementFacade filterBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'category')]")
    private WebElementFacade filterCategoryBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'city')]")
    private WebElementFacade filterCityBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'district')]")
    private WebElementFacade filterDistrictBtn;

    @FindBy(xpath = "//div[contains(@class, 'city')]//div[@class='item']")
    private List<WebElementFacade> filterCitiesList;

    @FindBy(xpath = "//div[contains(@class, 'district')]//div[@class='item']")
    private List<WebElementFacade> filterDistrictsList;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'order')]")
    private WebElementFacade filterSortingBtn;

    @FindBy(xpath = "//div[@class='menu-popup']//button[@type='submit']")
    private WebElementFacade filterSubmitBtn;

    @FindBy(xpath = "//div[@class='menu-popup']//div[@class='button-close']")
    private WebElementFacade filterCloseBtn;

    @FindBy(xpath = "//div[@class='menu-popup']//button[@type='reset']")
    private WebElementFacade filterResetBtn;

    @FindBy(xpath = "//div[@class='window-categories categories-tabs']")
    private WebElementFacade filterCategoryWindow;

    @FindBy(xpath = "//div[@class='window-categories categories-tabs']//div[@class='button-close']")
    private WebElementFacade filterCategoryWindowCloseBtn;
    //endregion

    public void verifySelectedCategoryEquals(String expectedCategory) {
        assertThat(selectedCategory.getText()).isEqualTo(expectedCategory);
    }

    public void verifyAllFoundProfilesHaveCategory(String expectedCategory) {
        List<String> categories = projectsList.stream()
                .map(project -> project.findElement(By.xpath(".//div[@class='category']")).getText())
                .collect(Collectors.toList());

        categories.forEach(c -> assertThat(c).isEqualTo(expectedCategory));
    }

    public void OpenRandomProfile() {
        WebElementFacade project = projectsList.get(new Random().nextInt(projectsList.size()));
        WebElement projectOwner = project.findElement(By.xpath(".//div[@class='bottom']//img"));

        projectOwner.click();
    }

    public void VerifyHeaderText(String text) {
        assertThat(pageHeader.getText()).isEqualTo(text);
    }

    public void openFilter() {
        filterBtn.click();
    }

    public void closeFilter() {
        filterCloseBtn.click();
    }

    public FavProject addRandomProjectToFavorites() {
        WebElementFacade project = projectsWithFavoriteMark
                .get(new Random().nextInt(projectsWithFavoriteMark.size()));

        focusElementJS(project);

        project.findElement(By.xpath(".//i")).click();

        return new FavProject(
                project.findElement(By.xpath(".//div[@class='presentation']")).getText(),
                project.findElement(By.xpath(".//div[@class='category']")).getText()
        );
    }

    public void openProjectContactsByName(String projectName) {
        WebElementFacade project = projectsWithFavoriteMark.stream()
                .filter(p -> p.getText().contains(projectName)).findFirst().orElse(null);

        if (project == null) {
            throw new NullPointerException(String.format("%s was not found", projectName));
        }

        project.findElement(By.xpath(".//div[@class='button-contact']")).click();
    }

    public void projectContactPopupShouldBeVisible(){
        projectContactPopup.shouldBeVisible();
    }

    public void closeContactPopup() {
        closeContactPopup.click();
    }

    public MasterBaseInfo openRandomProject() {
        WebElementFacade project = getRandomProject();
        MasterBaseInfo baseInfo = getProjectBaseInfo(project);
        project.click();

        return baseInfo;
    }

    public MasterBaseInfo openRandomMasterProfile() {
        WebElementFacade project = getRandomProject();
        MasterBaseInfo baseInfo = getProjectBaseInfo(project);

        project.findElement(By.xpath(projectMasterAvatarXpath)).click();

        return baseInfo;
    }

    public void openRandomFavProjectWithNameNot(String projectName) {
        List<WebElementFacade> projects = projectsWithFavoriteMark.stream()
                .filter(p -> !p.getText().contains(projectName)).collect(Collectors.toList());

        if (projects.isEmpty()) {
            throw new NullPointerException("Projects list is empty");
        }

        int elementNumber = new Random().nextInt(projects.size());
        focusElementJS("bottom", elementNumber);
        projects.get(elementNumber).click();
    }


    public void contactPopupShouldNotBeVisible() {
        projectContactPopup.shouldNotBeVisible();
    }

    public void verifyCategoryFilterBtnTextIsNot(String text) {
        assertThat(filterCategoryBtn.getText()).isNotEqualTo(text);
    }

    public void verifyCityFilterTextIsNot(String city) {
        assertThat(filterCityBtn.getText()).isNotEqualTo(city);
    }

    public void selectCity(String expectedCity) {
        WebElementFacade foundCity = filterCitiesList.stream()
                .filter(city -> expectedCity.equals(city.getText()))
                .findFirst()
                .orElse(null);

        if (foundCity == null) throw new NullArgumentException(
                String.format("%s is not in cities list", expectedCity));

        foundCity.click();
    }

    public void selectDistrict(String districtName) {
        firstDistrict.waitUntilClickable();
        WebElementFacade district = filterDistrictsList.stream()
                .filter(city -> city.getText().equals(districtName))
                .findFirst()
                .orElse(null);

        if (district == null) throw new NullArgumentException(
                String.format("%s is not in districts list", districtName));

        district.click();
    }

    public int getProjectsCount() {
        return projectsList.size();
    }

    public void verifyCityFilterText(String city) {
        assertThat(filterCityBtn.getText()).isEqualTo(city);
    }

    public void verifyDistrictFilterText(String district) {
        assertThat(filterDistrictBtn.getText()).isEqualTo(district);
    }

    public void openFilterCityDropdown() {
        filterCityBtn.click();
    }

    public void openDistrictDropdown() {
        filterDistrictBtn.click();
    }

    public int getProjectsCounterValue() {
        if (isSearchCatalogEmpty()) {
            return 0;
        }
        return Integer.valueOf(
                projectsCounter
                        .getText()
                        .replaceAll("[^0-9]", ""));
    }

    public boolean isSearchCatalogEmpty() {
        setImplicitTimeout(2, ChronoUnit.SECONDS);
        boolean result = emptyCatalogMessage.isVisible();
        resetImplicitTimeout();
        return result;
    }

    public void filterCategoryBtnShouldBeVisible() {
        filterCategoryBtn.shouldBeVisible();
    }

    public void filterCityBtnShouldBeVisible() {
        filterCityBtn.shouldBeVisible();
    }

    public void filterSortingBtnShouldBeVisible() {
        filterSortingBtn.shouldBeVisible();
    }

    public void filterSearchBtnShouldBeVisible() {
        filterSubmitBtn.shouldBeVisible();
    }

    public void filterResetBtnShouldBeVisible() {
        filterResetBtn.shouldBeVisible();
    }

    public void filterCloseBtnShouldBeVisible() {
        filterCloseBtn.shouldBeVisible();
    }

    public void openFilterCategoryPopup() {
        filterCategoryBtn.click();
    }

    public void filterCategoryWindowShouldBeVisible() {
        filterCategoryWindow.shouldBeVisible();
    }

    public void closeFilterCategoryWindow() {
        filterCategoryWindowCloseBtn.click();
    }

    public void filterCityDropdownShouldBeVisible() {
        assertThat(filterCitiesList.size()).isGreaterThan(1);
        filterCitiesList.forEach(WebElementState::shouldBeVisible);
    }


    private WebElementFacade getRandomProject() {
        int elementNumber = new Random().nextInt(projectsList.size());
        focusElementJS("bottom", elementNumber);

        return projectsList.get(elementNumber);
    }

    private MasterBaseInfo getProjectBaseInfo(WebElementFacade projectElement) {
        if (projectElement == null) {
            throw new NullPointerException();
        }

        MasterBaseInfo projectInfo = new MasterBaseInfo();
        projectInfo.setMasterName(projectElement.findElement(By.xpath(projectMasterNameXpath)).getText());
        projectInfo.setCategory(projectElement.findElement(By.xpath(projectCategoryXpath)).getText());
        projectInfo.setProjectUrl(projectElement.getAttribute("href"));
        projectInfo.setProfileUrl(
                projectElement.findElement(By.xpath(projectMasterAvatarXpath)).getAttribute("data-url"));

        return projectInfo;
    }
}
