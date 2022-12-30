package jUnit_Practise;

import org.junit.Test;
import org.junit.internal.RealSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C14_Actions extends TestBase {
    /*
    Bu class tan itibaren utilities package ile
    gerekli methodlari olusturacagim
     */

    @Test
    public void test01(){
      /*
        1- "http://webdriveruniversity.com/Actions" sayfasina gidin
        2- Hover over Me First" kutusunun ustune gelin
        3- Link 1" e tiklayin
        4- Popup mesajini yazdirin
        5- Popup'i tamam diyerek kapatin
        6- “Click and hold" kutusuna basili tutun
        7-“Click and hold" kutusunda cikan yaziyi yazdirin
        8- “Double click me" butonunu cift tiklayin
      */

        //1- "http://webdriveruniversity.com/Actions" sayfasina gidin
        driver.get("http://webdriveruniversity.com/Actions");

        //2- Hover over Me First" kutusunun ustune gelin
        WebElement hoveroverMeElementi = driver.findElement(By.xpath("//button[text()='Hover Over Me First!']"));
        Actions action = new Actions(driver);
        action.moveToElement(hoveroverMeElementi).perform();
        ReusableMethods.bekle(3);

        //3- Link 1" e tiklayin
        driver.findElement(By.xpath("//a[text()='Link 1']")).click();

        //4- Popup mesajini yazdirin
        System.out.println("Popup mesaji : "+driver.switchTo().alert().getText());

        //5- Popup'i tamam diyerek kapatin
        driver.switchTo().alert().accept();

        //6- “Click and hold" kutusuna basili tutun
        WebElement clickAndHoldElementi = driver.findElement(By.id("click-box"));
        action.clickAndHold(clickAndHoldElementi).perform();
        ReusableMethods.bekle(3);

        //7-“Click and hold" kutusunda cikan yaziyi yazdirin
        System.out.println("click and hold bolme yazisi : "+clickAndHoldElementi.getText());

        //8- “Double click me" butonunu cift tiklayin
        WebElement doubleClickElementi = driver.findElement(By.id("double-click"));
        action.doubleClick(doubleClickElementi).perform();


    }


}
