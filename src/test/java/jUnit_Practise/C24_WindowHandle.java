package jUnit_Practise;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

import java.util.Set;

public class C24_WindowHandle extends TestBase {

    @Test
    public void test01() {

        //1."http://webdriveruniversity.com/" adresine gidin
        driver.get("http://webdriveruniversity.com/");

        //2."Login Portal" a kadar asagi inin
        Actions actions = new Actions(driver);
        WebElement loginPortalElementi = driver.findElement(By.xpath("//*[text()='LOGIN PORTAL']"));
        actions.scrollToElement(loginPortalElementi);

        ReusableMethods.bekle(7);

        //3."Login Portal" a tiklayin
        String homePageWHD = driver.getWindowHandle();
        loginPortalElementi.click();

        //4.Diger window'a gecin
        String loginPortalWHD ="";
        Set<String> tumSayfalarWHD = driver.getWindowHandles();
        for (String each: tumSayfalarWHD) {
            if (!each.equals(homePageWHD)){
                loginPortalWHD=each;
            }
        }
        driver.switchTo().window(loginPortalWHD);

        //5."username" ve "password" kutularina deger yazdirin
        WebElement username= driver.findElement(By.xpath("//input[@id='text']"));

        actions = new Actions(driver);
        actions.click(username)
               .sendKeys("yvz")
               .sendKeys(Keys.TAB)
               .sendKeys("123").perform();

        ReusableMethods.bekle(5);

        //6."login" butonuna basin
        WebElement loginElementi=driver.findElement(By.xpath("//button[@type='submit']"));
        loginElementi.click();

        //7.Popup'ta cikan yazinin "validation failed" oldugunu test edin
        String expectedAlertText="validation failed";
        String actualAlertText=driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText, expectedAlertText);

        //8.Ok diyerek Popup'i kapatin
        driver.switchTo().alert().accept();

        ReusableMethods.bekle(3);

        //9.Ilk sayfaya geri donun
        driver.switchTo().window(homePageWHD);

        //10.Ilk sayfaya donuldugunu test edin
        String expectedStr="webdriveruniversity";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedStr));

        ReusableMethods.bekle(5);

    }
}