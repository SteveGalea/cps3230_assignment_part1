package com.task1_screenscraper.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MaltaParkPageObject {
    WebDriver driver;
    public MaltaParkPageObject(WebDriver driver){
        this.driver = driver;
    }
    public String getTitle(){
        return driver.getTitle();
    }
}
