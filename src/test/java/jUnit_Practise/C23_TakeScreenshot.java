package jUnit_Practise;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C23_TakeScreenshot extends TestBase {

    @Test
    public void tumEkranScreenshot(){
        // ebay.com a gidip, Logitech klavye aratin
        // sonuclarin "Logitech" icerdigini test edip
        // tum sayfanin screenshot'ini alin

        driver.get("https://www.ebay.com");

        WebElement searchbox = driver.findElement(By.xpath("//input[@type='text']"));
        searchbox.sendKeys("Logitech keyboard"+ Keys.ENTER);

        String expectedStr = "Logitech";
        String actualResultValue= driver.findElement(By.xpath("//*[@class='srp-controls__count-heading']")).getText();
        Assert.assertTrue(actualResultValue.contains(expectedStr));

        ReusableMethods.bekle(5);
        ReusableMethods.TumSayfaScreenshot(driver);
    }

    @Test
    public void elementScreenshot(){
        // ebay.com a gidip, Logitech klavye aratin
        // sonuclarin "Logitech" icerdigini test edip
        // sonuc elementinin screenshot'ini alin

        driver.get("https://www.ebay.com");

        WebElement searchbox = driver.findElement(By.xpath("//input[@type='text']"));
        searchbox.sendKeys("Logitech keyboard"+ Keys.ENTER);

        String expectedStr = "Logitech";
        WebElement actualResultValue= driver.findElement(By.xpath("//*[@class='srp-controls__count-heading']"));
        String actualResultValueStr= actualResultValue.getText();
        Assert.assertTrue(actualResultValueStr.contains(expectedStr));

        ReusableMethods.bekle(5);
        ReusableMethods.elementScreenshotCek(actualResultValue);
    }
}
