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

public class C11_Contact_Us_Form {
    //Test Case 6: Contact Us Form
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

    //2. Verify that home page is visible successfully
        String expectedWord = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedWord);

    //3. Click on 'Contact Us' button
        driver.findElement(By.xpath("//a[text()=' Contact us']")).click();

    //4. Verify 'GET IN TOUCH' is visible
        WebElement getInTouch = driver.findElement(By.xpath("//h2[text()='Get In Touch']"));
        Assert.assertTrue(getInTouch.isDisplayed());

    //5. Enter name, email, subject and message
        WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
        name.sendKeys("Yavuz");
        WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("yvz@gmail.com");
        WebElement subject = driver.findElement(By.xpath("//input[@name='subject']"));
        subject.sendKeys("hello");
        WebElement message = driver.findElement(By.xpath("//textarea[@name='message']"));
        message.sendKeys("Hello World!");

        Thread.sleep(4000);
    //6. Upload File
        WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
        String dosyaYolu = System.getProperty("user.home")+"\\Desktop\\image.png";
        uploadFile.sendKeys(dosyaYolu);

        Thread.sleep(4000);

    //7. Click 'Submit' button
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit']"));
        submit.click();

    //8. Click OK button
        driver.switchTo().alert().accept();

    //9. Verify success message 'Success! Your details have been submitted successfully.' is visible
        WebElement successMessage = driver.findElement(By.xpath("(//div[text()='Success! Your details have been submitted successfully.'])[1]"));
        Assert.assertTrue(successMessage.isDisplayed());

    //10. Click 'Home' button and verify that landed to home page successfully
        driver.findElement(By.xpath("//span[text()=' Home']")).click();
        Assert.assertEquals(actualTitle,expectedWord);
        Thread.sleep(5000);
    }
}
