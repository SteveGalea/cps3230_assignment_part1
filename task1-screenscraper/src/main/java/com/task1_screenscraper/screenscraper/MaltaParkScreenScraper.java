package com.task1_screenscraper.screenscraper;

import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.rest.RequestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MaltaParkScreenScraper {
    WebDriver driver;
    WebDriverWait wait;
    MaltaParkPageObject maltaParkPageObject;
    List<Product> productList;
    RequestHelper requestHelper;

    public MaltaParkScreenScraper(WebDriver driver, WebDriverWait wait, PriceConverter priceConverter, MaltaParkPageObject maltaParkPageObject, List<Product> productList) {
        this.driver = driver;
        this.maltaParkPageObject = maltaParkPageObject;
        this.productList = productList;
        this.requestHelper = new RequestHelper();

    }
//
//    public List<Product> getProductList() {
//        return productList;
//    }

    public void goToUrl(String websiteUrl) {
        driver.manage().window().maximize();
        driver.get(websiteUrl);
    }

    public void closeMessageModal(){
        WebElement closeButton = maltaParkPageObject.getCloseButton();
        closeButton.click();
    }

    public void searchProductByTerm(String term){
        WebElement searchBar = maltaParkPageObject.getSearchBar();
        WebElement searchButton = maltaParkPageObject.getSearchButton();
        searchBar.sendKeys(term);
        searchButton.submit();
    }
    public void stopScraping() {
        driver.quit();
    }

    public void scrapeFirst5Results() {
        List<String> first5ElementsLinks = maltaParkPageObject.getFirst5ItemsUrls();

        for (String productLink:
                first5ElementsLinks) {
            goToUrl(productLink);

            Product product = new Product();
            //get data
            int alertType = maltaParkPageObject.getProductAlertType();
            String heading = maltaParkPageObject.getProductHeading();
            String description = maltaParkPageObject.getProductDescription();
            String url = maltaParkPageObject.getProductUrl();
            String imageUrl = maltaParkPageObject.getProductImageUrl();
            int priceInCents = maltaParkPageObject.getProductPriceInCents();

            // setter injection
            product.setAlertType(alertType);
            product.setHeading(heading);
            product.setDescription(description);
            product.setUrl(url);
            product.setImageUrl(imageUrl);
            product.setPriceInCents(priceInCents);

            productList.add(product);
        }
    }

    public void uploadProductListToMarketAlert(){
        for (Product product:
             productList) {
            requestHelper.setJSONObject(product);
            requestHelper.makePostRequest();
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

//    public void deleteMarketAlerts(){
//        requestHelper.makeDeleteRequest();
//    }
}
