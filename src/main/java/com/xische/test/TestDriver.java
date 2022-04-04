package com.xische.test;

import com.xische.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.net.URLDecoder;
import java.util.Properties;

public class TestDriver {

    protected WebDriver driver;
    protected Properties properties = PropertyLoader.loadProperties();
    protected String browser = properties.getProperty("browser");
    protected String host = properties.getProperty("host");
    public HomePage homePage = new HomePage(driver);
    @SuppressWarnings("unused")
    private static String initFile = PropertyLoader.loadProperties().getProperty("dataBaseDir");
    @SuppressWarnings("unused")
    private static String tearDownFile = PropertyLoader.loadProperties().getProperty("dataBaseDir");

    public static long TIMESTAMP = -1;//(long) (Math.random() * 10000);
    protected boolean isStaticDriver = Boolean.parseBoolean(properties.getProperty("staticDriver"));

    @BeforeClass
    public static void setUpBaseDriverBeforeClass() {
    }

    @AfterClass
    public static void tearDownBaseDriverAfterClass() {
    }

    @BeforeTest
    public void setUpBaseDriver() {
        //Log.info("Static Driver : " + isStaticDriver + "\nSelenium Server : " + properties.getProperty("seleniumServer"));
        try {
            if (isStaticDriver) {
                if (browser.contains("firefox")) {
                    System.setProperty("webdriver.gecko.driver",properties.getProperty("firefoxDriverMac"));
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                } else if(browser.contains("chrome")) {
                    System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriver"));
                    driver = new ChromeDriver();
                    driver.manage().window().setPosition(new Point(0,0));
                    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                    Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
                    driver.manage().window().setSize(dim);
                } else if(browser.contains("safari")) {
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                } else if(browser.contains("headless")) {
                    System.setProperty("webdriver.gecko.driver",properties.getProperty("firefoxDriverMac"));
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(true);
                    driver = new FirefoxDriver(options);
                    driver.manage().window().maximize();

                }
                else {
                    driver = new InternetExplorerDriver();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String startPage = ""+host;

        driver.get(startPage);
        //waitFor(veryLongWait);

        //waitForPageReady(LoginPage.ID);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterTest
    public void tearDownBaseDriver() {
        driver.quit();
    }

    protected boolean waitForPageReady(By id) {
        for(int i = 0; i < 30; i++) {
            try {
                if(driver.findElements(id).size() > 0 && driver.findElement(id).isDisplayed()) return true;
                Thread.sleep(2000);
            } catch(Exception e) {
            }
        }
        return false;
    }
}
