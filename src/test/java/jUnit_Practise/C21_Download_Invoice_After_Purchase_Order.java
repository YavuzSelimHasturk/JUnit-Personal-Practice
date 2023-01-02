package jUnit_Practise;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ReusableMethods;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class C21_Download_Invoice_After_Purchase_Order extends TestBase {
    @Test
    public void test01() {
        //1. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //2. Verify that home page is visible successfully
        String expectedStr = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedStr,actualTitle);

        //3. Add products to cart
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]")).click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[17]")).click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
        driver.findElement(By.xpath("(//a[text()='Add to cart'])[9]")).click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();

        ReusableMethods.bekle(4);

        //4. Click 'Cart' button
        driver.findElement(By.xpath("//a[text()=' Cart']")).click();

        //5. Verify that cart page is displayed
        expectedStr= "Checkout";
        actualTitle=driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedStr));

        //6. Click Proceed To Checkout
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

        //7. Click 'Register / Login' button
        driver.findElement(By.xpath("//*[text()='Register / Login']")).click();
        ReusableMethods.bekle(3);

        //8. Fill all details in Signup and create account
        WebElement nameArea = driver.findElement(By.xpath("//input[@name='name']"));
        Actions actions = new Actions(driver);
        Faker faker = new Faker();
        actions.click(nameArea)
                .sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().emailAddress()).perform();

        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        ReusableMethods.bekle(3);
        WebElement gender = driver.findElement(By.xpath("//input[@id='id_gender1']"));
        actions.click(gender)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().name())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                .sendKeys(Keys.TAB)
                .sendKeys("10")
                .sendKeys(Keys.TAB)
                .sendKeys("April")
                .sendKeys(Keys.TAB)
                .sendKeys("1991")
                .sendKeys(Keys.TAB).perform();

        ReusableMethods.bekle(3);

        actions.click(driver.findElement(By.xpath("//input[@name='newsletter']")));
        actions.click(driver.findElement(By.xpath("//input[@name='optin']")));

        ReusableMethods.bekle(3);

        actions.click(driver.findElement(By.xpath("//input[@id='first_name']")))
                .sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.company().name())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().fullAddress())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys("canada")
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().state())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().city())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().zipCode())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.phoneNumber().cellPhone()).perform();

        ReusableMethods.bekle(3);

        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();

        //9. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='title text-center']")).isDisplayed());
        driver.findElement(By.xpath("//a[text()='Continue']")).click();

        ReusableMethods.bekle(4);
        //10.Click 'Cart' button
        driver.findElement(By.xpath("//a[text()=' Cart']")).click();

        //11. Click 'Proceed To Checkout' button
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

        //12. Verify Address Details and Review Your Order
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Address Details']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Review Your Order']")).isDisplayed());

        //13. Enter description in comment text area and click 'Place Order'
        actions.click(driver.findElement(By.xpath("//*[@name='message']")))
               .sendKeys("Thanks for your effort")
               .click(driver.findElement(By.xpath("//*[text()='Place Order']"))).perform();

        //14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        actions.click(driver.findElement(By.xpath("//*[@name='name_on_card']")))
               .sendKeys("Yavuz")
               .sendKeys(Keys.TAB)
               .sendKeys("1234567890")
               .sendKeys(Keys.TAB)
                .sendKeys("999")
                .sendKeys(Keys.TAB)
                .sendKeys("01")
                .sendKeys(Keys.TAB)
                .sendKeys("2025").perform();

        //15. Click 'Pay and Confirm Order' button
         actions.click(driver.findElement(By.xpath("//button[@id='submit']"))).perform();
        //16. Verify success message 'Congratulations! Your order has been confirmed!'
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Congratulations! Your order has been confirmed!']")).isDisplayed());

        //17. Click 'Download Invoice' button and verify invoice is downloaded successfully.
        driver.findElement(By.xpath("//a[text()='Download Invoice']")).click();

        ReusableMethods.bekle(5);
        String dosyaYolu = System.getProperty("user.home")+"\\Downloads\\invoice.txt";
        Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));

        //18. Click 'Continue' button
        driver.findElement(By.xpath("//a[text()='Continue']")).click();

        //19. Click 'Delete Account' button
        driver.findElement(By.xpath("//a[text()=' Delete Account']")).click();
        //20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@data-qa='account-deleted']")).isDisplayed());
        ReusableMethods.bekle(5);
    }
}
