package jUnit_Practise;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.TestBase;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;

public class C15_DropdownMenu extends TestBase {
    @Test
    public void dropdownMenuTest(){

        //1- amazon'a gidin
        driver.get("https://www.amazon.com");

        //2- Arama kutusunun solundaki dropdown menuyu handle edip listesini ekrana yazdırın
        WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select = new Select(dropdownMenu);
        System.out.println("Dropdown Menu Elemanlari : ");
        List<WebElement> dropdownMenuList = select.getOptions();
        for (WebElement each : dropdownMenuList
             ) {
            System.out.println( each.getText());

        }

        //3- dropdown menude 28 eleman olduğunu doğrulayın
       int actualMenuElemanSayisi = dropdownMenuList.size();
       int expectedMenuElemanSayisi = 28;
       Assert.assertEquals(expectedMenuElemanSayisi,actualMenuElemanSayisi);
    }
}
