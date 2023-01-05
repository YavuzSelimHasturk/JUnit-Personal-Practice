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
import java.util.List;
import java.util.Set;

public class C08_Handle_IFrame_Test {
    //1) http://demo.guru99.com/test/guru99home/ sitesine gidiniz
    //2) sayfadaki iframe sayısını bulunuz.
    //3) ilk iframe'deki (Youtube) play butonuna tıklayınız.
    //4) ilk iframe'den çıkıp ana sayfaya dönünüz
    //5) ikinci iframe'deki (Jmeter Made Easy) linke tiklayiniz
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
            // 1) http://demo.guru99.com/test/guru99home/ sitesine gidiniz
            driver.navigate().to("http://demo.guru99.com/test/guru99home/");
            // 2) sayfadaki iframe sayısını bulunuz.
            List<WebElement> butunFrame = driver.findElements(By.tagName("iframe"));
            System.out.println("Sayfadaki iframe sayisi : "+butunFrame.size());
            // 3) ilk iframe'deki (Youtube) play butonuna tıklayınız.
            WebElement iframeElementi = driver.findElement(By.xpath("//iframe[@wmode='transparent']"));
            driver.switchTo().frame(iframeElementi);
            WebElement youtubeElementi = driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button ytp-large-play-button-red-bg']"));
            youtubeElementi.click();

            // 4) ilk iframe'den çıkıp ana sayfaya dönünüz
            driver.switchTo().defaultContent();
        }
        @Test
        public void test02(){
            driver.navigate().to("http://demo.guru99.com/test/guru99home/");
            String ilkSayfaWHD = driver.getWindowHandle();
            // 5) ikinci iframe'deki (Jmeter Made Easy) linke tıklayın
            WebElement iframe = driver.findElement(By.id("a077aa5e"));
            driver.switchTo().frame(iframe);
            WebElement jmeterMadeEasy = driver.findElement(By.xpath("//img[@src='Jmeter720.png']"));
            jmeterMadeEasy.click();
            String ikinciSayfaWHD = "";
            Set<String> tumSayfalarWHD = driver.getWindowHandles();
            for (String eachWHD: tumSayfalarWHD) {
                if (!eachWHD.equals(ilkSayfaWHD)) {
                    ikinciSayfaWHD = eachWHD;
                }
            }
            // (https://www.guru99.com/live-selenium-project.html) test edin
            driver.switchTo().window(ikinciSayfaWHD);
            String expectedUrl = "selenium";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrl));
        }
    }
