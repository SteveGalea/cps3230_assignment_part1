package com.task1_screenscraper.pageobjects;

import com.task1_screenscraper.converters.PriceConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MaltaParkPageObject {
    //should this page object be in main?
    WebDriver driver;
    WebDriverWait wait;
    PriceConverter priceConverter;

    public MaltaParkPageObject(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.priceConverter = new PriceConverter();
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
        int priceInCents = priceConverter.textToCents(rawPriceText);
        return priceInCents;
    }

    public int getProductAlertType() {
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

            // Anything else not considered above
            default: alertType = -1; break;
        }
        return alertType;
    }

    private String getCategoryOfItem() {
        By byCategoryXpath = By.xpath("//div[contains(@class,'ui') and contains(@class,'list') and contains(@class,'fixed-label') and contains(@class,'item-details')]/div[3]");
        WebElement webElement = driver.findElement(byCategoryXpath);
        return webElement.getText().split("Category:")[1];
    }
}
