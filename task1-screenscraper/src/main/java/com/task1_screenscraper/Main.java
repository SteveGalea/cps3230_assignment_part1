package com.task1_screenscraper;

import com.task1_screenscraper.facade.Facade;

public class Main {
    public static void main(String args[]){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");

        Facade facade = new Facade();

        facade.scrapeAndUpload5AlertsUsingKeyword("Laptop");
        facade.verify5AlertsWereUploaded();
    }
}
