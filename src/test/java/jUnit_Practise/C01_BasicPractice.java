package jUnit_Practise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_BasicPractice {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("Setup methodu calisti");
    }
    @After
    public void teardown(){
        driver.close();
        System.out.println("teardown methodu calisti");
    }
    @Test
    public void test01(){
    driver.get("https://www.amazon.com");
        System.out.println(driver.getTitle());
}
    @Test
    public void test02(){
    driver.get("https://ebay.com");
        System.out.println(driver.getTitle());
    }
    @Test
    public void test03(){
    driver.get("https://www.youtube.com");
        System.out.println(driver.getTitle());
    }
}
