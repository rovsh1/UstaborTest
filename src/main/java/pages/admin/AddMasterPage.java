package pages.admin;

import entities.Master;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import utils.Config;
import utils.XmlParser;

public class AddMasterPage extends BaseAdminPage {

    @FindBy(xpath = "//input[@id='form_data_presentation']")
    private WebElementFacade systemName;

    @FindBy(xpath = "//input[@id='form_data_surname']")
    private WebElementFacade firstName;

    @FindBy(xpath = "//input[@id='form_data_name']")
    private WebElementFacade lastName;

    @FindBy(xpath = "//input[@id='form_data_email']")
    private WebElementFacade email;

    @FindBy(xpath = "//input[@id='form_data_phone']")
    private WebElementFacade phone;

    @FindBy(xpath = "//select[@id='form_data_experience']")
    private WebElementFacade experience;

    @FindBy(xpath = "//select[@id='form_data_site_id']")
    private WebElementFacade site;

    @FindBy(xpath = "//input[@id='form_data_login']")
    private WebElementFacade login;

    @FindBy(xpath = "//input[@id='form_data_password']")
    private WebElementFacade password;

    @FindBy(xpath = "//textarea[@id='form_data_about']")
    private WebElementFacade about;

    @FindBy(xpath = "//li[@data-tab='tab-settings']")
    private WebElementFacade settingsTab;

    @FindBy(xpath = "//li[@data-tab='tab-about']")
    private WebElementFacade aboutTab;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade submitButton;

    @FindBy(xpath = "//a[@id='btn-master-categories']")
    private WebElementFacade categories;

    @FindBy(xpath = "//form[./input[@id='form_data_categories']]//button[@type='submit']")
    private  WebElementFacade saveCategories;

    @FindBy(xpath = "//tr[./th[text()='ID']]/td")
    private WebElementFacade masterId;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "master/create");
    }

    public void createMaster(Master master) {
        var code = phone.getAttribute("placeholder").replaceAll("[^\\d]", "");
        master.setPhoneCode(code);

        systemName.sendKeys(master.getFirstName());
        firstName.sendKeys(master.getFirstName());
        lastName.sendKeys(master.getLastName());
        phone.sendKeys(master.getPhoneNumber());

        String xpath = "//select[@id='form_data_city_id']//optgroup[@label='%s']//option";
        String country = Config.getCountry();
        getDriver().findElements(By.xpath(String.format(xpath, country))).get(0).click();
        experience.selectByIndex(1);

        settingsTab.click();
        site.selectByIndex(1);
        login.sendKeys(master.getLogin());
        master.setPassword("password");
        password.sendKeys(master.getPassword());

        aboutTab.click();
        about.sendKeys("test");
        submitButton.click();
    }

    public void openMasterPage(Master master) {
        getDriver().findElement(By.xpath(String.format("//a[text()='%s']", master.getFirstName()))).click();
        master.setProfileId(masterId.getText().substring(1));
    }

    public void setCategoryToMaster(Master master) {
        categories.click();
        getDriver()
                .findElement(By.xpath(String.format("//input[@value='%s']", master.getCategory().getSystemId())))
                .click();
        getDriver()
                .findElement(By.xpath(String.format("//div[./div[./input[@value='%s']]]/div[@class='attr']//input", master.getCategory().getSystemId())))
                .click();
        saveCategories.click();
    }
}
