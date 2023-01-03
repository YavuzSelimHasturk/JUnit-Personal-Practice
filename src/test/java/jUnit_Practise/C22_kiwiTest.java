package jUnit_Practise;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C22_kiwiTest extends TestBase {
    String https="https://www.";
    @Test
    public void test01(){
        // https://www.kiwi.com sayfasina gidin
        driver.get(https+"kiwi.com");

        // cookies'i reddedin
        WebElement cookies = driver.findElement(By.xpath("(//div[text()='Reject all'])[2]"));
        cookies.click();

        // Sayfa basliginin "Kiwi" icerdigini test edin
        Assert.assertTrue(driver.getTitle().contains("Kiwi"));

        // Sag ust kisimdan dil ve para secenegini Turkiye ve TL olarak secin
        WebElement dilSecimi = driver.findElement(By.xpath("//button[@data-test='RegionalSettingsButton']"));
        dilSecimi.click();

        WebElement dropdown = dilSecimi.findElement(By.xpath("//select[@data-test='LanguageSelect']"));
        Select select =new Select(dropdown);
        select.selectByValue("tr");

        WebElement currencyElemeti = driver.findElement(By.xpath("//select[@data-test='CurrencySelect']"));
        Select select1= new Select(currencyElemeti);
        select1.selectByVisibleText("Turkish lira - TRY");

        WebElement saveButon = driver.findElement(By.xpath("//button[@data-test='SubmitRegionalSettingsButton']"));
        saveButon.click();

        // Sectiginiz dil ve para birimini dogrulayin
        WebElement text =driver.findElement(By.xpath("//*[text()='TRY']"));
        Assert.assertTrue(text.getText().contains("TRY"));


        // Ucus secenegi olarak tek yon secin
        WebElement tekYonMenu = driver.findElement(By.xpath("(//div[@class='ButtonPrimitiveContent__StyledButtonPrimitiveContent-sc-1r81o9a-0 ZYrQU'])[10]"));
        tekYonMenu.click();
        WebElement tekYon = driver.findElement(By.xpath("//a[@data-test='ModePopupOption-oneWay']"));
        tekYon.click();

        // Kalkis ve varis boxlarini temizleyerek kalkis ve varis ulkesini kendimiz belirleyelim
        WebElement defaultCloseCity= driver.findElement(By.xpath("//div[@data-test='PlacePickerInputPlace-close']"));
        defaultCloseCity.click();

        WebElement kalkisTextBox = driver.findElement(By.xpath("(//input[@data-test='SearchField-input'])[1]"));
        kalkisTextBox.sendKeys("Istanbul");
        driver.findElement(By.xpath("//*[text()='İstanbul, Türkiye']")).click();

        WebElement varisTextBox = driver.findElement(By.xpath("(//input[@data-test='SearchField-input'])[2]"));
        varisTextBox.sendKeys("Dusseldorf");
        driver.findElement(By.xpath("//*[text()='Düsseldorf, Almanya']")).click();

        // Gidis tarihi kismina erisim saglayarak gidecegimiz gunu secelim ve bookingi iptal edelim
        driver.findElement(By.xpath("//input[@data-test='SearchFieldDateInput']")).click();
        ReusableMethods.bekle(5);

        driver.findElement(By.xpath("//div[@data-value='2023-02-14']")).click();
        driver.findElement(By.xpath("//button[@data-test='SearchFormDoneButton']")).click();

        driver.findElement(By.xpath("//*[text()='Booking.com ile konaklama arayın']")).click();
        driver.findElement(By.xpath("//a[@data-test='LandingSearchButton']")).click();

        // Sadece aktarmasiz ucuslar olarak filtreleme yapalim ve en ucuz secenegine tiklayalim
        driver.findElement(By.xpath("//*[text()='Aktarmasız (direkt)']")).click();
        driver.findElement(By.xpath("//*[text()='En ucuz']")).click();
        ReusableMethods.bekle(3);

        // Filtreleme yaptigimiz ve en ucuz ucusun fiyatlarini getirerek 5000 TL den kucuk oldugunu dogrulayalim
        WebElement ucusFiyati = driver.findElement(By.xpath("(//*[@class=' length-8'])[4]"));
        String fiyat = ucusFiyati.getText();
        fiyat=fiyat.replaceAll("\\.","").replaceAll(" TL","");
        int fiyatSon =Integer.parseInt(fiyat);
        Assert.assertTrue(fiyatSon<5000);
    }

}
