package com.xische.pages;

import com.xische.test.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Properties;

public class HomePage {
    public static final By ID = By.xpath("//a[@title='English']");

    public WebDriver driver;
    protected Properties properties = PropertyLoader.loadProperties();
    protected String host = properties.getProperty("host");

    @FindBy(how = How.XPATH, using = "//a[@title='English']")
    WebElement english;
    @FindBy(how = How.XPATH, using = "//a[@target='burger-nav']")
    WebElement burger;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageEnglish changeToEnglish(){
        PageFactory.initElements(driver, HomePage.class);
        english.click();
        return PageFactory.initElements(driver, HomePageEnglish.class);
    }
}
