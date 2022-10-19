package com.task1_screenscraper.unirest_client;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestServiceTest {
    RequestService requestService;

    @BeforeEach
    public void setup(){
        requestService = new RequestService();

    }

    @AfterEach
    public void teardown(){
        requestService = null;
    }

    @Test
    public void testDeleteRequestReturnsOK(){
        // Setup (if any)

        // Exercise
        HttpResponse<JsonNode> response = requestService.makeDeleteRequest();

        // Verify
        Assertions.assertEquals("OK", response.getStatusText());

        // Teardown (if any)
    }

    @Test
    public void testPostRequestReturnsCreated(){
        // Setup (if any)
        int alertType = 6;
        String heading = "Jumper Windows 11 Laptop";
        String description = "Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD";
        String url = "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth";
        String imageUrl = "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg";
        int priceInCents = 24999;
        requestService.setJSONObject(alertType, heading, description, url, imageUrl, priceInCents);

        // Exercise
        HttpResponse<JsonNode> response = requestService.makePostRequest();

        // Verify
        Assertions.assertEquals("Created", response.getStatusText());

        // Teardown (if any)
    }

}
