package qa.okay.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.okay.base.BaseTest;
import qa.okay.pages.HomePage;

import static qa.okay.utils.PropertyGetter.getProperty;

@Epic("Operation 36 form web testing")
public class Operation36FormTest extends BaseTest {

    @Test(dataProvider = "getTestData")
    @Story("Successful data entering testing")
    @Description("Successful data entering test")
    public void verifySuccessfulDataEnteringTest(String accountType, String checkByRule, String accountNumber,
                                                 String accountFormat, String expectedResult) {
        HomePage homePage = getHomePage();
        homePage.fillTheForm(accountType, checkByRule, accountNumber, accountFormat);
        Assert.assertTrue(homePage.getResult().contains(expectedResult), "Wrong result!");
    }

    @DataProvider
    private Object[][] getTestData() {
        Object[][] testData = new Object[16][5];
        String[] accountTypes = getProperty("account_types", "data").split(",");
        String[] checkByRules = getProperty("check_by_rules", "data").split(",");
        String[] accountNumbers = getProperty("account_numbers", "data").split(",");
        String[] accountFormats = getProperty("account_formats", "data").split(",");
        String[] expectedResults = getProperty("expected_results", "data").split(",");
        int i = 0;
        for (String accountType : accountTypes) {
            for (String checkByRule : checkByRules) {
                for (String accountNumber : accountNumbers) {
                    for (String accountFormat : accountFormats) {
                        testData[i][0] = accountType;
                        testData[i][1] = checkByRule;
                        testData[i][2] = accountNumber;
                        testData[i][3] = accountFormat;
                        if (checkByRule.equals(checkByRules[0]) && accountNumber.equals(accountNumbers[0])
                                || checkByRule.equals(checkByRules[1]) && accountFormat.equals(accountFormats[0])) {
                            testData[i][4] = expectedResults[0];
                        } else {
                            testData[i][4] = expectedResults[1];
                        }
                        i++;
                    }
                }
            }
        }
        return testData;
    }
}
