package pages;

import static org.assertj.core.api.Assertions.*;

import freemarker.template.utility.NullArgumentException;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CatalogPage extends SearchBlock {

    @FindBy(xpath = "//h1")
    private WebElementFacade pageHeader;

    @FindBy(xpath = "//div[contains(@class, 'category selected')]")
    private WebElementFacade selectedCategory;

    @FindBy(xpath = "//div[@id='projects-gallery']/a")
    private List<WebElementFacade> projectsList;

    @FindBy(xpath = "//div[@id='projects-gallery']/a[.//i]")
    private List<WebElementFacade> projectsWithFavoriteMark;

    @FindBy(xpath = "//div[@id='catalog-search-menu']")
    private WebElementFacade filterBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'category')]")
    private WebElementFacade categoryFilterBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'city')]")
    private WebElementFacade cityFilterBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'district')]")
    private WebElementFacade districtFilterBtn;

    @FindBy(xpath = "//div[@class='menu-popup']//div[@class='button-close']")
    private WebElementFacade closeFilterBtn;

    @FindBy(xpath = "//div[contains(@class,'window-contacts')]")
    private WebElementFacade projectContactPopup;

    @FindBy(xpath = "//div[contains(@class,'window-contacts')]//div[@class='button-close']")
    private WebElementFacade closeContactPopup;

    @FindBy(xpath = "//div[contains(@class, 'city')]//div[@class='item']")
    private List<WebElementFacade> citiesList;

    @FindBy(xpath = "//div[contains(@class, 'district')]//div[@class='item']")
    private List<WebElementFacade> districtsList;

    @FindBy(xpath = "//div[contains(@class, 'district')]//div[@class='item']")
    private WebElementFacade firstDistrict;

    @FindBy(xpath = "//div[@class='results']/span")
    private WebElementFacade projectsCounter;

    @FindBy(xpath = "//div[@class='catalog-search-empty']//div[@class='text']")
    private WebElementFacade emptyCatalogMessage;

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
        closeFilterBtn.click();
    }

    public String addRandomProjectToFavorites() {
        WebElementFacade project = projectsWithFavoriteMark
                .get(new Random().nextInt(projectsWithFavoriteMark.size()));

        project.findElement(By.xpath(".//i")).click();
        return project.findElement(By.xpath(".//div[@class='presentation']")).getText();
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

    public void openRandomProjectWithNameNot(String projectName) {
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
        assertThat(categoryFilterBtn.getText()).isNotEqualTo(text);
    }

    public void verifyCityFilterTextIsNot(String city) {
        assertThat(cityFilterBtn.getText()).isNotEqualTo(city);
    }

    public void selectCity(String expectedCity) {
        WebElementFacade foundCity = citiesList.stream()
                .filter(city -> expectedCity.equals(city.getText()))
                .findFirst()
                .orElse(null);

        if (foundCity == null) throw new NullArgumentException(
                String.format("%s is not in cities list", expectedCity));

        foundCity.click();
    }

    public void selectDistrict(String districtName) {
        firstDistrict.waitUntilClickable();
        WebElementFacade district = districtsList.stream()
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
        assertThat(cityFilterBtn.getText()).isEqualTo(city);
    }

    public void verifyDistrictFilterText(String district) {
        assertThat(districtFilterBtn.getText()).isEqualTo(district);
    }

    public void openCityDropdown() {
        cityFilterBtn.click();
    }

    public void openDistrictDropdown() {
        districtFilterBtn.click();
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
}
