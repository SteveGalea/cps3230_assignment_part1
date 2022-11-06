package com.task1_screenscraper.tests;

import com.task1_screenscraper.facade.Facade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FacadeTests {
    Facade facade;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");
        facade = new Facade();
    }

    @AfterEach
    public void teardown(){
        facade = null;
    }

    @Test
    public void testScrapeAndUpload5Laptops(){
        // Setup

        // Exercise
        facade.scrapeAndUpload5AlertsUsingKeyword("Laptop");

        // Verify
//        facade.get

        // Teardown
    }
}
