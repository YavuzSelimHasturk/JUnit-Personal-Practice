package jUnit_Practise;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C18_Login_User extends TestBase {
    @Test
    public void Login(){
        //*** Email = "yavuz_selim@hotmail.com"/password = "123456"

        //1. Navigate to url 'http://automationexercise.com'
            driver.get("http://automationexercise.com");

        //2. Verify that home page is visible successfully
        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        //3. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();

        //4. Verify 'Login to your account' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Login to your account']")).isDisplayed());

        //5. Enter correct email address and password
        Actions actions = new Actions(driver);
        WebElement emailAdressElementi =driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        emailAdressElementi.sendKeys("yavuz_selim@hotmail.com"+Keys.TAB+"123456");


        ReusableMethods.bekle(4);

        //6. Click 'login' button
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();

        //7. Verify that 'Logged in as username' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//i[@class='fa fa-user']")).isDisplayed());

        //8. Click 'Delete Account' button
        driver.findElement(By.xpath("//a[text()=' Delete Account']")).click();

        //9. Verify that 'ACCOUNT DELETED!' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Account Deleted!']")).isDisplayed());
    }

}
