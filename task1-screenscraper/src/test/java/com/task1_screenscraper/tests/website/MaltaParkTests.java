package com.task1_screenscraper.tests.website;

import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MaltaParkTests {
    WebDriver driver;
    WebDriverWait wait;
    WebElement mockWebElement;
    PriceConverter priceConverter;
    MaltaParkPageObject maltaParkPageObject;

    @BeforeEach
    public void setup(){
        //Setup before each test case
        driver = mock(ChromeDriver.class);
        wait = mock(WebDriverWait.class);
        priceConverter = mock(PriceConverter.class);

        maltaParkPageObject = mock(MaltaParkPageObject.class);
        maltaParkPageObject = new MaltaParkPageObject(driver, wait, priceConverter);
        mockWebElement = mock(WebElement.class);

        Mockito.when(wait.until(any())).thenReturn(true);
        Mockito.when(driver.findElement(Mockito.any())).thenReturn(mockWebElement);

    }

    @AfterEach
    public void teardown(){
        //Teardown before each test case
        maltaParkPageObject = null;
    }

    @Test
    public void testGetCloseButton(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        WebElement closeButtonElement = maltaParkPageObject.getCloseButton();

        //Verify
        Assertions.assertNotNull(closeButtonElement);

        //Teardown (if any)
    }

    @Test
    public void testGetSearchBar(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        WebElement searchBarElement = maltaParkPageObject.getSearchBar();

        //Verify
        Assertions.assertNotNull(searchBarElement);
        //Teardown (if any)
    }

    @Test
    public void testSearchButton(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        WebElement searchButtonElement = maltaParkPageObject.getSearchButton();

        //Verify
        Assertions.assertNotNull(searchButtonElement);

        //Teardown (if any)
    }

    @Test
    public void testGetProductHeading(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        String productHeadingString = maltaParkPageObject.getProductHeading();

        //Verify
        Assertions.assertNotNull(productHeadingString);

        //Teardown (if any)
    }
    @Test
    public void testGetProductDescription(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        String productDescriptionString = maltaParkPageObject.getProductDescription();

        //Verify
        Assertions.assertNotNull(productDescriptionString);

        //Teardown (if any)
    }

    @Test
    public void testGetProductUrl(){
        //Setup (if any)

        //Exercise
        maltaParkPageObject.getProductUrl();

        //Verify
        Mockito.verify(driver, Mockito.times(1)).getCurrentUrl();

        //Teardown (if any)
    }


    @Test
    public void testGetProductImageUrl(){
        //Setup (if any)
        Mockito.when(mockWebElement.getAttribute(anyString())).thenReturn(Mockito.anyString());

        //Exercise
        String productImageUrlString = maltaParkPageObject.getProductImageUrl();

        //Verify
        Assertions.assertNotNull(productImageUrlString);

        //Teardown (if any)
    }
    @Test
    public void testGetProductPriceInCents(){
        //Setup (if any)
        //dummy data passed, proper testing of conversion in price converter component
        int cents = 1234;
        doReturn(String.valueOf(cents)).when(mockWebElement).getText();
        doReturn(cents).when(priceConverter).textToCents(anyString());

        //Exercise
        int productPriceInCents = maltaParkPageObject.getProductPriceInCents();

        //Verify
        Assertions.assertEquals(cents, productPriceInCents);

        //Teardown (if any)
    }

    @Test
    public void testGetCategoryOfItem(){
        //Setup
        //Dummy data passed
        Mockito.when(mockWebElement.getText()).thenReturn("Category:RandomCategory");

        //Exercise
        String category = maltaParkPageObject.getCategoryOfItem();

        //Verify
        Assertions.assertEquals("RandomCategory",category);

        //Teardown
    }

    @Test
    public void testTextMapsToCorrectAlertType(){
        //Setup
        Mockito.when(mockWebElement.getText()).thenReturn(
                "Category:Cars","Category:Other",
                "Category:Marine",
                "Category:Long Lets","Category:Short / Holiday Lets",
                "Category:Property For Sale",
                "Category:Dolls & Bears", "Category:Toys",
                "Category:Cameras & Photo", "Category:Video Games",
                "Category:Unlisted Category");
        // 11 alerts are expected
        int[] expectedOutcomes = new int[]{
          1,1,2,3,3,4,5,5,6,6,-1
        };
        int[] actualOutcome = new int[11];

        //Exercise
        //iterate multiple times to check if the correct alert type was assigned at the partition ends
        for(int i = 0; i < 11; i++){
            actualOutcome[i] = maltaParkPageObject.getProductAlertType();
        }

        //Verify
        for(int i = 0; i < 11; i++){
            Assertions.assertEquals(expectedOutcomes[i],actualOutcome[i]);
        }

        //Teardown
    }

    @Test
    public void testGetFirst5ItemUrls(){
        //Setup
        List<WebElement> mockListElements = mock(List.class);
        Mockito.when(driver.findElements(any())).thenReturn(mockListElements);
        Mockito.when(mockWebElement.getAttribute(anyString())).thenReturn(Mockito.anyString());

        //        Mockito.when(driver.findElements(any())).thenReturn(mockListElements);
//        Mockito.when(mockWebElement.g)
        //        Mockito.when(mockListElements.forEach(any()).thenReturn(mock(List.class));

        //Exercise
        List<String> elements = maltaParkPageObject.getFirst5ItemsUrls();

        //Verify
        Assertions.assertNotNull(elements);
//        Mockito.verify(mockWebElement, times(52)).getAttribute(anyString());


        //Teardown
    }
//    @Test
//    public void testVisitMaltaPark(){
//        //verify page is present by ensuring driver.get() was invoked - spy like
//        Mockito.verify(driver, Mockito.times(1)).get(Mockito.anyString());
//    }
//
//
//    @Test
//    public void testVerifySearchElementsAreFound(){
//        //Setup
//        WebElement mockWebElement = mock(WebElement.class);
//        doReturn(mockWebElement).when(maltaParkPageObject).getSearchBar();
//        doReturn(mockWebElement).when(maltaParkPageObject).getSearchButton();
//
//        //Exercise
//        WebElement searchBar = maltaParkPageObject.getSearchBar();
//        WebElement searchButton = maltaParkPageObject.getSearchButton();
//
//        //Verify WebElements related to Search are Found
//        Assertions.assertNotNull(searchBar);
//        Assertions.assertNotNull(searchButton);
//    }
//
//    @Test
//    public void testVerifySearchForAProductAndRetrieveRelatedDataFromPage(){
//        //Setup
//        WebElement searchBar;
//        WebElement searchButton;
//        WebElement firstItem;
//
//        WebElement mockWebElement = mock(WebElement.class);
//        when(maltaParkPageObject.getSearchBar()).thenReturn(mockWebElement);
//        when(maltaParkPageObject.getSearchButton()).thenReturn(mockWebElement);
//        when(maltaParkPageObject.getFirstItem()).thenReturn(mockWebElement);
////        doReturn(mockWebElement).when(maltaParkPageObject).getSearchButton();
////        doReturn(mockWebElement).when(maltaParkPageObject).getFirstItem();
//
//        //mock retrieved data from maltapark
//        doReturn(6).when(maltaParkPageObject).getProductAlertType();
//        doReturn("Test Heading").when(maltaParkPageObject).getProductHeading();
//        doReturn("Test Description").when(maltaParkPageObject).getProductDescription();
//        doReturn("Test Url").when(maltaParkPageObject).getProductUrl();
//        doReturn("Test ImageUrl").when(maltaParkPageObject).getProductImageUrl();
//        doReturn(23).when(maltaParkPageObject).getProductPriceInCents();
//
//        //Exercise
//        searchBar = maltaParkPageObject.getSearchBar();
//        searchButton = maltaParkPageObject.getSearchButton();
//        searchBar.sendKeys(Mockito.anyString());
//        searchButton.submit();
//        firstItem = maltaParkPageObject.getFirstItem();
//        firstItem.click();
//
//        int alertType = maltaParkPageObject.getProductAlertType();
//        String heading = maltaParkPageObject.getProductHeading();
//        String description = maltaParkPageObject.getProductDescription();
//        String url = maltaParkPageObject.getProductUrl();
//        String imageUrl = maltaParkPageObject.getProductImageUrl();
//        int priceInCents = maltaParkPageObject.getProductPriceInCents();
//
//        //Verify
//        Mockito.verify(driver, Mockito.times(1)).get(Mockito.anyString());
//
//        Assertions.assertNotNull(searchBar);
//        Mockito.verify(searchBar, Mockito.times(1)).sendKeys(Mockito.anyString());
//
//        Assertions.assertNotNull(searchButton);
//        Mockito.verify(searchButton, Mockito.times(1)).submit();
//
//        Mockito.verify(firstItem, Mockito.times(1)).click();
//
//        Assertions.assertNotNull(firstItem);
//        Assertions.assertTrue(alertType>0);
//        Assertions.assertTrue(heading.length()>0);
//        Assertions.assertTrue(description.length()>0);
//        Assertions.assertTrue(url.length()>0);
//        Assertions.assertTrue(imageUrl.length()>0);
//        Assertions.assertTrue(priceInCents>0);
//    }
//
//    @Test
//    public void testSearchForAProductAndGetFirst5ProductUrlLinks(){
//        //Setup
//        WebElement searchBar;
//        WebElement searchButton;
//        List<String> first5ItemsUrls;
//
//        //mock return elements as dummy objects
//        WebElement mockWebElement = mock(WebElement.class);
//        List<String> mockUrlList = mock(List.class);
//
//        doReturn(mockWebElement).when(maltaParkPageObject).getSearchBar();
//        doReturn(mockWebElement).when(maltaParkPageObject).getSearchButton();
//        doReturn(mockUrlList).when(maltaParkPageObject).getFirst5ItemsUrls();
//        when(mockUrlList.size()).thenReturn(5);
//
//        //Exercise
//        searchBar = maltaParkPageObject.getSearchBar();
//        searchButton = maltaParkPageObject.getSearchButton();
//        searchBar.sendKeys(Mockito.anyString());
//        searchButton.submit();
//        first5ItemsUrls = maltaParkPageObject.getFirst5ItemsUrls();
//
//        //Verify
//        Assertions.assertNotNull(searchBar);
//        Mockito.verify(searchBar, Mockito.times(1)).sendKeys(Mockito.anyString());
//
//        Assertions.assertNotNull(searchButton);
//        Mockito.verify(searchButton, Mockito.times(1)).submit();
//
//        Assertions.assertNotNull(first5ItemsUrls);
//        Assertions.assertEquals(5, first5ItemsUrls.size());
//    }
}
