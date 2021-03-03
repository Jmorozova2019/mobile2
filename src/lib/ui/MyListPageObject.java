package lib.ui;

import lib.utils.Locator;

import io.appium.java_client.AppiumDriver;


public class MyListPageObject extends MainPageObject{

    private static final String
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }


    private static String getFolderByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public void openFolderByName(String name_of_folder) throws InterruptedException {
        Thread.sleep(1000);
        String folder_name = getFolderByName(name_of_folder);
        safeAction.click(new Locator(folder_name),
                "Cannot find folder", 15);
    }

    public void swipeByArticleToDelete(String article_title) throws InterruptedException {
        waitForArticleToAppearByTitle(article_title);
        Thread.sleep(1000);

        String article_title_rpl = getSavedArticleByTitle(article_title);
        swipe.swipeElementToLeft(
                new Locator(article_title_rpl), "Cannot find saved article");
        waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_title_rpl = getFolderByName(article_title);

        waitUtils.waitForElementNotPresent(new Locator(article_title_rpl),
            "Saved article still present with title " + article_title,
            15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        waitUtils.waitForElementPresent(new Locator(getFolderByName(article_title)),
            "Cannot find saved article by title " + article_title, 15
        );
    }

    public void openArticleFromMyList(String article_title)
    {
        safeAction.click(new Locator(getSavedArticleByTitle(article_title)),
            "Cannot find saved article by name " + article_title, 15
        );
    }
}
