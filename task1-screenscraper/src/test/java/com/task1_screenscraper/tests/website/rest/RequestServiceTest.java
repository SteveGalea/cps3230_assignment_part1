package com.task1_screenscraper.tests.website.rest;

import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.rest.RequestHelper;
import com.task1_screenscraper.utils.MarketAlertServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

public class RequestServiceTest {
    RequestHelper requestHelper;
    MarketAlertServer marketAlertServer;

    @BeforeEach
    public void setup(){
        requestHelper = mock(RequestHelper.class);
        marketAlertServer = mock(MarketAlertServer.class);
    }

    @AfterEach
    public void teardown(){
        requestHelper = null;
    }

    @Test
    public void testDeleteRequestReturnsOK(){
        // Setup (if any)
        Mockito.when(requestHelper.makeDeleteRequest()).thenReturn(marketAlertServer.OK);

        // Exercise
        int statusCode = requestHelper.makeDeleteRequest() ;

        // Verify
        Assertions.assertEquals(MarketAlertServer.OK,statusCode);

        // Teardown (if any)
    }

    @Test
    public void testPostRequestReturnsCreated(){
        // Setup (if any)
        Mockito.when(requestHelper.makePostRequest()).thenReturn(marketAlertServer.CREATED);

        // create dummy object
        int alertType = 6;
        String heading = "Jumper Windows 11 Laptop";
        String description = "Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD";
        String url = "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth";
        String imageUrl = "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg";
        int priceInCents = 24999;
        Product product = new Product(alertType, heading, description, url, imageUrl, priceInCents);

        // setter injection
        requestHelper.setJSONObject(product);

        // Exercise
        int statusCode = requestHelper.makePostRequest();

        // Verify
        Assertions.assertEquals(marketAlertServer.CREATED, statusCode);

        // Teardown (if any)
    }

    @Test
    public void testPostRequestReturnsBadRequestAsNoBodyIsSupplied(){
        // Setup (if any)
        Mockito.when(requestHelper.makePostRequest()).thenReturn(marketAlertServer.BAD_REQUEST);

        // Exercise
        int response = requestHelper.makePostRequest();

        // Verify
        Assertions.assertEquals(marketAlertServer.BAD_REQUEST, response);

        // Teardown (if any)
    }
    @Test
    public void testPostRequestReturnsUnsupportedMediaTypeWhenNotPassingJsonBody(){
        // Setup (if any)
        Mockito.when(requestHelper.makePostRequest()).thenReturn(marketAlertServer.UNSUPPORTED_MEDIA_TYPE);

        // Exercise
        int response = requestHelper.makePostRequest();

        // Verify
        Assertions.assertEquals(marketAlertServer.UNSUPPORTED_MEDIA_TYPE, response);

        // Teardown (if any)
    }
//    @Test
//    public void testDeleteRequestOKOnThirdTry(){
//        // Setup (if any)
//        Mockito.when(marketAlertServer.getStatus(any())).thenReturn(marketAlertServer.SERVICE_UNAVAILABLE, marketAlertServer.SERVICE_UNAVAILABLE, marketAlertServer.OK);
//
//        requestHelper.setMarketAlertServer(marketAlertServer);
//        // Exercise
//        int response = requestHelper.makeDeleteRequest();
//
//        // Verify
//        Assertions.assertNotNull(requestHelper.getMarketAlertServer());
//        Assertions.assertEquals(marketAlertServer.OK, response);
//
//        // Teardown (if any)
//    }

// @Test
//    public void testPostRequest2(){
//        // Setup (if any)
////        Mockito.when(requestHelper.makePostRequest()).thenReturn("Created");
//        requestHelper = new RequestHelper();
//        // create dummy object
//        int alertType = 6;
//        String heading = "Jumper Windows 11 Laptop";
//        String description = "Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD";
//        String url = "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth";
//        String imageUrl = "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg";
//        int priceInCents = 24999;
//        Product product = new Product(alertType, heading, description, url, imageUrl, priceInCents);
//
//        // setter injection
////        requestHelper.setJSONObject(product);
//
//        // Exercise
//        String response = requestHelper.makePostRequest();
//
//        // Verify
//        Assertions.assertEquals("Bad Request", response);
//
//        // Teardown (if any)
//    }

}
