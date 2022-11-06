package com.task1_screenscraper.facade;

import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.screenscraper.MaltaParkScreenScraper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Facade {
    WebDriver driver;
    MaltaParkScreenScraper maltaParkScreenScraper;

    final String eCommerceWebsiteUrl = "https://www.maltapark.com/";

    public Facade(){
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PriceConverter priceConverter = new PriceConverter();
        maltaParkScreenScraper = new MaltaParkScreenScraper(driver, wait, priceConverter);
    }

    public void scrapeAndUpload5AlertsUsingKeyword(String keyword){
        maltaParkScreenScraper.goToUrl(eCommerceWebsiteUrl);
        maltaParkScreenScraper.closeMessageModal();
        maltaParkScreenScraper.searchProductByTerm(keyword);
        maltaParkScreenScraper.scrapeFirst5Results();
        maltaParkScreenScraper.uploadProductListToMarketAlert();
        maltaParkScreenScraper.stopScraping();
    }

    public void verify5AlertsWereUploaded() {

    }
}
