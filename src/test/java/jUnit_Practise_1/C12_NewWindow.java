package jUnit_Practise_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C12_NewWindow {
    static WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @After
    public void teardown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
    @Test
    public void test() throws InterruptedException {
        driver.get("https://www.ebay.com");
        String ebayWindowHandleDegeri = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB).get("https://www.youtube.com");

        String expectedStr = "YouTube";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedStr,actualTitle);
        Thread.sleep(5000);

        driver.switchTo().window(ebayWindowHandleDegeri);
        expectedStr="ebay";
        String actualURL= driver.getCurrentUrl();
        Assert.assertTrue(actualURL.contains(expectedStr));
        Thread.sleep(5000);

    }
}
