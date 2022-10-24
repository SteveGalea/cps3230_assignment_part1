package com.task1_screenscraper.tests.website;

import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MaltaParkTests {
    WebDriver driver;
    MaltaParkPageObject maltaParkPageObject;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.maltapark.com/");

        maltaParkPageObject = new MaltaParkPageObject(driver);
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

    @Test
    public void testVisitWebsite(){
        //Setup

        //Exercise
        String actualTitle = maltaParkPageObject.getTitle();

        //Verify
        Assertions.assertEquals("Home | Maltapark", actualTitle); // since we should test one particular thing, should we have multiple verifications? (obv related to same thing)

        //Teardown
    }
}
