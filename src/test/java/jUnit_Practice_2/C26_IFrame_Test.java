package jUnit_Practice_2;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C26_IFrame_Test extends TestBase {

    // 1. “http://webdriveruniversity.com/IFrame/index.html” sayfasina gidin
    // 2. “Our Products” butonuna basin
    // 3. “Cameras product”i tiklayin
    // 4. Popup mesajini yazdirin
    // 5. “close” butonuna basin
    // 6. "WebdriverUniversity.com (IFrame)" linkini tiklayin
    // 7. "http://webdriveruniversity.com/index.html" adresine gittigini test edin

    @Test
    public void test01(){
        // 1. “http://webdriveruniversity.com/IFrame/index.html” sayfasina gidin
        driver.get("http://webdriveruniversity.com/IFrame/index.html");
        // 2. “Our Products” butonuna basin
        WebElement iframe = driver.findElement(By.id("frame"));
        driver.switchTo().frame(iframe);

        WebElement ourProductsLink = driver.findElement(By.xpath("//*[text()='Our Products']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ourProductsLink).perform();
        ourProductsLink.click();

        ReusableMethods.bekle(3);

        // 3. “Cameras product”i tiklayin
        WebElement cameras = driver.findElement(By.xpath("//*[text()='Cameras']"));
        cameras.click();

        ReusableMethods.bekle(3);

        // 4. Alert mesajini yazdirin
        WebElement htmlAlert = driver.findElement(By.xpath("//*[@class='modal-body']"));
        System.out.println("Alert mesaji : " +htmlAlert.getText());

        // 5. “close” butonuna basin
        WebElement closeButton = driver.findElement(By.xpath("//button[text()='Close']"));
        closeButton.click();

        // 6. "WebdriverUniversity.com (IFrame)" linkini tiklayin
        driver.switchTo().parentFrame();
        WebElement link = driver.findElement(By.xpath("//a[text()='WebdriverUniversity.com (IFrame)']"));
        link.click();

        // 7. "http://webdriveruniversity.com/index.html" adresine gittigini test edin
        String expectedUrl = "webdriveruniversity";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl));

    }


}
