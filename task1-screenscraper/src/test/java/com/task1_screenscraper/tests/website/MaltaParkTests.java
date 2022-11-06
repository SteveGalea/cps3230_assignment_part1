package com.task1_screenscraper.tests.website;

import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

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
    public void testGetALaptop(){
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
        Assertions.assertTrue(alertType>0);
        Assertions.assertTrue(heading.length()>0);
        Assertions.assertTrue(description.length()>0);
        Assertions.assertTrue(url.length()>0);
        Assertions.assertTrue(imageUrl.length()>0);
        Assertions.assertTrue(priceInCents>0);

        //Teardown
    }

    @Test
    public void testGetFirst5LaptopsUrlLinks(){
        //Setup
        WebElement searchBar;
        WebElement searchButton;
        List<String> first5ItemsUrls;

        //Exercise
        searchBar = maltaParkPageObject.getSearchBar();
        searchButton = maltaParkPageObject.getSearchButton();
        searchBar.sendKeys("Laptop");
        searchButton.submit();
        first5ItemsUrls = maltaParkPageObject.getFirst5ItemsUrls();

        //Verify
        Assertions.assertNotNull(searchBar);
        Assertions.assertNotNull(searchButton);
        Assertions.assertEquals(5, first5ItemsUrls.size());
        //Teardown
    }
}
