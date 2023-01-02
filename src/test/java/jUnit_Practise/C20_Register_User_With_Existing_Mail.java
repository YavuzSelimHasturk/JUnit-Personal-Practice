package jUnit_Practise;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C20_Register_User_With_Existing_Mail extends TestBase {
    @Test
    public void test01(){
        //1. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //2. Verify that home page is visible successfully
        String expectedStr= "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedStr,actualTitle);

        //3. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();

        ReusableMethods.bekle(5);
        //4. Verify 'New User Signup!' is visible
        WebElement signupElement =driver.findElement(By.xpath("//h2[text()='New User Signup!']"));
       Assert.assertTrue(signupElement.isDisplayed());

        //5. Enter name and already registered email address
        WebElement nameElement= driver.findElement(By.xpath("//input[@name='name']"));
        nameElement.sendKeys("Yavuz"+ Keys.TAB + "yavuz_selim@hotmail.com");


        //6. Click 'Signup' button
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        //7. Verify error 'Email Address already exist!' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Email Address already exist!']")).isDisplayed());

    }

}
