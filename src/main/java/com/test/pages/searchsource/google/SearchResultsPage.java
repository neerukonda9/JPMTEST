package com.test.pages.searchsource.google;

import com.test.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchResultsPage {
    private final By SEARCH_RESULTS_SELECTOR = By.cssSelector(".bkWMgd .srg .g");
    @Autowired
    private WebDriverHelper webDriverHelper;

    public List<String> getSearchResultsUpdatedTime(String testUrl) {
        webDriverHelper.waitForElementToDisplayAndPresent(SEARCH_RESULTS_SELECTOR, 10);
        return getTimeList(testUrl);
    }

    private List<String> getTimeList(String testUrl) {
        List<String> searchResultsUpdatedListMap = new ArrayList<>();
        List<WebElement> elements = webDriverHelper.getElements(SEARCH_RESULTS_SELECTOR);
        elements.forEach(e -> {
            String articleUrl = e.findElement(By.cssSelector(".r a")).getAttribute("href");
            if (!articleUrl.startsWith(testUrl)) {
                try {
                    String time = e.findElement(By.cssSelector("span.f")).getText().replace(" -", "");
                    if(time.contains("mins") ||time.contains("hours")||time.contains("day ")){
                        searchResultsUpdatedListMap.add(time);
                    }else if(time.contains("7 days")){
                        searchResultsUpdatedListMap.add(time);
                    }
                } catch (NoSuchElementException exception) {
                }

            }
        });
        return searchResultsUpdatedListMap;
    }
}
