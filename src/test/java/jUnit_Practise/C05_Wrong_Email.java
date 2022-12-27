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

public class C05_Wrong_Email {

    //1. Navigate to url 'http://automationexercise.com'
    //2. Verify that home page is visible successfully
    //3. Click on 'Signup / Login' button
    //4. Verify 'Login to your account' is visible
    //5. Enter incorrect email address and password
    //6. Click 'login' button
    //7. Verify error 'Your email or password is incorrect!' is visible

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterClass
    public static void teardown(){
        driver.close();
    }

    @Test
    public void Test1(){
        driver.get("http://automationexercise.com");
    }
    @Test
    public void Test2(){
        String expectedWord = "Automation Exercise";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(expectedWord,actualTitle);
    }
    @Test
    public void Test3(){
        driver.findElement(By.xpath("//a[@href='/login']")).click();
    }
    @Test
    public void Test4(){
       WebElement loginVisible = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
        Assert.assertTrue(loginVisible.isDisplayed());
    }
    @Test
    public void Test5(){
        WebElement email = driver.findElement(By.xpath("(//input[@type='email'])[1]"));
        email.sendKeys("gulugulu@gmail.com");
        WebElement password = driver.findElement(By.xpath("(//input[@type='password'])"));
        password.sendKeys("12345");
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
    }
    @Test
    public void Test6(){
        WebElement error = driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']"));
        Assert.assertTrue(error.isDisplayed());
    }

}



