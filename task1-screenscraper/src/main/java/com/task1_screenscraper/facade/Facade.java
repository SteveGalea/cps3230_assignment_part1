package com.task1_screenscraper.facade;

import com.task1_screenscraper.screenscraper.MaltaParkScreenScraper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Facade {
    WebDriver driver;
    MaltaParkScreenScraper maltaParkScreenScraper;

    final String eCommerceWebsiteUrl = "https://www.maltapark.com/";

    public Facade(){
        driver = new ChromeDriver();
        maltaParkScreenScraper = new MaltaParkScreenScraper(driver);
    }

    public void scrapeAndUpload5AlertsUsingKeyword(String keyword){
        maltaParkScreenScraper.goToUrl(eCommerceWebsiteUrl);
        maltaParkScreenScraper.closeMessageModal();
        maltaParkScreenScraper.searchProductByTerm(keyword);
        maltaParkScreenScraper.scrapeFirst5Results();
        maltaParkScreenScraper.uploadProductListToMarketAlert();
        maltaParkScreenScraper.stopScraping();
    }
}
