package lib.ui;

import lib.utils.Locator;

import org.junit.Assert;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
        TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_NAME_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        SEARCH_LANG_BUTTON = "//*[@resource-id='org.wikipedia:id/search_lang_button']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return waitUtils.waitForElementPresent(new Locator(TITLE),
            "Cannot find article title in page", 15);
    }

    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public  void swipeToFooter()
    {
        swipe.swipeUpToFindElement(new Locator(FOOTER_ELEMENT),
            "Cannot find end of article", 20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        safeAction.click(new Locator(OPTIONS_BUTTON),
            "Cannot find button to open article options"
        );

        waitUtils.waitForElementNotPresent(new Locator(SEARCH_LANG_BUTTON),
                "Find button change language"
        );

        safeAction.click(new Locator("xpath", OPTIONS_ADD_TO_MY_LIST_BUTTON),
            "Cannot find option to add article to reading list"
        );

        safeAction.click(new Locator("id", ADD_TO_MY_LIST_OVERLAY),
            "Cannot find 'GOT IT' tip overlay"
        );

        safeAction.clear(new Locator("id", MY_LIST_NAME_INPUT),
            "Cannot find input to set name of articles folder"
        );

        safeAction.sendKeysWithoutPaste(new Locator("id", MY_LIST_NAME_INPUT),
            "Cannot put text into articles folder input", name_of_folder
        );

        safeAction.click(new Locator(MY_LIST_NAME_BUTTON),
            "Cannot press OK button");
    }

    public void addSecondArticleToMyList(String name_of_folder)
    {
        safeAction.click(
            new Locator(OPTIONS_BUTTON),
            "Cannot find button to open article options"
        );

        //чтобы не открывалось окно смены языка
        waitUtils.waitForElementNotPresent(
            new Locator(SEARCH_LANG_BUTTON),
            "Find button change language"
        );

        safeAction.click(
            new Locator(OPTIONS_ADD_TO_MY_LIST_BUTTON),
            "Cannot find option to add article to reading list"
        );

        safeAction.click(
            new Locator("//*[@resource-id='org.wikipedia:id/item_title' and @text='" + name_of_folder +"']"),
            "Cannot folder " + name_of_folder + " to list folders"
        );
    }

    public void closeArticle()
    {
        safeAction.click(new Locator(CLOSE_ARTICLE_BUTTON),
            "Cannot close article, cannot find X link"
        );
    }

    public void isArticleTitleEqualsExpected(String expected_title)
    {
        String article_title_fact = getArticleTitle();
        Assert.assertTrue(article_title_fact.equals(expected_title));
    }

    public void assertElementsPresentNowByXpath(String error_message)
    {
        checker.assertElementsPresentNowByXpath(TITLE, error_message);
    }
}
