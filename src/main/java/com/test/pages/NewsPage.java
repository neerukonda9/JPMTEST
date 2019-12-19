package com.test.pages;

import com.test.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsPage {
    private final By ARTICLES_SELECTOR = By.cssSelector("[data-test-id='facia-card'] a[data-link-name='article']");
    @Autowired
    private WebDriverHelper webDriverHelper;

    public void navigateToPage(String url) {
        webDriverHelper.navigateToURL(url);
    }

    public List<String> getAllArticles() {
        webDriverHelper.waitForElementToDisplayAndPresent(ARTICLES_SELECTOR, 5);
        return webDriverHelper.getElementsText(ARTICLES_SELECTOR)
                .stream()
                .map(title->title.replace(" – live",""))
                .collect(Collectors.toList());
    }
}
