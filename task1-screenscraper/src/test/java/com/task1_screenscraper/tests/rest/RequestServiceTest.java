package com.task1_screenscraper.tests.rest;

import com.task1_screenscraper.products.Product;
import com.task1_screenscraper.rest.Requests;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestServiceTest {
    // TODO: I WILL MOST LIKELY DELETE THIS, I JUST WANTED TO HAVE A PROOF OF CONCEPT THAT MY REST API WORKS WELL.
    Requests requests;

    @BeforeEach
    public void setup(){
        requests = new Requests();
    }

    @AfterEach
    public void teardown(){
        requests = null;
    }

    @Test
    public void testDeleteRequestReturnsOK(){
        // Setup (if any)

        // Exercise
        HttpResponse<JsonNode> response = requests.makeDeleteRequest();

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
        Product product = new Product(alertType, heading, description, url, imageUrl, priceInCents);
//        requests.setJSONObject(alertType, heading, description, url, imageUrl, priceInCents);
        requests.setJSONObject(product); // setter injection

        // Exercise
        HttpResponse<JsonNode> response = requests.makePostRequest();

        // Verify
        Assertions.assertEquals("Created", response.getStatusText());

        // Teardown (if any)
    }

}
