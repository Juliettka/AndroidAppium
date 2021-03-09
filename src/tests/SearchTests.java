package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testSearchAndCancel() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("King");
        SearchPageObject.waitForSearchResult("King-Rega");
        SearchPageObject.waitForSearchResult("Influential 1611 English version of the Bible");
        SearchPageObject.clearSearchField();
        SearchPageObject.waitForEmptySearchLabel();
    }

    @Test
    public void testAmount0SearchResult(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "mghjgjhghj";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found few results",
                amount_of_search_results > 0
        );
    }
    @Test
    public void testCancelSearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

 /*//   @Test
  //  public void testAssertHasText() throws Exception {
 //       MainPageObject.assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
  //              "Search container does not have text Search Wikipedia",
  //              "Search Wikipedia");
  //  }



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






    */

}
