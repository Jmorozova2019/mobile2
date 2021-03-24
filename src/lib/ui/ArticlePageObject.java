package lib.ui;

import lib.Platform;
import org.junit.Assert;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        SAVE_BUTTON,
        NOTIFICATION_ADDED_TO_LIST,
        REMOVE_FROM_SAVED,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        CLOSE_SEARCH_ARTICLE_LIST,
        SEARCH_LANG_BUTTON;

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return waitForElementPresent(TITLE, "Cannot find article title in page", 15);
    }

    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        if(Platform.getInstance().isAndroid())
        {
            return titleElement.getAttribute("text");
        } else
        {
            return titleElement.getAttribute("name");
        }
    }

    public  void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find end of article", 40);
        } else {
            swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find end of article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        click(OPTIONS_BUTTON, "Cannot find button to open article options");

        waitForElementNotPresent(SEARCH_LANG_BUTTON,"Find button change language");
        click(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list");
        click(ADD_TO_MY_LIST_OVERLAY,"Cannot find 'GOT IT' tip overlay");
        clear(MY_LIST_NAME_INPUT, "Cannot find input to set name of articles folder");
        sendKeysWithoutPaste(MY_LIST_NAME_INPUT,"Cannot put text into articles folder input", name_of_folder);
        click(MY_LIST_OK_BUTTON,"Cannot press OK button");
    }
/*
    public void addSecondArticleToMyList(String name_of_folder)
    {
        click( OPTIONS_BUTTON,"Cannot find button to open article options");
        //чтобы не открывалось окно смены языка
        waitForElementNotPresent(SEARCH_LANG_BUTTON,"Find button change language");
        click(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list");
        click("//*[@resource-id='org.wikipedia:id/item_title' and @text='" + name_of_folder +"']",
            "Cannot folder " + name_of_folder + " to list folders"
        );
    }*/

    public void addArticleToMySaved()
    {
        //click(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
        click(SAVE_BUTTON, "Cannot find button to open article options");
        waitForElementPresent(NOTIFICATION_ADDED_TO_LIST,
                "Cannot find notification of saved article");
        waitForElementNotPresent(NOTIFICATION_ADDED_TO_LIST,
                "No close notification of saved article");
    }

    public void closeArticle()
    {
        click(CLOSE_ARTICLE_BUTTON,"Cannot close article, cannot find back button");
    }

    public void closeSearchArticleList()
    {
        click(CLOSE_SEARCH_ARTICLE_LIST,"Cannot close article list, cannot find back button");
    }

    public void isArticleTitleEqualsExpected(String expected_title)
    {
        String article_title_fact = getArticleTitle();
        Assert.assertTrue(article_title_fact.equals(expected_title));
    }

    public void assertElementsPresentNowByXpath(String error_message)
    {
        assertElementsPresentNowByXpath(TITLE, error_message);
    }

}
