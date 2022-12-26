package jUnit_Practise;

import org.junit.Assert;
import org.junit.Test;

public class C02_AssertionPractice {

    int P1Yas =60;
    int P2Yas =66;
    int P3Yas =70;

    @Test
    public void Test01(){
        // Emekli olma yasi 65 olduguna gore
        // Personel2 nin emekli olabilecegini test edin

        Assert.assertTrue(P2Yas>65);
    }
    @Test
    public void Test02(){
        // Emekli olma yasi 65 olduguna gore
        // Personel1 in emekli olamayacagini test edin

        Assert.assertFalse(P1Yas>65);
    }

    @Test
    public void Test03(){
        // Emekli olma yasi 65 olduguna gore
        // Personel3 un emekli olamayacagini test edin

        Assert.assertFalse(P3Yas>65);

    }
}
