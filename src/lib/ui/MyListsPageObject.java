package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            TITLE_ELEMENT_IN_MY_LISTS;

    private static String getFolderXPathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",
                name_of_folder);
    }
    private static String getSavedArticleXPathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",
                article_title);
    }
    public MyListsPageObject (AppiumDriver driver) {
        super(driver);
    }
    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath,
                "Cannot find folder by name" + name_of_folder,
                5);
    }
    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXPathByName(article_title);
        this.swipeElementToLeft(article_xpath,
                "cannot swipe saved article");
        if (Platform.getInstance().isIos()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getFolderXPathByName(article_title);
        this.waitForElementNotPresent(article_xpath,
                "Saved article is still present with title" +article_title,
                15);
    }
    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getFolderXPathByName(article_title);
        this.waitForElementPresent(article_xpath,
                "Cannot find saved article by title" +article_title,
                15);
    }
    public String getArticleTitleInTheList(){
        WebElement title_element = waitForTitleInMyListElement();
        return title_element.getAttribute("text");
    }

    private WebElement waitForTitleInMyListElement () {
        return this.waitForElementPresent(TITLE_ELEMENT_IN_MY_LISTS, "Cannot find Article Title in my lists",
                15);
    }
}
