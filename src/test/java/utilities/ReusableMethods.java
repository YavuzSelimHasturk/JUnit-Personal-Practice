package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReusableMethods {
    public static void bekle(int saniye){

        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
        }

    }

    public static void TumSayfaScreenshot(WebDriver driver) {
        TakesScreenshot tss= (TakesScreenshot) driver;

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String tarih = ldt.format(dtf);

        String dosyaYolu="target\\screenshots\\tumEkranScreenshot"+tarih+".jpeg";
        File tumSayfaScreenshot=new File(dosyaYolu);

        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,tumSayfaScreenshot);
        } catch (IOException e) {

        }
    }

    public static void elementScreenshotCek(WebElement actualResultValue) {

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String tarih = ldt.format(dtf);

        String dosyaYolu = "target\\Screenshots\\elementScreenshot"+tarih+".jpeg";

        File elementScreenshot = new File(dosyaYolu);

        File geciciDosya = actualResultValue.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya,elementScreenshot);
        } catch (IOException e) {

        }
    }
}
