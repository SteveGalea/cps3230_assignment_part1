package com.task1_screenscraper.unirest_client;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.UUID;

public class RequestService {
    private JSONObject jsonObject;
    private final String endpoint;
    private final UUID myUUID;

    // constructor
    public RequestService(){
        endpoint = "https://api.marketalertum.com/Alert";
        myUUID = UUID.fromString("baf95487-17f6-40df-b758-3c938a0ec72a");
        jsonObject = null;
    }

    // setters and getters
    public void setJSONObject(int alertType, String heading, String description, String url, String imageUrl, int priceInCents){
        jsonObject = new JSONObject();
        jsonObject.put("alertType", alertType); // dont escape integers, otherwise :(
        jsonObject.put("heading", heading);
        jsonObject.put("description", description);
        jsonObject.put("url", url); // no https = no search
        jsonObject.put("imageUrl", imageUrl);
        jsonObject.put("postedBy", myUUID);
        jsonObject.put("priceInCents", priceInCents);
    }

    private JSONObject getJSONObject(){
        return jsonObject;
    }

    // delete request by user id
    public HttpResponse<JsonNode> makeDeleteRequest() {
        return Unirest.delete(endpoint +"?userId="+myUUID).asJson();
    }

    // post request of json object in body
    public HttpResponse<JsonNode> makePostRequest() {
        return Unirest.post(endpoint)
                .header("Content-Type", "application/json")
                .body(getJSONObject())
                .asJson();
    }

}
