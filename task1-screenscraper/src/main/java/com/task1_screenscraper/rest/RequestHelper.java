package com.task1_screenscraper.rest;

public class RequestHelper {
    private RequestMaker requestMaker;

    // constructor
    public RequestHelper(){
        requestMaker = null;
    }

    //setter
    public void setRequestMaker(RequestMaker requestMaker) {
        this.requestMaker = requestMaker;
    }

    // post and delete helpers
    public int post() {
        if(requestMaker == null){
            return -1; // bad request returned
        }else {
            int statusCode;
            int tries = 1;
            do{
                statusCode = requestMaker.makePostRequest();
                if (statusCode == 201) {
                    return statusCode;
                } else {
                    tries++;
                }
            }while(tries <= 3); // assume status code is returned as is once 3 recalls are done to post request
            return statusCode;
        }
    }
    public int delete() {
        if(requestMaker == null){
            return -1; // bad request returned
        }else {
            int statusCode;
            int tries = 1;
            do{
                statusCode = requestMaker.makeDeleteRequest();
                if (statusCode == 200) {
                    return statusCode;
                } else {
                    tries++;
                }
            }while(tries <= 3); // assume status code is returned as is once 3 recalls are done to post request
            return statusCode;
        }
    }

}
