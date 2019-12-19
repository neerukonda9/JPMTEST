package com.test.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class BrowserFactory {

    private final static String INTERNET_EXPLORER = "internet explorer";
    private final static String IE = "ie";
    private final static String CHROME = "chrome";
    private final static String FIREFOX = "firefox";
    private final static String WEBDRIVER_MODE_LOCAL = "local";

    @Value("${webdriver.mode}")
    private String driverMode;
    @Value("${browser.name}")
    private String browserName;
    @Value("${browser.version}")
    private String browserVersion;
    @Value("${browser.platform}")
    private String browserPlatform;

    public BrowserDriver create() {
        WebDriver driver = getDriverObject(driverMode);
        if (driver == null) {
            throw new IllegalArgumentException("Mode: " + driverMode + " - Unsupported webdriver: " + browserName + " " +
                    browserVersion + " " + browserPlatform);
        }
        return new BrowserDriver(driver);
    }

    private WebDriver getDriverObject(String driverMode) {
        WebDriver driver = null;

        if (WEBDRIVER_MODE_LOCAL.equals(driverMode)) {
            String driversPath = System.getProperty("user.dir") + "/src/test/resources/config/drivers_exe/";
            switch (browserName) {
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", driversPath + "geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case INTERNET_EXPLORER:
                case IE:
                    System.setProperty("webdriver.ie.driver", driversPath + "IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", driversPath + "chromedriver");
                    driver = new ChromeDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid Browser Name");
            }
            driver.manage().window().maximize();
        }
        return driver;
    }
}
