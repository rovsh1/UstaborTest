package pages.admin;

import entities.Category;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.List;

public class AddRequestQuestionsPage extends BaseAdminPage {

    private final String questionXpath = "//span[text()='%s']";

    @FindBy(xpath = "//nav[@id='main-categories']/div[@class='item']")
    private List<WebElementFacade> categories;

    @FindBy(xpath = "//div[./span[text()='Autotest']]")
    private WebElementFacade categoryService;

    @FindBy(xpath = "//textarea")
    private WebElementFacade questionTextArea;

    @FindBy(xpath = "//button[@class='submit']")
    private WebElementFacade submitQuestionBtn;

    @FindBy(xpath = "//button[@class='button-submit']")
    private WebElementFacade submitPriceBtn;

    public void openPage() {
        getDriver().get(Config.getAdminUrl() + "customer/requestanswer/");
    }

    public void openQuestionsFormForCategory(Category category) {
        var expectedCategory = categories
                .stream()
                .filter(c -> c.getText().contains(category.getName()))
                .findFirst()
                .orElse(null);

        if (expectedCategory == null) {
            throw new NullPointerException(String.format("No such category found: %s", category.getName()));
        }

        expectedCategory.click();
    }

    public void addQuestion(String question) {
        categoryService.click();
        questionTextArea.sendKeys(question);
        submitQuestionBtn.click();
    }
}

