package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
            SEARCH_RESULT_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ARTICLE_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/following-sibling::*[@text='{DESCRIPTION}']",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
            SEARCH_INPUT_ID = "id:org.wikipedia:id/search_src_text",
            SEARCH_EMPTY_PAGE = "id:org.wikipedia:id/search_empty_message";
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }
    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementWithTitleAndDescriptionXpath(String title, String description)
    {
        return SEARCH_RESULT_ARTICLE_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /*TEMPLATES METHODS*/
    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click init search element",5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking init search element",
                5);
    }
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find Cancel Button", 5);
    }
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Cancel Button is still present", 5);
    }
    public void clickCancelButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot click Cancel button", 5);
    }
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT,
                search_line,
                "Cannot find input and type search line",
                15);
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring" + substring, 5);
    }
    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementWithTitleAndDescriptionXpath(title, description);
        this.waitForElementPresent(search_result_xpath,
                "Cannot find element with " + title +"and"+ description,
                15);
    }
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + substring, 10);
    }
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }
    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed to find no results");
    }
    public void clearSearchField(){
        this.waitForElementAndClear(SEARCH_INPUT_ID, "Input field not found to clear it", 15);
    }

    public void waitForEmptySearchLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_PAGE, "There is results on the page", 15);
    }
}
