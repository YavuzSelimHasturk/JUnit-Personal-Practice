package jUnit_Practise_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C03_BestBuyAssertion {
    //https://www.bestbuy.com/ adresine gidin farkli test methodlari olusturarak asagidaki testleri yapin
    // 1. Sayfa URL'inin https://www.bestbuy.com/ 'a esit oldugunu test edin
    // 2. titleTest==> Sayfa basliginin "Rest" icermedigini test edin
    // 3. logoTest==> Bestbuy logosunun goruntulendigini test edin
    // 4. FrancaisLinkTest==> Fransizca Linkin goruntulendigini test edin

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public static void tearDown(){
        driver.close();
    }
    @Test
    public void URLTest(){
        driver.get("https://www.bestbuy.com");

        String expectedUrl ="https://www.bestbuy.com/";
        String actualUrl=driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl,actualUrl);
    }
    @Test
    public void TitleTest(){
        String expectedWord="Rest";
        String actualTitle=driver.getTitle();

        Assert.assertFalse(actualTitle.contains(expectedWord));
    }
    @Test
    public void LogoTest(){
       WebElement logo= driver.findElement(By.xpath("(//img[@class='logo'])[1]"));
       Assert.assertTrue(logo.isDisplayed());
    }
    @Test
    public void FrancaisLinkTest(){
        WebElement francaisTest = driver.findElement(By.xpath("//button[text()='Français']"));
        Assert.assertTrue(francaisTest.isDisplayed());
    }

}
