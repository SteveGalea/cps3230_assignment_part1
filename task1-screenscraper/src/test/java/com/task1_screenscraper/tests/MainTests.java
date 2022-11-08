package com.task1_screenscraper.tests;

import com.task1_screenscraper.Main;
import com.task1_screenscraper.facade.Facade;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MainTests {
    @Test
    public void testMainMethodWasCalled(){
        //Setup
        Facade facade = mock(Facade.class);
        Main.setFacade(facade);

        doNothing().when(facade).scrapeAndUpload5AlertsUsingKeyword(anyString());

        //Exercise
//        main.main(null);
        Main.main(null);

        //Verify
        verify(facade, times(1)).scrapeAndUpload5AlertsUsingKeyword(anyString());

        //Teardown
    }
}
