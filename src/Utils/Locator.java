package Utils;

import org.openqa.selenium.By;

public class Locator
{
    private String strategy;
    private String locator;
    private By by;

    public Locator(String strategy, String locator) {
        this.strategy = strategy;
        this.locator = locator;

        switch (strategy)
        {
            case("id"):
                by = By.id(this.locator);
                break;
            case("name"):
                by = By.name(this.locator);
                break;
            case("xpath"):
                by = By.xpath(this.locator);
                break;
            case("tagName"):
                by = By.tagName(this.locator);
                break;
            case("className"):
                by = By.className(this.locator);
                break;
            case("cssSelector"):
                by = By.cssSelector(this.locator);
                break;
            case("linkText"):
                by = By.linkText(this.locator);
                break;
            case("partialLinkText"):
                by = By.partialLinkText(this.locator);
                break;
            default: System.out.println("Unexpected strategy location");
        }
    }

    public String getStrategy(){ return strategy; }
    public String getLocator(){ return locator; }
    public By getBy() { return by; }
}
