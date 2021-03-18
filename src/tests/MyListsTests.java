package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    @Test
    public void testSaveFirstArticleToMyList (){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("JavaScript");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }


        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIos()) {
        SearchPageObject.clickCancelButton();}

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        if (Platform.getInstance().isIos()) {
            NavigationUI.closePopup();
        }
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
        MyListsPageObject.openFolderByName(name_of_folder);}
        MyListsPageObject.swipeByArticleToDelete(article_title);

    }
    @Test
    public void testSave2ArticlesToTheList(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "King";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("King");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Ex5";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("King James Version");
        ArticlePageObject.waitForTitleElement();
        String second_article = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToExistingMyList();
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(first_article);
        MyListsPageObject.waitForArticleToDisappearByTitle(first_article);
        MyListsPageObject.waitForArticleToAppearByTitle(second_article);
        String title_of_article_in_my_list = MyListsPageObject.getArticleTitleInTheList();

        assertEquals("The title of the article is not equal to the title in my list",
                title_of_article_in_my_list,
                second_article);
    }

}
