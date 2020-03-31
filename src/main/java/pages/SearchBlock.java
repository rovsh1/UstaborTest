package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchBlock extends BasePage {

    @FindBy(xpath = "//input[@name='quicksearch']")
    private WebElementFacade searchInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade searchBtn;


    @FindBy(xpath = "//ul[contains(@class, 'ui-autocomplete')]")
    private WebElementFacade suggestionDropdown;

    @FindBy(xpath = "//ul[contains(@class, 'ui-autocomplete')]/li")
    private List<WebElementFacade> suggestionsOptionsList;

    public void enterSearchText(String text) {
        searchInput.sendKeys(text);
    }

    public void cleanSearchInput() {
        searchInput.clear();
    }

    public void ClickSearchBtn() {
        searchBtn.click();
    }


    public void suggestionListShouldBeVisible() {
        suggestionDropdown.shouldBeVisible();
    }

    public void selectSuggestion(String suggestion) {
        WebElementFacade option = suggestionsOptionsList.stream()
                .filter(s -> s.getText().contains(suggestion))
                .findFirst()
                .orElse(null);

        if (option == null) {
            throw new NullPointerException();
        }

        option.click();
    }
}
