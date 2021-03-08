
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;


public class FirstTest extends CoreTestCase {


    @Test
    public void testAssertHasText() throws Exception {
        MainPageObject.assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search container does not have text Search Wikipedia",
                "Search Wikipedia");
    }



    @Test
    public void testSearchResultsContainSearchTerm() throws Exception {
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Element 'Search Wikipedia' not found",
                5);
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find input",
                5
        );
        assertTrue(MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text,'Java')]"),
                "This search result 'Java' does not contain Java",
                "java") &&
                MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text,'JavaScript')]"),
                        "This search result 'JavaScript' does not contain Java",
                        "java")
                && MainPageObject.assertElementContainsText(By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "This search result 'Java (programming language)' does not contain Java",
                "java"));

    }






    @Test
    public void testSave2ArticlesToTheList(){
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Element 'Search Wikipedia' not found",
                5);
        String search_line = "King";
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find input",
                5
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='King']"),
                "Article King not found",
                5);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Article King has not been opened",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "There is no 'more options' on the page",
                15
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list'",
                5);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "GOT IT button not found",
                15);
        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input field",
                5);
        String name_of_folder = "Ex5";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot enter reading list title",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article",
                15
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Element 'Search Wikipedia' not found",
                5);
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find input",
                5
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='King James Version']"),
                "Article 'King James Version' not found",
                5);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Article 'King James Version' has not been opened",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "There is no 'more options' on the page",
                5
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list'",
                5);
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_container"),
                "cannot find already created list",
                5
        );
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article King James Version",
                15
        );
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find Navigation button to my lists",
                15
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='"+ name_of_folder + "']"),
                "Cannot find Reading list",
                5);
        MainPageObject.swipeElementToLeft(By.xpath("//*[@text='King']"),
                "cannot swipe");
        MainPageObject.waitForElementNotPresent(By.xpath("//*[@text='King']"),
                "cannot delete saved article",
                15);
        MainPageObject.waitForElementPresent(By.xpath("//*[@text='King James Version']"),
                "cannot find second article",
                5);
        String title_of_article_in_my_list = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/page_list_item_title"),
                "text",
                "cannot find title",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='King James Version']"),
                "cannot tap on second article",
                5);
        String title_of_the_article = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "cannot find article title",
                5
        );
        assertEquals("The title of the article is not equal to the title in my list",
                title_of_article_in_my_list,
                title_of_the_article);
    }
    @Test
    public void testAssertTitle(){
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Element 'Search Wikipedia' not found",
                5);
        String search_line = "King";
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find input",
                5
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='King']"),
                "Article King not found",
                5);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Article King has not been opened",
                5);
        MainPageObject.assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "There is no title element");
    }

}
