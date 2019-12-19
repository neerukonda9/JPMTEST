package com.test.pages.searchsource.google;

import com.test.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoogleHomePage {
    private final By SEARCH_TEXT_BOX_SELECTOR = By.cssSelector("[name='q']");
    @Autowired
    public WebDriverHelper webDriverHelper;

    public void searchForAnArticleTitle(String articleTitle) {
        webDriverHelper.waitForElementToDisplayAndPresent(SEARCH_TEXT_BOX_SELECTOR, 10);
        webDriverHelper.setTextInTextBox(SEARCH_TEXT_BOX_SELECTOR, articleTitle);
        webDriverHelper.pressEntry(SEARCH_TEXT_BOX_SELECTOR);
    }
}
