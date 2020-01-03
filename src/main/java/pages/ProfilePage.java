package pages;

import static org.assertj.core.api.Assertions.*;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends PageObject {

    @FindBy(xpath = "//ul[@class='cities']/li")
    private List<WebElementFacade> citiesList;

    public void VerifyProfileCitiesHave(String expectedCity) {
        assertThat(citiesList.stream().map(WebElementFacade::getText)).contains(expectedCity);
    }
}
