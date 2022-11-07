package com.task1_screenscraper.rest;

import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.utils.MarketAlertServer;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.openqa.selenium.json.Json;

import java.util.UUID;

public class RequestHelper {
    private JSONObject jsonObject;
    private final String endpoint;
    private final UUID myUUID;
    // constructor
    public RequestHelper(){
        jsonObject = new JSONObject();
        endpoint = "https://api.marketalertum.com/Alert";
        myUUID = UUID.fromString("baf95487-17f6-40df-b758-3c938a0ec72a");
    }

    // getters
    private JSONObject getJSONObject(){
        return jsonObject;
    }

    // setters
    public void setJSONObject(Product product){
        jsonObject = new JSONObject();
        jsonObject.put("alertType", product.getAlertType()); // dont escape integers, otherwise :(
        jsonObject.put("heading", product.getHeading());
        jsonObject.put("description", product.getDescription());
        jsonObject.put("url", product.getUrl()); // no https = no search
        jsonObject.put("imageUrl", product.getImageUrl());
        jsonObject.put("postedBy", myUUID);
        jsonObject.put("priceInCents", product.getPriceInCents());
    }

    // delete request using UnirestApi
    public int makeDeleteRequest() {
        HttpResponse<JsonNode> response = Unirest.delete(endpoint + "?userId=" + myUUID).asJson();
        return response.getStatus();
    }

    // post request using UnirestApi
    public int makePostRequest() {
        HttpResponse<JsonNode> response = Unirest.post(endpoint)
                .header("Content-Type", "application/json")
                .body(getJSONObject())
                .asJson();
        return response.getStatus();
    }

}
