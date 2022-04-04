package com.xische.test.accessment;

import com.xische.pages.HomePageEnglish;
import com.xische.test.TestDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Accessments extends TestDriver {
    //protected WebDriver driver;
    public HomePageEnglish homePageEnglish = new HomePageEnglish(driver);

    @Test
    public void testaccessone() throws InterruptedException {

        homePageEnglish = homePage.changeToEnglish();
        waitForPageReady(HomePageEnglish.ID);
        homePageEnglish.doSearch("Abudhabi Police");
        List<String> result1 = homePageEnglish.getSearchResult();
        System.out.println(result1.toString());
        String count1 = homePageEnglish.getSearchResultCount();
        System.out.println(count1);

        homePageEnglish.refreshPage();

        List<String> result2 = homePageEnglish.getSearchResult();
        System.out.println(result2.toString());
        String count2 = homePageEnglish.getSearchResultCount();
        System.out.println(count2);

        Assert.assertEquals(result1, result2);
        Assert.assertEquals(count1, count2);

        homePageEnglish.refreshPage();

        List<String> result3 = homePageEnglish.getSearchResult();
        System.out.println(result3.toString());
        String count3 = homePageEnglish.getSearchResultCount();
        System.out.println(count3);

        Assert.assertEquals(result1, result3);
        Assert.assertEquals(count1, count3);

        homePageEnglish.refreshPage();

        List<String> result4 = homePageEnglish.getSearchResult();
        System.out.println(result4.toString());
        String count4 = homePageEnglish.getSearchResultCount();
        System.out.println(count4);

        Assert.assertEquals(result1, result4);
        Assert.assertEquals(count1, count4);

        homePageEnglish.refreshPage();

        List<String> result5 = homePageEnglish.getSearchResult();
        System.out.println(result5.toString());
        String count5 = homePageEnglish.getSearchResultCount();
        System.out.println(count5);

        Assert.assertEquals(result1, result5);
        Assert.assertEquals(count1, count5);

        homePageEnglish.refreshPage();

        List<String> result6 = homePageEnglish.getSearchResult();
        System.out.println(result6.toString());
        String count6 = homePageEnglish.getSearchResultCount();
        System.out.println(count6);

        Assert.assertEquals(result1, result6);
        Assert.assertEquals(count1, count6);
    }

    @Test
    public void testaccesstwo() throws InterruptedException {
        homePageEnglish = homePage.changeToEnglish();
        waitForPageReady(HomePageEnglish.ID);
        homePageEnglish.goToAbuDhabiGovt();
        homePageEnglish.doSearchADP("ADP");
        WebElement adp = driver.findElement(By.xpath("//h2[@class='TPN-adge-card__Title']/a[text()]"));
        adp.click();
    }
}
