package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject{

    protected static String
        SAVED_BLOCK,
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL;

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
        click(folder_name, "Cannot find folder", 15);
    }

    public void clickSavedBlock()
    {
        click(SAVED_BLOCK, "Cannot find folder", 15);
    }

    public void swipeByArticleToDelete(String article_title) throws InterruptedException {
        waitForArticleToAppearByTitle(article_title);
        Thread.sleep(1000);

        String article_title_rpl = getSavedArticleByTitle(article_title);
        swipeElementToLeft(article_title_rpl, "Cannot find saved article");

        if (Platform.getInstance().isIOS()) {
            clickElementToTheRightUpperCorner(article_title_rpl, "Cannot find saved article");
        }

        waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_title_rpl = getFolderByName(article_title);

        waitForElementNotPresent(article_title_rpl,
                "Saved article still present with title " + article_title,15);
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        waitForElementPresent(getFolderByName(article_title),
            "Cannot find saved article by title " + article_title, 15
        );
    }

    public void openArticleFromMyList(String article_title)
    {
        click(getSavedArticleByTitle(article_title),
            "Cannot find saved article by name " + article_title, 15);
    }
}
