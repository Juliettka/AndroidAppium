package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text = 'View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            EXISTING_FOLDER = "id:org.wikipedia:id/item_container";
    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find Article Title",
                15);
    }
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT,
                "Cannot find the end of article",
                20);
    }
    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "no more options",
                15
        );
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "no reading list",
                5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "GOT IT button not found",
                15);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "input not found",
                5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "no input",
                5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5);
    }

    public void addArticleToExistingMyList(){
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "no more options",
                15
        );
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "no reading list",
                5);
        this.waitForElementAndClick(
                EXISTING_FOLDER,
                "cannot find already created list",
                5
        );
    }
    public void closeArticle(){
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                  "Cannot close article",
                15
        );
    }
    public void articleOpened(){
        this.waitForTitleElement();
    }
    public void checkArticleTitleElement(){
        this.assertElementPresent(TITLE, "Article title element is not present");
    }
}
