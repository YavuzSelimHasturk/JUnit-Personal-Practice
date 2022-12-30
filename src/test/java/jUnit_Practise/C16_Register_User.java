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

import javax.swing.*;

public class C16_Register_User extends TestBase {

    @Test
    public void registerUserTest() {
        //1. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        //2. Verify that home page is visible successfully
        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        ReusableMethods.bekle(5);
        //3. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();

        //4. Verify 'New User Signup!' is visible
        WebElement newUserSignUpElementi= driver.findElement(By.xpath("//h2[text()='New User Signup!']"));

        //5. Enter name and email address
        WebElement nameArea = driver.findElement(By.xpath("//input[@name='name']"));
        Actions actions = new Actions(driver);
        Faker faker = new Faker();
        actions.click(nameArea)
                .sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().emailAddress()).perform();

        ReusableMethods.bekle(3);

        //6. Click 'Signup' button
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        ReusableMethods.bekle(3);
        //7. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        WebElement information = driver.findElement(By.xpath("(//h2[@class='title text-center'])[1]"));
        Assert.assertTrue(information.isDisplayed());

        //8. Fill details: Title, Name, Email, Password, Date of birth
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

        //9. Select checkbox 'Sign up for our newsletter!'
        actions.click(driver.findElement(By.xpath("//input[@name='newsletter']")));

        //10. Select checkbox 'Receive special offers from our partners!'
        actions.click(driver.findElement(By.xpath("//input[@name='optin']")));

        ReusableMethods.bekle(3);

        //11. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
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

        //12. Click 'Create Account button'
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();

        ReusableMethods.bekle(3);

        //13. Verify that 'ACCOUNT CREATED!' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='title text-center']")).isDisplayed());

        //14. Click 'Continue' button
        driver.findElement(By.xpath("//a[text()='Continue']")).click();

        ReusableMethods.bekle(3);

        //15. Verify that 'Logged in as username' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=' Logged in as ']")).isDisplayed());

        //16. Click 'Delete Account' button
        driver.findElement(By.xpath("//a[text()=' Delete Account']")).click();

        ReusableMethods.bekle(3);

        //17. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@data-qa='account-deleted']")).isDisplayed());
    }
}