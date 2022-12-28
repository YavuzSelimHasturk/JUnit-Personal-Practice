package jUnit_Practise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C08_Handle_IFrame_Test {
    //1) http://demo.guru99.com/test/guru99home/ sitesine gidiniz
    //2) sayfadaki iframe sayısını bulunuz.
    //3) ilk iframe'deki (Youtube) play butonuna tıklayınız.
    //4) ilk iframe'den çıkıp ana sayfaya dönünüz
    //5) ikinci iframe'deki (Jmeter Made Easy) linke
    //(https://www.guru99.com/live-selenium-project.html) tıklayınız

    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterClass
    public static void tearDown(){
        driver.close();
    }
    @Test
    public void Test01() throws InterruptedException {
        driver.get("http://demo.guru99.com/test/guru99home/");

        WebElement iFrameWebElementi = driver.findElement(By.xpath("//iFrame[@wmode='transparent']"));
        driver.switchTo().frame(iFrameWebElementi);
        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button ytp-large-play-button-red-bg']")).click();
        Thread.sleep(3000);

        driver.switchTo().defaultContent();
        WebElement iFrameWebElementi2= driver.findElement(By.xpath("//iFrame[@id='a077aa5e']"));
        driver.switchTo().frame(iFrameWebElementi2);
        driver.findElement(By.xpath("//a[@href='http://www.guru99.com/live-selenium-project.html']")).click();
    }
}
