package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.time.Duration;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = articlePageObject.getArticleTitle();

        rotateScreenLandscape();

        String title_after_rotation = articlePageObject.getArticleTitle();

        assertEquals(
            "Article title have been changed after screen rotation",
            title_before_rotation,
            title_after_rotation
        );

        rotateScreenPotrait();

        String title_after_secont_rotation = articlePageObject.getArticleTitle();

        assertEquals(
            "Article title have been changed after screen rotation",
            title_before_rotation,
            title_after_secont_rotation
        );
    }

    @Test
    public void testRunAppInBackground()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        backgroundApp(Duration.ofSeconds(2));
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
