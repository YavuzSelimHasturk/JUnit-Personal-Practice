package jUnit_Practise;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.util.List;

public class C25_WebTables extends TestBase {
    @Test
    public void test01(){
        // 1. “https://demoqa.com/webtables” sayfasina gidin
        driver.get("https://demoqa.com/webtables");

        // 2. Headers da bulunan basliklari yazdirin
        WebElement headerElementi = driver.findElement(By.xpath("//div[@class='rt-thead -header']"));
        System.out.println("Headers elementindeki basliklar : "+ headerElementi.getText());

        // 3. 3.sutunun basligini yazdirin
        List<WebElement> basliklarListesi = driver.findElements(By.xpath("//div[@class='rt-th rt-resizable-header -cursor-pointer']"));
        System.out.println("3. sutunun basligi : "+basliklarListesi.get(2).getText());

        // 4. Tablodaki tum datalari yazdirin
        WebElement tabloBilgileriElementi = driver.findElement(By.xpath("//div[@class='rt-tbody']"));
        System.out.println(" Tablo body tum bilgileri : "+tabloBilgileriElementi.getText());

        // 5. Tabloda kac tane bos olmayan cell (data) oldugunu yazdirin
        List<WebElement> datalarListesi = driver.findElements(By.xpath("//div[@class='rt-td']"));

        int siraNo =1;
        for (WebElement each: datalarListesi
             ) {
        if (!(each.getText().equals("")|| each.getText().equals(""))){
            System.out.println(siraNo+". -"+each.getText());
            siraNo++;
        }
        }
        // 6. Tablodaki satir sayisini yazdirin
        List<WebElement> satirElementleriListesi = driver.findElements(By.xpath("//div[@class='rt-tr-group']"));
        System.out.println("Tablodaki satir sayisi : " + satirElementleriListesi.size());

        // 7. Tablodaki sutun sayisini yazdirin
        System.out.println("Tablodaki sutun sayisi : " + basliklarListesi.size());

        // 8. Tablodaki 3.kolonu yazdirin
        List<WebElement> ucuncuKolonListesi = driver.findElements(By.xpath("((//div[@class='rt-tr-group']))//div[1]/div[3]"));
        for (WebElement eachElement:ucuncuKolonListesi
        ) {
            if (!(eachElement.getText().equals("") || eachElement.getText().equals(" "))){
                System.out.println(eachElement.getText());
            }
        // 9. Tabloda "First Name" i Kierra olan kisinin Salary'sini yazdirin
            for (int i = 0; i <datalarListesi.size() ; i++) {
                if (datalarListesi.get(i).getText().equals("Kierra")){
                    System.out.println("Istenen kisinin maasi : " +datalarListesi.get(i+4).getText());
                }
            }


        }
    }
}
