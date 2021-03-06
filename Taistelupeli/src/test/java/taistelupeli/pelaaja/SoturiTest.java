package taistelupeli.pelaaja;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SoturiTest {
    
    public Soturi tauno;
    
    public SoturiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        tauno = new Soturi();
    }

    @Test
    public void soturinLuontiarvotOvatOikeat() {
        assertEquals(tauno.getKesto(), 32);
        assertEquals(tauno.getArmourClass(), 17);
        assertEquals(tauno.getModifier(), 3);
    }
    
    @Test
    public void peliEiOleHavittyLuotaessa() {
        assertFalse(tauno.havisitkoPelin());
    }
    
    @Test
    public void vahinkoAsettuuKestoon() {
        tauno.otaVahinkoa(16);
        assertEquals(tauno.getKesto(), 16);
    }
    
    @Test
    public void kuolemaSattuu() {
        tauno.otaVahinkoa(32);
        assertTrue(tauno.havisitkoPelin());
    }
    
    @Test
    public void overkillBestKill() {
        tauno.otaVahinkoa(Integer.MAX_VALUE);
        assertTrue(tauno.havisitkoPelin());
    }
    
    @Test
    public void acMuunteluToimiiYlospain() {
        tauno.modifyArmourClass(3);
        assertEquals(tauno.getArmourClass(), 20);
    }
    
    @Test
    public void acMuunteluToimiiAlaspain() {
        tauno.modifyArmourClass(-3);
        assertEquals(tauno.getArmourClass(), 14);
    }
    
    @Test
    public void acMuunteluNollallaEiAiheutaJatkotoimenpiteita() {
        tauno.modifyArmourClass(0);
        assertEquals(tauno.getArmourClass(), 17);
    }
    
    @Test
    public void vahingontekoToimiiAlkuaseella() {
        for (int i = 0; i < 50; i++) {
            int laiminta = tauno.hyokkaa();
            assertTrue(laiminta > 3 && laiminta < 12);
        }
    }
    
    @Test
    public void kirveeseenVaihdonJalkeenVahinkoPysyyUusissaRajoissa() {
        tauno.aseenVaihto(new Kirves());
        for (int i = 0; i < 50; i++) {
            int kirveily = tauno.hyokkaa();
            assertTrue(kirveily > 3 && kirveily < 16);
        }
    }
    
    @Test
    public void negatiivinenVahinkoEiParanna() {
        tauno.otaVahinkoa(-13);
        assertEquals(tauno.getKesto(), 32);
    }
    
    @Test
    public void healPls() {
        tauno.otaVahinkoa(12);
        tauno.parane();
        assertEquals(tauno.getKesto(), 32);
    }
    
    @Test
    public void paraneminenEiNostaKuolleista() {
        tauno.otaVahinkoa(236);
        tauno.parane();
        assertTrue(tauno.havisitkoPelin());
    }
    
    @Test
    public void spesiaaliEiKasaannuLoputtomiin() {
        tauno.spesiaali();
        tauno.spesiaali();
        tauno.spesiaali();
        assertEquals(tauno.getArmourClass(), 19);
    }
    
    @Test
    public void hyokkaysPoistaaSpesiaalinSuomanEdun() {
        tauno.spesiaali();
        tauno.hyokkaa();
        assertEquals(tauno.getArmourClass(), 17);
    }
    
    @Test
    public void modifierMuovaantuuMitenKuuluu() {
        tauno.muutaModifier(17);
        assertEquals(tauno.getModifier(), 17);
    }
    
    @Test
    public void negatiivisiaStattimodejaEiOleEikaTule() {
        tauno.muutaModifier(-7);
        assertEquals(tauno.getModifier(), 3);
    }
    
    @Test
    public void stattimuuttujaEiSaaMyoskaanOllaNolla() {
        tauno.muutaModifier(0);
        assertEquals(tauno.getModifier(), 3);
    }
    
    @Test
    public void toStringToimittaaAlkuarvoisestiOikeatArvot() {
        String vrt = "Kesto: 32\nAse: Miekka\nAC: 17\nMod: 3";
        assertEquals(tauno.toString(), vrt);
    }
    
    @Test
    public void spesiaaliPalauttaaEnsimmaisellaKaytollaOikeanMerkkijonon() {
        assertEquals(tauno.spesiaali(), "Nostat kilpesi puolustusvalmiuteen.\n");
    }
    
    @Test
    public void spesiaaliPalauttaaSeuraavillaKaytoillaOikeanMerkkijonon() {
        tauno.spesiaali();
        for (int i = 0; i < 4; i++) {
            assertEquals(tauno.spesiaali(), "Pidät puolustusvalmiutesi.\n");
        }
    }
}
