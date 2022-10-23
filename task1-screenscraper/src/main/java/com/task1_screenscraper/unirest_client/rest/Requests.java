package com.task1_screenscraper.unirest_client.rest;

import com.task1_screenscraper.unirest_client.products.Product;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.UUID;

public class Requests {
    private JSONObject jsonObject;
    private final String endpoint;
    private final UUID myUUID;

    // constructor
    public Requests(){
        endpoint = "https://api.marketalertum.com/Alert";
        myUUID = UUID.fromString("baf95487-17f6-40df-b758-3c938a0ec72a");
        jsonObject = null;
    }

    // setters and getters
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

    private JSONObject getJSONObject(){
        return jsonObject;
    }

    // delete request
    public HttpResponse<JsonNode> makeDeleteRequest() {
        return Unirest.delete(endpoint +"?userId="+myUUID).asJson();
    }

    // post request
    public HttpResponse<JsonNode> makePostRequest() {
        return Unirest.post(endpoint)
                .header("Content-Type", "application/json")
                .body(getJSONObject())
                .asJson();
    }

}
