package com.task1_screenscraper.tests.screenscraper;

import com.beust.ah.A;
import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import com.task1_screenscraper.rest.RequestHelper;
import com.task1_screenscraper.screenscraper.MaltaParkScreenScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MaltaParkScreenScraperTests {
    WebDriver driver;
    WebDriverWait wait;
    PriceConverter priceConverter;
    RequestHelper requestHelper;
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
        requestHelper = mock(RequestHelper.class);
//        productList = new ArrayList<>();
        maltaParkPageObject = mock(MaltaParkPageObject.class);
        maltaParkScreenScraper = new MaltaParkScreenScraper(driver,wait,priceConverter, maltaParkPageObject, productList, requestHelper);
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

    @Test
    public void testScrapeFirst5ResultsToProductList(){
        //Setup
        List<String> mockListOf5Links = new ArrayList<>();
        mockListOf5Links.add("Link1");
        mockListOf5Links.add("Link2");
        mockListOf5Links.add("Link3");
        mockListOf5Links.add("Link4");
        mockListOf5Links.add("Link5");

        when(maltaParkPageObject.getFirst5ItemsUrls()).thenReturn(mockListOf5Links);

        setupOfMocksForGoToUrl();
//        Iterate over mocked list
//        Iterator<String> mockLinkIterator = mock(Iterator.class);
//        doCallRealMethod().when(mockListOf5Links).forEach(any((Consumer.class)));
//        when(mockListOf5Links.iterator()).thenReturn(mockLinkIterator);

        when(maltaParkPageObject.getProductAlertType()).thenReturn(6);
        when(maltaParkPageObject.getProductHeading()).thenReturn("Test Heading");
        when(maltaParkPageObject.getProductDescription()).thenReturn("Test Description");
        when(maltaParkPageObject.getProductUrl()).thenReturn("Test Url");
        when(maltaParkPageObject.getProductImageUrl()).thenReturn("Test ImageUrl");
        when(maltaParkPageObject.getProductPriceInCents()).thenReturn(6);

        //Exercise
        maltaParkScreenScraper.scrapeFirst5Results();

        //Verify
        Assertions.assertEquals(5, mockListOf5Links.size()); // assert we have
        verify(productList, times(5)).add(any()); // assert that our iterator was invoked 5 times
        //Teardown
    }

    @Test
    public void testUploadProductListToMarketAlertEndpointIsDone(){
        //Setup
        Product product = new Product(6,"Test Heading","Test Description", "Test Url", "Test ImageUrl",22);

        productList = new ArrayList<>();
        productList.add(product);
        productList.add(product);
        productList.add(product);
        productList.add(product);
        productList.add(product);
        maltaParkScreenScraper.setProductList(productList);

        doNothing().when(requestHelper).setJSONObject(any()); // fix this
        when(requestHelper.makePostRequest()).thenReturn(201);

        //Exercise
        maltaParkScreenScraper.uploadProductListToMarketAlert();

        //Verify
        verify(requestHelper, times(5)).setJSONObject(any());
        verify(requestHelper, times(5)).makePostRequest();

        Assertions.assertEquals(201, requestHelper.makePostRequest());

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
