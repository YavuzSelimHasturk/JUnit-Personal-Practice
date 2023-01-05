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

public class C10_ViewCart_Brand_Products {
    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterClass
    public static void teardown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
    @Test
    public void Test01() throws InterruptedException {
        //1. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //2. Click on 'Products' button
        driver.findElement(By.xpath("//a[text()=' Products']")).click();

        //3. Verify that Brands are visible on left side bar
        WebElement brandsElement = driver.findElement(By.xpath("//div[@class='brands_products']"));
        Assert.assertTrue(brandsElement.isDisplayed());
        Thread.sleep(5000);
    }
    @Test
    public void Test02(){
        //4. Click on any brand name
        driver.findElement(By.xpath("//a[text()='Madame']")).click();

        //5. Verify that user is navigated to brand page and brand products are displayed
        WebElement brandProducts = driver.findElement(By.xpath("//div[@class='features_items']"));
        Assert.assertTrue(brandProducts.isDisplayed());

        String expectedWord = "Madame Products";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedWord));
    }
    @Test
    public void Test03(){
        //6. On left side bar, click on any other brand link
        driver.findElement(By.xpath("//a[text()='H&M']")).click();
        //7. Verify that user is navigated to that brand page and can see products
        String expectedWord = "H&M Products";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedWord));

        WebElement brandProducts = driver.findElement(By.xpath("//div[@class='features_items']"));
        Assert.assertTrue(brandProducts.isDisplayed());
    }

}
