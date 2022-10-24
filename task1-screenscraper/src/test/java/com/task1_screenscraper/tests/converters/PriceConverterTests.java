package com.task1_screenscraper.tests.converters;

import com.task1_screenscraper.converters.PriceConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceConverterTests {
    PriceConverter priceConverter;

    @BeforeEach
    public void setup(){
        priceConverter = new PriceConverter();
    }

    @AfterEach
    public void teardown(){
        priceConverter = null;
    }

    @Test
    public void testConvertEmpty(){
        //Setup
        String price = "";
        //Exercise
        int actual = priceConverter.textToCents(price);
        //Verify
        Assertions.assertEquals(-1, actual);
        //Teardown
    }

    @Test
    public void testConvertThousandSeparator(){
        //Setup
        String price = ",";
        //Exercise
        int actual = priceConverter.textToCents(price);
        //Verify
        Assertions.assertEquals(-1, actual);
        //Teardown
    }

    @Test
    public void testConvert1000EuroIntoCents(){
        //Setup
        String price = "1000";
        //Exercise
        int actual = priceConverter.textToCents(price);
        //Verify
        Assertions.assertEquals(100000, actual);
        //Teardown
    }

    @Test
    public void testConvert1000EuroIntoCentsWithThousandsSeparator(){
        //Setup
        String price = "1,000";
        //Exercise
        int actual = priceConverter.textToCents(price);
        //Verify
        Assertions.assertEquals(100000, actual);
        //Teardown
    }

    @Test
    public void testConvert10Euro50Cents(){
        //Setup
        String price = "10.50";

        //Exercise
        int actual = priceConverter.textToCents(price);

        //Verify
        Assertions.assertEquals(1050, actual);

        //Teardown
    }

    @Test
    public void testConvert1000Euro50CentsWithThousandSeparator(){
        //Setup
        String price = "1,000.50";

        //Exercise
        int actual = priceConverter.textToCents(price);

        //Verify
        Assertions.assertEquals(100050, actual);

        //Teardown
    }

    @Test
    public void testConvert1000Euro50CentsWithSpaceCharacter(){
        //Setup
        String price = " 1,000.50";

        //Exercise
        int actual = priceConverter.textToCents(price);

        //Verify
        Assertions.assertEquals(100050, actual);

        //Teardown
    }

    @Test
    public void testConvert1000Euro50CentsWithEuroSymbol(){
        //Setup
        String price = "€ 1,000.50";

        //Exercise
        int actual = priceConverter.textToCents(price);

        //Verify
        Assertions.assertEquals(100050, actual);

        //Teardown
    }

    @Test
    public void testConvert1000Euro50CentsWithLineBreak(){
        //Setup
        String price = "\n€ 1,000.50";

        //Exercise
        int actual = priceConverter.textToCents(price);

        //Verify
        Assertions.assertEquals(100050, actual);

        //Teardown
    }
    //TODO: ASK ABOUT THIS

//    @Test
//    public void testHandlingTwoDecimalPointsInNumber(){
//        //Setup
//        String price = "..";
//
//        //Exercise
//        int actual = priceConverter.textToCents(price);
//
//        //Verify
//        Assertions.assertEquals(-1, actual);
//
//        //Teardown
//    }
}
