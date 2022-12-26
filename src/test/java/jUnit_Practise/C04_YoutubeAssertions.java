package jUnit_Practise;

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

public class C04_YoutubeAssertions {

    //https://www.youtube.com adresine gidin
    //3) Aşağıdaki adları kullanarak 3 test metodu oluşturun ve gerekli testleri yapin
    //○titleTest  => Sayfa başlığının “YouTube” oldugunu test edin
    //○imageTest => YouTube resminin görüntülendiğini (isDisplayed()) test edin
    //○Search Box 'in erisilebilir oldugunu test edin (isEnabled())
    //○wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin

   static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.youtube.com");

    }
    @AfterClass
    public static void teardown(){
        driver.close();
    }
    @Test
    public void TitleTest(){

        String expectWord = "YouTube";
        String actualTitle = driver.getTitle();

        Assert.assertTrue(actualTitle.equals(expectWord));
    }
    @Test
    public void imageTest(){
        WebElement logoElementi = driver.findElement(By.xpath("//a[@id='logo']"));

        Assert.assertTrue(logoElementi.isDisplayed());
    }
    @Test
    public void searchboxTest(){
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='search_query']"));
        Assert.assertTrue(searchBox.isEnabled());
    }
    @Test
    public void wrongTitleTest(){
        String expectedWord="youtube";
        String actualTitle=driver.getTitle();

        Assert.assertFalse(actualTitle.contains(expectedWord));
    }

}
