package jUnit_Practise;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C17_VerifySubscriptionInHomePage extends TestBase {
    @Test
    public void Test01(){
        // Verify Subscription in home page

        //1. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        ReusableMethods.bekle(5);
        //2. Verify that home page is visible successfully
        String expectedHomePageTitle = "Automation Exercise";
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(expectedHomePageTitle,actualHomePageTitle);

        //3. Scroll down to footer
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.END);

        //4. Verify text 'SUBSCRIPTION'
        String expectedText = "SUBSCRIPTION";
        String actualText = driver.findElement(By.xpath("//*[text()='Subscription']")).getText();
        Assert.assertEquals(expectedText,actualText);

        //5. Enter email address in input and click arrow button
        Faker faker = new Faker();
        WebElement email =driver.findElement(By.xpath("//input[@type='email']"));
        email.click();
        email.sendKeys(faker.internet().emailAddress());

        ReusableMethods.bekle(3);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        ReusableMethods.bekle(1);
        //6. Verify success message 'You have been successfully subscribed!' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert-success alert']")).isDisplayed());
    }
}
