package com.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NavigationHelper {
    @Autowired
    private WebDriverHelper webDriverHelper;

    public void navigateToPage(String page) {
        webDriverHelper.navigateToURL(page);
    }
}
