package lib.ui;

import lib.utils.Locator;
import io.appium.java_client.AppiumDriver;


public class SearchPageObject extends MainPageObject{

    private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
    SEARCH_INPUT = "//*[contains(@text,'Search…')]",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL =
        "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text ='{SUBSTRING}']",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']" +
         "/*[@resource-id='org.wikipedia:id/page_list_item_container']";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* Template */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* Template */

    //Search
    public void initSearchInput()
    {
        this.safeAction.clear(new Locator(SEARCH_INIT_ELEMENT),
            "Cannot find and click search init element"
        );
        this.waitUtils.waitForElementPresent(
            new Locator(SEARCH_INPUT),
            "Cannot find search input after clicking search init element");

    }

    public void typeSearchLine(String search_line)
    {
        this.safeAction.sendKeys(new Locator(SEARCH_INPUT),
            search_line, "Cannot find and type into search input"
        );
    }

    public void waitForSearchResult(String substring)
    {
        String string_result_xpath = getResultSearchElement(substring);
        waitUtils.waitForElementPresent(new Locator(string_result_xpath),
            "Cannot find search result with substring " + substring
        );
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String string_result_xpath = getResultSearchElement(substring);
        safeAction.click(new Locator(string_result_xpath),
            "Cannot find and click search result with substring " + substring
        );
    }
    //---End search

    //Cancel
    public void waitForCancelButtonToAppear()
    {
        waitUtils.waitForElementPresent(new Locator("id", SEARCH_CANCEL_BUTTON),
            "Cannot find search cancel button"
        );
    }

    public void waitForCancelButtonToDisappear()
    {
        waitUtils.waitForElementNotPresent(new Locator("id", SEARCH_CANCEL_BUTTON),
            "Search cancel button is still present",
            20
        );
    }

    public void clickCancelSearch()
    {
        safeAction.click(new Locator("id", SEARCH_CANCEL_BUTTON),
            "Cannot find and click search cancel button"
        );
    }
    //---End Cancel

    public int getAmountOfFoundArticles()
    {
        waitUtils.waitForElementPresent(new Locator(SEARCH_RESULT_ELEMENT),
            "Cannot find anything by the request"
        );

        return new Locator(SEARCH_RESULT_ELEMENT).getAmountOfElements(driver);
    }


    public void assertThereIsNotResultOfSearch()
    {
        checker.assertElementNotPresent(new Locator(SEARCH_RESULT_ELEMENT),
                "We supported not to find any result"
        );
    }
}
