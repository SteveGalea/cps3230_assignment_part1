package com.task1_screenscraper.tests.screenscraper;

import com.task1_screenscraper.screenscraper.MaltaParkScreenScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MaltaParkScreenScraperTests {
//    WebDriver driver;
//    MaltaParkScreenScraper maltaParkScreenScraper;
//    String eCommerceWebsiteUrl = "https://www.maltapark.com/";
////    String marketAlertWebsiteUrl = "https://www.marketalertum.com/Alerts/List";
//
//
//    @BeforeEach
//    public void setup(){
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");
//        driver = new ChromeDriver();
//        maltaParkScreenScraper = new MaltaParkScreenScraper(driver);
//        //        marketAlertUMPageObject = new MarketAlertUMPageObject(driver);
//    }
//
//    @AfterEach
//    public void teardown(){
//        maltaParkScreenScraper.stopScraping();
//        maltaParkScreenScraper.deleteMarketAlerts();
//    }
//
//    // test get 5 alerts and put them on the website
//    // returns ok, created, ... status code
//    // what if web went down, databases failed, etc
//
//    @Test
//    public void testScrapeFirst5LaptopResults(){
//        // Setup
//        maltaParkScreenScraper.goToUrl(eCommerceWebsiteUrl);
//        maltaParkScreenScraper.closeMessageModal();
//        maltaParkScreenScraper.searchProductByTerm("Laptop");
//        maltaParkScreenScraper.scrapeFirst5Results();
//        maltaParkScreenScraper.uploadProductListToMarketAlert();
//
//        // Exercise
//        int size = maltaParkScreenScraper.getProductList().size();
//
//        // Verify
//        Assertions.assertEquals(5, size);
//
//        // Teardown
//    }

}
