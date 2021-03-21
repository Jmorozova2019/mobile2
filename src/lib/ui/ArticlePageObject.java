package lib.ui;

import org.junit.Assert;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
        TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_NAME_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        SEARCH_LANG_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/search_lang_button']";

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
        return titleElement.getAttribute("text");
    }

    public  void swipeToFooter()
    {
        swipeUpToFindElement(FOOTER_ELEMENT,"Cannot find end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        click(OPTIONS_BUTTON, "Cannot find button to open article options");

        waitForElementNotPresent(SEARCH_LANG_BUTTON,"Find button change language");
        click(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list");
        click(ADD_TO_MY_LIST_OVERLAY,"Cannot find 'GOT IT' tip overlay");
        clear(MY_LIST_NAME_INPUT, "Cannot find input to set name of articles folder");
        sendKeysWithoutPaste(MY_LIST_NAME_INPUT,"Cannot put text into articles folder input", name_of_folder);
        click(MY_LIST_NAME_BUTTON,"Cannot press OK button");
    }

    public void addSecondArticleToMyList(String name_of_folder)
    {
        click( OPTIONS_BUTTON,"Cannot find button to open article options");
        //чтобы не открывалось окно смены языка
        waitForElementNotPresent(SEARCH_LANG_BUTTON,"Find button change language");
        click(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to reading list");
        click("//*[@resource-id='org.wikipedia:id/item_title' and @text='" + name_of_folder +"']",
            "Cannot folder " + name_of_folder + " to list folders"
        );
    }

    public void closeArticle()
    {
        click(CLOSE_ARTICLE_BUTTON,"Cannot close article, cannot find X link");
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
