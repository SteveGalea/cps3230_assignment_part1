package com.task1_screenscraper.pageobjects;

import com.task1_screenscraper.converters.PriceConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class MarketAlertUMPageObject extends PageObject {

    public MarketAlertUMPageObject(WebDriver driver){
        super(driver);
    }

}
