package com.xische.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.xpath.XPathResult;

import java.util.ArrayList;
import java.util.List;

public class HomePageEnglish {
    public static final By ID = By.xpath("//input[@name='q']");

    public WebDriver driver;

    @FindBy(how = How.XPATH, using = "//input[@name='q']")
    WebElement searchInput;
    @FindBy(how = How.XPATH, using = "//a[@target='burger-nav']")
    WebElement burger;


    public HomePageEnglish(WebDriver driver) {
        this.driver = driver;
    }

    public void doSearch(String searchText) throws InterruptedException {

        WebElement input = driver.findElement(By.xpath("//div[@class='TPN-intro__search']/div[@class='TPN-autocomplete']/form/div[@class='TPN-autocomplete__search']/input"));
        input.sendKeys(searchText);

        input.sendKeys(Keys.RETURN);
        Thread.sleep(15000);
    }

    public List<String> getSearchResult(){
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='ui-lib-category-list ui-lib-category-list-dummy']//div/a"));
        List<String> searchResult = new ArrayList<String>();
        searchResult.add(list.get(0).getText());
        searchResult.add(list.get(1).getText());
        searchResult.add(list.get(2).getText());
        searchResult.add(list.get(3).getText());
        searchResult.add(list.get(4).getText());
        return searchResult;
    }

    public String getSearchResultCount(){
        WebElement getCount = driver.findElement(By.className("totalResultsText"));
        String text = getCount.getText();
        String[] strArr = text.split(" ");
        return strArr[strArr.length-2];
    }
    public void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(15000);
    }

    public void goToAbuDhabiGovt() throws InterruptedException {
        burger.click();
        Thread.sleep(15000);
        WebElement abuDhabiGovtLink = driver.findElement(By.xpath("//div[@class='TPN-header__burger-nav open']//a[text()='Abu Dhabi Government Entities']"));
        abuDhabiGovtLink.click();
        Thread.sleep(5000);
    }

    public void doSearchADP(String searchText) throws InterruptedException {

        WebElement input = driver.findElement(By.xpath("//input[@name='textBoxSearch']"));
        input.sendKeys(searchText);

        input.sendKeys(Keys.RETURN);
        Thread.sleep(15000);
    }

}
