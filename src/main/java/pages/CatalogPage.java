package pages;

import entities.Master;
import entities.Project;
import freemarker.template.utility.NullArgumentException;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class CatalogPage extends SearchBlock {

    private static final String masterNameXpath = ".//div[@class='presentation']";
    private static final String ratingXpath = ".//div[contains(@class,'rating')]";
    private static final String reviewsCountXpath = ".//span[@class='reviews-count']";
    private static final String avatarXpath = ".//div[@class='image']";

    private static final String badgesXpath = ".//div[@class='master-advantages']//span";
    private static final String districtXpath = "//div[contains(@class, 'district expanded')]//div[@class='item'and text()='%s']";

    private static final String promoAttribute = "data-promotion";

    @FindBy(xpath = "//div[@class='nav']//nav//a[contains(@href, 'catalog')]")
    private WebElementFacade mastersCatalog;

    @FindBy(xpath = "//h1")
    private WebElementFacade pageHeader;

    @FindBy(xpath = "//nav[@class='breadcrumbs']/a")
    private List<WebElementFacade> headerNavigationElements;

    @FindBy(xpath = "//div[contains(@class, 'category selected')]")
    private WebElementFacade selectedCategory;

    @FindBy(xpath = "//div[@id='projects-gallery']/a[@class='item project']")
    private List<WebElementFacade> projectsList;

    @FindBy(xpath = "//div[@id='projects-gallery']/a[.//div[@class='stats'] and not(.//div[@class='master-badges icons'])]")
    private List<WebElementFacade> projectsWithoutBadges;

    @FindBy(xpath = "//div[@id='projects-gallery']/a[.//i]")
    private List<WebElementFacade> projectsWithFavoriteMark;

    @FindBy(xpath = "//div[@id='projects-gallery']/a[last()]")
    private WebElementFacade lastAddedProject;

    @FindBy(xpath = "//div[contains(@class,'window-contacts')]")
    private WebElementFacade projectContactPopup;

    @FindBy(xpath = "//div[contains(@class,'window-contacts')]//div[@class='btn-close']")
    private WebElementFacade closeContactPopup;

    @FindBy(xpath = "//div[contains(@class, 'district')]//div[@class='item']")
    private WebElementFacade firstDistrict;

    @FindBy(xpath = "//div[@class='results']")
    private WebElementFacade projectsCounter;

    @FindBy(xpath = "//div[@class='catalog-search-empty']//div[@class='text']")
    private WebElementFacade emptyCatalogMessage;

    @FindBy(xpath = "//button[@id='load-more' and not(@disabled='disabled')]")
    private WebElementFacade loadMoreBtn;

    @FindBy(xpath = "//button[@id='load-more' and @disabled='disabled']")
    private WebElementFacade disabledLoadMoreBtn;

    //region Filter elements
    @FindBy(xpath = "//div[@id='catalog-search-menu']")
    private WebElementFacade filterBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'category')]")
    private WebElementFacade filterCategoryBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'city')]")
    private WebElementFacade filterCityBtn;

    @FindBy(xpath = "//div[@class='menu-popup']/div[contains(@class, 'district')]")
    private WebElementFacade filterDistrictBtn;

    @FindBy(xpath = "//div[contains(@class, 'city')]//div[contains(@class,'item')]")
    private List<WebElementFacade> filterCitiesList;

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

    //region New
    @FindBy(xpath = "//div[@id='projects-gallery']/a[@class='item master full']")
    private List<WebElementFacade> mastersList;
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

    public void openFilter() {
        scrollPageUpJS();
        filterBtn.click();

        if (!Config.isChrome()) {
            var counter = 0;
            while (!filterCategoryBtn.isVisible() && counter < 10) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                filterBtn.click();
                counter++;
            }
        }
    }

    public void closeFilter() {
        filterCloseBtn.click();
    }

    public void openMasterContactsByName(String masterName) {
        var master = mastersList.stream()
                .filter(p -> p.getText().contains(masterName)).findFirst().orElse(null);

        if (master == null) {
            throw new NullPointerException(String.format("%s was not found", masterName));
        }

        master.findElement(By.xpath(".//button[@class='button-contacts icon']")).click();
    }

    public void projectContactPopupShouldBeVisible(){
        projectContactPopup.shouldBeVisible();
    }

    public void closeContactPopup() {
        closeContactPopup.click();
    }

    public Master openRandomMasterProfile() {
        var randomMaster = mastersList.get(new Random().nextInt(mastersList.size()));
        var master = getMasterInfo(randomMaster);
        randomMaster.findElement(By.xpath(avatarXpath)).click();

        return master;
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
                .filter(city -> expectedCity.contains(city.getText()))
                .findFirst()
                .orElse(null);

        if (foundCity == null) throw new NullArgumentException(
                String.format("%s is not in cities list", expectedCity));

        foundCity.click();
    }

    public void selectDistrict(String districtName) {
        getDriver().findElement(By.xpath(String.format(districtXpath, districtName))).click();
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
        return Integer.parseInt(projectsCounter
                .getText()
                .replaceAll("[^0-9]", ""));
    }

    public boolean isSearchCatalogEmpty() {
        setTimeouts(1, ChronoUnit.SECONDS);
        boolean result = emptyCatalogMessage.isVisible();
        resetTimeouts();
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

    private Master getMasterInfo(WebElementFacade selectedMaster) {
        var master = new Master();
        master.setFirstName(selectedMaster.findElement(By.xpath(masterNameXpath)).getText());
        master.setRating(selectedMaster.findElement(By.xpath(ratingXpath)).getAttribute("class"));
        master.setFeedback(selectedMaster.findElement(By.xpath(reviewsCountXpath)).getText().replaceAll("[^0-9]", ""));
        return master;
    }

    public void verifyMasterWithBadges(Master master) {
        assertThat(mastersList.get(0).getAttribute("data-id")).isEqualTo(master.getProfileId());
    }

    public void verifyMasterAtFirstPosition(Master master) {
        var firstMaster = mastersList.stream().findFirst();
        int dataId = Integer.parseInt(firstMaster.get().getAttribute("data-id"));
        int promoId = Integer.parseInt(firstMaster.get().getAttribute(promoAttribute).replaceAll("\\s", ""));

        assertThat(dataId).isEqualTo(Integer.parseInt(master.getProfileId()));
        assertThat(promoId).isEqualTo(Integer.parseInt(master.getCategory().getPromoId().replaceAll("\\s", "")));
    }

    public void loadAllResults() {
        while (loadMoreBtn.isPresent()) {
            if (loadMoreBtn.isVisible()) {
                focusElementJS(loadMoreBtn);
                loadMoreBtn.click();
                continue;
            }
            return;
        }
    }

    public void verifyProjectsSortedByRate(Project project, int rating) {
        var projectsWithoutPromoAndBadges = projectsWithoutBadges.stream()
                .filter(x -> x.getAttribute("data-promotion").equals("0"))
                .collect(Collectors.toList());

        for (WebElementFacade proj : projectsWithoutPromoAndBadges) {
            var projectRating = 0;
            var ratingString = proj.findElements(By.xpath(ratingXpath)).get(0).getText();

            if (!ratingString.equals("")) {
                projectRating = Integer.parseInt(ratingString);
            }

            if (projectRating >= rating) {
                if (proj.getAttribute("data-id").equals(project.getSystemId())) {
                    return;
                }
            } else {
                throw new IllegalArgumentException("Ratings order is wrong");
            }
        }
    }

    public void applyFilter() {
        filterSubmitBtn.click();
    }

    public boolean isBreadcrumbsLongEnough() {
        return headerNavigationElements.size() > 3;
    }

    public void openPage() {
        mastersCatalog.click();
    }
}
