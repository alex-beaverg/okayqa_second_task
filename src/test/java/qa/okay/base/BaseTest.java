package qa.okay.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import qa.okay.pages.HomePage;

import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static qa.okay.utils.PropertyGetter.getProperty;

public class BaseTest {

    @Step(value = "Getting web driver step")
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        addChromeOptionsForGitHubActions();
        open();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        closeWebDriver();
    }

    @Step(value = "Adding options for GitHub Actions")
    private void addChromeOptionsForGitHubActions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        Configuration.browserCapabilities = options;
    }

    @Step(value = "Getting home page step")
    public HomePage getHomePage() {
        HomePage homePage = open(Paths.get(getProperty("home_url", "config")).toUri().toString(), HomePage.class);
        homePage.isPageOpen();
        return homePage;
    }
}
