package com.task1_screenscraper.tests.screenscraper;

import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import com.task1_screenscraper.screenscraper.MaltaParkScreenScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MaltaParkScreenScraperTests {
    WebDriver driver;
    WebDriverWait wait;
    PriceConverter priceConverter;
    MaltaParkScreenScraper maltaParkScreenScraper;
    MaltaParkPageObject maltaParkPageObject;
    WebDriver.Options mockOptions;
    WebDriver.Window mockWindow;
    List<Product> productList;
    String eCommerceWebsiteUrl = "https://www.maltapark.com/";
//    String marketAlertWebsiteUrl = "https://www.marketalertum.com/Alerts/List";


    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");
        driver = mock(ChromeDriver.class);
        wait = mock(WebDriverWait.class);
        priceConverter = mock(PriceConverter.class);
        productList = mock(ArrayList.class);
        maltaParkPageObject = mock(MaltaParkPageObject.class);
        maltaParkScreenScraper = new MaltaParkScreenScraper(driver,wait,priceConverter, maltaParkPageObject, productList);
//        driver = new ChromeDriver();
//        maltaParkScreenScraper = new MaltaParkScreenScraper(driver, wait, priceConverter);
        //        marketAlertUMPageObject = new MarketAlertUMPageObject(driver);
    }

    @AfterEach
    public void teardown(){
//        maltaParkScreenScraper.stopScraping();
//        maltaParkScreenScraper.deleteMarketAlerts();
    }

    public void setupOfMocksForGoToUrl(){
        mockOptions = mock(WebDriver.Options.class);
        mockWindow = mock(WebDriver.Window.class);

        when(driver.manage()).thenReturn(mockOptions);
        when(mockOptions.window()).thenReturn(mockWindow);
        doNothing().when(mockWindow).maximize();
    }
    @Test
    public void testGoToUrlOpensAWebPageFromUrl(){
        //Setup
        setupOfMocksForGoToUrl();

        //Exercise
        maltaParkScreenScraper.goToUrl(eCommerceWebsiteUrl);

        //Verify
        Mockito.verify(driver, times(1)).get(anyString());

        //Teardown
    }

    @Test
    public void testCloseMessageModalClicksCloseButton(){
        //Setup
        WebElement mockButton = mock(WebElement.class);
        when(maltaParkPageObject.getCloseButton()).thenReturn(mockButton);

        //Exercise
        maltaParkScreenScraper.closeMessageModal();

        //Verify
        Mockito.verify(mockButton, times(1)).click();

        //Teardown
    }

    @Test
    public void testSearchProductByTermClicksSearchButton(){
        //Setup
        WebElement mockSearchBar = mock(WebElement.class);
        WebElement mockSearchButton = mock(WebElement.class);
        when(maltaParkPageObject.getSearchBar()).thenReturn(mockSearchBar);
        when(maltaParkPageObject.getSearchButton()).thenReturn(mockSearchButton);
        doNothing().when(mockSearchBar).sendKeys(anyString());

        //Exercise
        maltaParkScreenScraper.searchProductByTerm("Random Product");

        //Verify
        Mockito.verify(mockSearchButton, times(1)).submit();

        //Teardown
    }

    @Test
    public void testStopScrapingToQuitScraping(){
        //Setup
        doNothing().when(driver).quit();

        //Exercise
        maltaParkScreenScraper.stopScraping();

        //Verify
        Mockito.verify(driver, times(1)).quit();

        //Teardown
    }

    @Test // TODO SOLVE THIS ISSUE
    public void testScrapeFirst5ResultsToProductList(){
        //Setup
        List<Product> productListActual = new ArrayList<>();
        List<String> mockListOf5Links = mock(List.class);
        Iterator<String> mockLinkIterator = mock(Iterator.class);
        when(maltaParkPageObject.getFirst5ItemsUrls()).thenReturn(mockListOf5Links);
        when(mockListOf5Links.size()).thenReturn(5);

        setupOfMocksForGoToUrl();
        doCallRealMethod().when(mockListOf5Links).forEach(any((Consumer.class)));
        when(mockListOf5Links.iterator()).thenReturn(mockLinkIterator);

        when(maltaParkPageObject.getProductAlertType()).thenReturn(6);
        when(maltaParkPageObject.getProductHeading()).thenReturn("Test Heading");
        when(maltaParkPageObject.getProductDescription()).thenReturn("Test Description");
        when(maltaParkPageObject.getProductUrl()).thenReturn("Test Url");
        when(maltaParkPageObject.getProductImageUrl()).thenReturn("Test ImageUrl");
        when(maltaParkPageObject.getProductPriceInCents()).thenReturn(6);

        doCallRealMethod().when(productList).add(any());
        //Exercise
        maltaParkScreenScraper.scrapeFirst5Results();

        //Verify
        Assertions.assertEquals(5, mockListOf5Links.size()); // assert we have
//        Assertions.assertEquals(5, maltaParkScreenScraper.getProductList().size());
//        Mockito.verify(mockListOf5Links, times(5)).forEach(any()); // assert that our iterator was invoked 5 times
//        Assertions.assertEquals();
        Mockito.verify(productList, times(5)).add(any()); // assert that our iterator was invoked 5 times

        //Teardown
    }

    // test get 5 alerts and put them on the website
    // returns ok, created, ... status code
    // what if web went down, databases failed, etc

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
