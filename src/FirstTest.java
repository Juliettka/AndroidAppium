import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.Locale;

public class FirstTest {

    private AppiumDriver driver;
    @Before
    public void setUp () throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\julie\\Desktop\\JAVAAPpiumAUthomation\\AndroidAppium\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown () {
        driver.quit();
    }
    @Test
    public void testAssertHasText() throws Exception {
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search container does not have text Search Wikipedia",
                "Search Wikipedia");
    }

    @Test
    public void testSearchAndCancel() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Element 'Search Wikipedia' not found",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "King",
                "Cannot find input",
                5
        );
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='King-Rega']"),
                "There is not article 'King-Rega'",
                15);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Influential 1611 English version of the Bible']"),
                "There is not article 'Influential 1611 English version of the Bible'",
                15);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Input field not found",
                15);
        waitForElementPresent(By.id("org.wikipedia:id/search_empty_message"),
                "Results are not empty",
                15);
    }

    @Test
    public void testSearchResultsContainSearchTerm() throws Exception {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Element 'Search Wikipedia' not found",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find input",
                5
        );
        Assert.assertTrue(assertElementContainsText(By.xpath("//*[contains(@text,'Java')]"),
                "This search result 'Java' does not contain Java",
                "java") &&
                assertElementContainsText(By.xpath("//*[contains(@text,'JavaScript')]"),
                        "This search result 'JavaScript' does not contain Java",
                        "java")
                && assertElementContainsText(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "This search result 'Java (programming language)' does not contain Java",
                "java"));

    }
    private WebElement waitForElementPresent (By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message+"/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    private WebElement waitForElementAndClick (By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys (By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;

    }

    private String assertElementHasText(By by, String error_message, String expected_text) throws Exception {
        WebElement element = waitForElementPresent(by, error_message,5);
        String actual_text = element.getAttribute("text");
        if (!actual_text.toLowerCase().equals(expected_text.toLowerCase())) {
            throw new Exception(error_message);
        } else {
            return  actual_text;
        }
    }
    private boolean assertElementContainsText(By by, String error_message, String expected_text) throws Exception {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String actual_text = element.getAttribute("text");
        if (actual_text.toLowerCase().contains(expected_text.toLowerCase()))
            return true;
        else throw new Exception(error_message);
    }
}
