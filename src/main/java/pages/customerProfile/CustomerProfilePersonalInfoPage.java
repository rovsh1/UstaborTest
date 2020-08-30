package pages.customerProfile;

import static org.assertj.core.api.Assertions.*;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CustomerProfilePersonalInfoPage extends CustomerProfileBasePage {

    @FindBy(xpath = "//input[@id='form_user_presentation']")
    private WebElementFacade userNameInput;

    @FindBy(xpath = "//input[@id='form_user_phone']")
    private WebElementFacade phoneNumberInput;

    @FindBy(xpath = "//a[@id='button-delete']")
    private WebElementFacade deleteProfileButton;

    @FindBy(xpath = "//div[.//div[text()='Удалить профиль']]//button[@class='button-submit']")
    private WebElementFacade submitButton;

    @FindBy(xpath = "//div[@id='projects-gallery']//a[not(contains(@style, 'display: none'))]")
    private List<WebElementFacade> favoriteProjects;

    @FindBy(xpath = "//div[@id='customer-masters']//div[@class='item']")
    private List<WebElementFacade> myMastersList;


    public void deleteBtnShouldBeVisible() {
        deleteProfileButton.shouldBeVisible();
    }

    public void verifyCountOfFavouriteProjectsEquals(int countOfProjects) {
        getDriver().navigate().refresh();
        assertThat(favoriteProjects.size()).isEqualTo(countOfProjects);
    }

    public void removeRandomFavoriteProject() {
        favoriteProjects
                .get(new Random().nextInt(favoriteProjects.size()))
                .findElement(By.xpath(".//i"))
                .click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyMyMastersListContains(String projectName) {
        boolean anyMatch = myMastersList.stream()
                .map(WebElementFacade::getText)
                .filter(Objects::nonNull)
                .anyMatch(item -> item.contains(projectName));

        assertThat(anyMatch).isTrue();
    }

    public void deleteProfile() {
        deleteProfileButton.click();
    }

    public void submitProfileDeleting() {
        submitButton.click();
    }

    public void verifyUserName(String phoneNumber) {
        assertThat(userNameInput.getValue()).isEqualTo(phoneNumber);
    }

    public void verifyPhoneNumber(String phoneNumber) {
        assertThat(phoneNumberInput.getValue().replaceAll("\\s+", "")).contains(phoneNumber);
    }
}
