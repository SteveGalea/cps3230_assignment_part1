package com.task1_screenscraper.pageobjects;

import com.task1_screenscraper.converters.PriceConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaltaParkPageObject extends PageObject{
    // helper method price converter called to convert text into a savable price
    PriceConverter priceConverter;

    public MaltaParkPageObject(WebDriver driver){
        super(driver);
        this.priceConverter = new PriceConverter();
    }

    public WebElement getCloseButton() {
        By byXpathButtonDisabled = By.xpath("//*[@id=\"okbutton\" and contains(., 'Close (1)')]");
        By byXpathButtonEnabled = By.xpath("//*[@id=\"okbutton\" and contains(., 'Close')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpathButtonDisabled));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpathButtonEnabled));
        wait.until(ExpectedConditions.elementToBeClickable(byXpathButtonEnabled));
        return driver.findElement(byXpathButtonEnabled);
    }

    public WebElement getSearchBar() {
        By bySearchBarId = By.id("search");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchBarId));
        return driver.findElement(bySearchBarId);
    }

    public WebElement getSearchButton() {
        By bySearchButtonXpath = By.xpath("//button[contains(@class, 'btn-search')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchButtonXpath));
        return driver.findElement(bySearchButtonXpath);
    }

    public WebElement getFirstItem() {
        By bySearchButtonXpath = By.xpath("//div[contains(@class, 'item') and contains(@class, 'featured') and contains(@class, 'e4') and contains(@class, 'e3') and contains(@class, 'e2') and contains(@class, 'i0')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchButtonXpath));
        return driver.findElement(bySearchButtonXpath);
    }

    public String getProductHeading() {
        By byHeadingXpath = By.xpath("//h1[@class='top-title']/span");
        WebElement webElement = driver.findElement(byHeadingXpath);
        return webElement.getText();
    }

    public String getProductDescription() {
        By byHeadingXpath = By.xpath("//div[@class='readmore-wrapper']");
        WebElement webElement = driver.findElement(byHeadingXpath);
        return webElement.getText();
    }

    public String getProductUrl() {
        return driver.getCurrentUrl();
    }

    public String getProductImageUrl() {
        By byImageXpath = By.xpath("//a[@class='fancybox']");
        WebElement webElement = driver.findElement(byImageXpath);
        return webElement.getAttribute("href");
    }

    public int getProductPriceInCents() {
        By byPriceXpath = By.xpath("//h1[@class='top-price']");
        WebElement webElement = driver.findElement(byPriceXpath);
        String rawPriceText = webElement.getText();
        return priceConverter.textToCents(rawPriceText);
    }

    public int getProductAlertType() { //TODO: ASK
        int alertType;
        String categoryText = getCategoryOfItem();

        // determine and assign Alert Type
        switch (categoryText){
            // Car
            case "Cars":
            case "Motorcycles":
            case "Quad Bikes":
            case "Scooters":
            case "Vans & Trucks":
            case "Vehicle Parts":
            case "Other":
                alertType = 1; break;

            // Boat
            case "Marine":
                alertType = 2; break;

            // PropertyForRent
            case "Long Lets":
            case "Short / Holiday Lets":
                alertType = 3; break;

            // PropertyForSale
            case "Property For Sale":
                alertType = 4; break;

            // Toys
            case "Dolls & Bears":
            case "Toys":
                alertType = 5; break;

            // Electronics
            case "Cameras & Photo":
            case "Computers & Office":
            case "Consumer Electronics":
            case "Home Appliances":
            case "Networking & Telecom":
            case "PDAs":
            case "TV":
            case "Video Games":
                alertType = 6; break;

            // Anything else not considered above will be invalid
            default: alertType = -1; break;
        }
        return alertType;
//        return 6;
    }

    private String getCategoryOfItem() {
        By byCategoryXpath = By.xpath("//div[contains(@class,'ui') and contains(@class,'list') and contains(@class,'fixed-label') and contains(@class,'item-details')]/div[3]");
        WebElement webElement = driver.findElement(byCategoryXpath);
        return webElement.getText().split("Category:")[1];
    }

    public List<String> getFirst5ItemsUrls() {
        By byCommonClassXpath =  By.xpath("//div[contains(@class,'ui') and contains(@class,'items') and contains(@class,'listings')]/div[contains(@class,'item')]/*/a[@class='header']");
        List<WebElement> allElements = driver.findElements(byCommonClassXpath);

        List<String> allLinks = new ArrayList<String>(5);
        allElements.forEach(element -> allLinks.add(element.getAttribute("href")));
        return allLinks.stream().limit(5).collect(Collectors.toList());
    }

}
