package com.task1_screenscraper.tests.rest;

import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.rest.RequestHelper;
import com.task1_screenscraper.utils.MarketAlertServer;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RequestHelperTest {
    RequestHelper requestHelper;
    MarketAlertServer marketAlertServer;


    @BeforeEach
    public void setup(){
        requestHelper = new RequestHelper();
        marketAlertServer = mock(MarketAlertServer.class);
    }

    @AfterEach
    public void teardown(){
        requestHelper = null;
    }

    @Test
    public void testMakeDeleteRequest(){
        // Setup (if any)

        // Exercise
        int statusCode = requestHelper.makeDeleteRequest() ;

        // Verify
        Assertions.assertEquals(marketAlertServer.OK,statusCode);

        // Teardown (if any)
    }

    @Test
    public void testPostRequestReturnsCreated(){
        // Setup (if any)

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
        requestHelper.makeDeleteRequest();
    }

}
