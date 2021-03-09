package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text = 'View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            EXISTING_FOLDER = "org.wikipedia:id/item_container";
    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find Article Title",
                15);
    }
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
    }
    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "no more options",
                15
        );
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "no reading list",
                5);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY),
                "GOT IT button not found",
                15);
        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT),
                "input not found",
                5);

        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "no input",
                5);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5);
    }

    public void addArticleToExistingMyList(){
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "no more options",
                15
        );
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "no reading list",
                5);
        this.waitForElementAndClick(
                By.id(EXISTING_FOLDER),
                "cannot find already created list",
                5
        );
    }
    public void closeArticle(){
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article",
                15
        );
    }
    public void articleOpened(){
        this.waitForTitleElement();
    }
    public void checkArticleTitleElement(){
        this.assertElementPresent(By.id(TITLE), "Article title element is not present");
    }
}
