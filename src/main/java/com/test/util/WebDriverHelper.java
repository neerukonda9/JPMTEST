package com.test.util;

import com.test.context.Context;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WebDriverHelper {
    @Autowired
    private Context context;


    public void navigateToURL(final String url) {
        context.getBrowser().get(url);
    }

    public List<WebElement> getElements(final By locator) {
        return context.getBrowser().findElements(locator);
    }


    public void setTextInTextBox(final By locator, final String text) {
        context.getBrowser().findElement(locator).clear();
        context.getBrowser().findElement(locator).sendKeys(text);
    }
    public void pressEntry(By locator) {
        context.getBrowser().findElement(locator).sendKeys(Keys.ENTER);
    }
    public List<String> getElementsText(final By locator) {
        return context.getBrowser()
                .findElements(locator)
                .stream()
                .filter(webElement -> !webElement.getText().isEmpty())
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void waitForElementToDisplayAndPresent(final By element, final int waitTimeInSeconds) {
        try {
            (new WebDriverWait(context.getBrowser(), waitTimeInSeconds))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            (new WebDriverWait(context.getBrowser(), waitTimeInSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException te) {
        }
    }

    public void screenshot() {
        byte[] screenshot = context.getBrowser().getScreenshotAs(OutputType.BYTES);
        context.getScenario().embed(screenshot, "image/png");
    }

}
