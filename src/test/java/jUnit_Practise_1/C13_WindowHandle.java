package jUnit_Practise_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class C13_WindowHandle {
    /*
    ● https://the-internet.herokuapp.com/windows adresine gidin.
    ● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
    ● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
    ● Click Here butonuna basın.
    ● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
    ● Sayfadaki textin “New Window” olduğunu doğrulayın.
    ● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
     */
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
    @Test
    public void Test01() throws InterruptedException {
        //    ● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
        String ilkSayfaWindowHandleDegeri = driver.getWindowHandle();

        //    ● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        String expectedSayfaText = "Opening a new window";
        String actualSayfaText= driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(expectedSayfaText,actualSayfaText);

        //    ● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String expectedSayfaTitle = "The Internet";
        String actualSayfaTitle = driver.getTitle();
        Assert.assertEquals(expectedSayfaTitle,actualSayfaTitle);
        Thread.sleep(5000);

        //    ● Click Here butonuna basın.
        driver.findElement(By.xpath("//a[text()='Click Here']")).click();

        //    ● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        Set<String> tumWindowHandleDegerleri = driver.getWindowHandles();
        String ikinciSayfaWindowHandleDegeri = "";
        for (String each:tumWindowHandleDegerleri) {
            if (!each.equals(ilkSayfaWindowHandleDegeri)){
                ikinciSayfaWindowHandleDegeri=each;
            }
        }
        driver.switchTo().window(ikinciSayfaWindowHandleDegeri);

        String expectedNewTitle= "New Window";
        String actualNewTitle= driver.getTitle();

        Assert.assertEquals(expectedNewTitle,actualNewTitle);

        //    ● Sayfadaki textin “New Window” olduğunu doğrulayın.
        String actualText= driver.findElement(By.tagName("h3")).getText();
        String expectedText= "New Window";
        Assert.assertEquals(actualText,expectedText);
        Thread.sleep(5000);

        //    ● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(ilkSayfaWindowHandleDegeri);
        Assert.assertEquals(expectedSayfaTitle,actualSayfaTitle);
        Thread.sleep(5000);
    }

}
