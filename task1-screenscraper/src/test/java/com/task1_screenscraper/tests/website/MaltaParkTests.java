package com.task1_screenscraper.tests.website;

import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MaltaParkTests {
    WebDriver driver;
    MaltaParkPageObject maltaParkPageObject;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.maltapark.com/");

        maltaParkPageObject = new MaltaParkPageObject(driver);
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

    @Test
    public void testGetAnElectronic(){
        //Setup
        WebElement searchBar;
        WebElement searchButton;
        WebElement firstItem;

        //Exercise
        searchBar = maltaParkPageObject.getSearchBar();
        searchButton = maltaParkPageObject.getSearchButton();
        searchBar.sendKeys("Laptop");
        searchButton.submit();
        firstItem = maltaParkPageObject.getFirstItem();
        firstItem.click();

        // int alertType = get number from category by switch case: computer & office /electronics, then alert will pertain to electronics
        int alertType = maltaParkPageObject.getProductAlertType();
        String heading = maltaParkPageObject.getProductHeading();
        String description = maltaParkPageObject.getProductDescription();
        String url = maltaParkPageObject.getProductUrl();
        String imageUrl = maltaParkPageObject.getProductImageUrl();
        int priceInCents = maltaParkPageObject.getProductPriceInCents();

        //Verify
        Assertions.assertNotNull(searchBar);
        Assertions.assertNotNull(searchButton);
        Assertions.assertNotNull(firstItem);
        Assertions.assertEquals(6, alertType);
        Assertions.assertEquals("Apple MacBook Pro, 13\" 256GB (2016)", heading);
        Assertions.assertTrue(description.length()>0);
        Assertions.assertEquals("https://www.maltapark.com/item/details/9506959", url);
        Assertions.assertEquals("https://www.maltapark.com/asset/itemphotos/9506959/9506959_1.jpg?_ts=3", imageUrl);
        Assertions.assertEquals(70000, priceInCents);
        //Teardown
    }

    /*public void testGetAnElectronic(){
    public void testGetAnElectronic(){
    public void testGetAnElectronic(){
    public void testGetAnElectronic(){
    public void testGetAnElectronic(){*/
}
