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

public class C09_Remove_Products {
    //  Test Case : Remove Products From Cart
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterClass
    public static void tearDown(){
        driver.close();
    }

    @Test
    public void Test01_HomePageTest(){
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //3. Verify that home page is visible successfully
        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }
    @Test
    public void Test02_AddProductTest(){
        //4. Add products to cart
        driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]")).click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();

        driver.findElement(By.xpath("(//a[@data-product-id='2'])[1]")).click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();

        driver.findElement(By.xpath("(//a[@data-product-id='3'])[1]")).click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();

        //5. Click 'Cart' button
        driver.findElement(By.xpath("//a[text()=' Cart']")).click();

        //6. Verify that cart page is displayed
        WebElement cartPage = driver.findElement(By.xpath("//li[text()='Shopping Cart']"));
        Assert.assertTrue(cartPage.isDisplayed());
    }
    @Test
    public void Test03_RemoveProductTest() throws InterruptedException {
        Thread.sleep(3000);
        //7. Click 'X' button corresponding to particular product
        driver.findElement(By.xpath("(//i[@class='fa fa-times'])[2]")).click();
        Thread.sleep(3000);
        //8. Verify that product is removed from the cart
        WebElement container = driver.findElement(By.xpath("//div[@id='cart_info']"));
        String expectedWord= "Men Tshirt";
        String cartProducts = container.getText();
        Assert.assertFalse(cartProducts.contains(expectedWord));

    }


}
