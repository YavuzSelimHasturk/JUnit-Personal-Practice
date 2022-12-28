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

public class C06_Search_Product {

    //1. Navigate to url 'http://automationexercise.com'
    //2. Verify that home page is visible successfully
    //3. Click on 'Products' button
    //4. Verify user is navigated to ALL PRODUCTS page successfully
    //5. Enter product name in search input and click search button
    //6. Verify 'SEARCHED PRODUCTS' is visible
    //7. Verify all the products related to search are visible

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @AfterClass
    public static void tearDown(){
        driver.close();
    }
    @Test
    public void Test1(){
        driver.get("https://www.automationexercise.com");

    }
    @Test
    public void Test2() throws InterruptedException {
        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);

        driver.findElement(By.xpath("//a[text()=' Products']")).click();

        Thread.sleep(5000);

    }
    @Test
    public void Test3()  {
        WebElement allProductsVerify = driver.findElement(By.xpath("//div[@class='features_items']"));
        Assert.assertTrue(allProductsVerify.isDisplayed());

    }

    @Test
    public void Test4(){
        WebElement searchBox= driver.findElement(By.xpath("//input[@name='search']"));
        searchBox.sendKeys("jeans");
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='button']"));
        searchButton.click();
    }
    @Test
    public void Test5(){
      WebElement searchedProducts= driver.findElement(By.xpath("//h2[text()='Searched Products']"));
      Assert.assertTrue(searchedProducts.isDisplayed());
    }

    @Test
    public void Test6() {
        String expectedName = "Jeans";
        WebElement product1 = driver.findElement(By.xpath("(//p[text()='Soft Stretch Jeans'])[1]"));
        Assert.assertTrue(product1.getText().contains(expectedName));

        WebElement product2 = driver.findElement(By.xpath("(//p[text()='Regular Fit Straight Jeans'])[1]"));
        Assert.assertTrue(product2.getText().contains(expectedName));

        WebElement product3 = driver.findElement(By.xpath("(//p[text()='Grunt Blue Slim Fit Jeans'])[1]"));
        Assert.assertTrue(product3.getText().contains(expectedName));


    }
}
