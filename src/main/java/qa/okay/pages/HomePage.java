package qa.okay.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.innerText;
import static com.codeborne.selenide.Selenide.$;

import static qa.okay.utils.PropertyGetter.getProperty;

public class HomePage {
    private final By titleElement = By.xpath(getProperty("xpath.title", "locator"));
    private final String title = getProperty("title", "data");
    private final By accountTypeElement = By.xpath(getProperty("xpath.account_type", "locator"));
    private final By checkByRulesElement = By.xpath(getProperty("xpath.check_by_rules", "locator"));
    private final By accountNumberElement = By.xpath(getProperty("xpath.account_number", "locator"));
    private final By accountFormatElement = By.xpath(getProperty("xpath.account_format", "locator"));
    private final By checkButtonElement = By.xpath(getProperty("xpath.check_button", "locator"));
    private final By resultElement = By.xpath(getProperty("xpath.result", "locator"));

    @Step("Checking opening current Page")
    public void isPageOpen() {
        $(titleElement).shouldHave(innerText(title));
    }

    private void selectAccountType(String accountType) {
        $(accountTypeElement).selectOption(accountType);
    }

    private void selectCheckByRules(String checkByRule) {
        $(checkByRulesElement).selectOption(checkByRule);
    }

    private void enterAccountNumber(String accountNumber) {
        if (!accountNumber.equals("-empty-")) {
            $(accountNumberElement).type(accountNumber);
        }
    }

    private void enterAccountFormat(String accountFormat) {
        if (!accountFormat.equals("-empty-")) {
            $(accountFormatElement).type(accountFormat);
        }
    }

    private void clickCheckButton() {
        $(checkButtonElement).click();
    }

    @Step(value = "Fill the form step")
    public void fillTheForm(String accountType, String checkByRule, String accountNumber, String accountFormat) {
        selectAccountType(accountType);
        selectCheckByRules(checkByRule);
        enterAccountNumber(accountNumber);
        enterAccountFormat(accountFormat);
        clickCheckButton();
    }

    @Step(value = "Getting result step")
    public String getResult() {
        return $(resultElement).text();
    }
}
