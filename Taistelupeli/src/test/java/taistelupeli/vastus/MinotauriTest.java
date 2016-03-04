/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taistelupeli.vastus;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import taistelupeli.sovelluslogiikka.Toiminto;
import static taistelupeli.sovelluslogiikka.Toiminto.ERIKOISTAITO;
import static taistelupeli.sovelluslogiikka.Toiminto.HYOKKAYS;

/**
 *
 * @author henpeura
 */
public class MinotauriTest {
    
    public Minotauri etc;
    
    public MinotauriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        etc = new Minotauri();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void konstrukstoriTekeeMitaKuuluu() {
        assertEquals(etc.getKesto(), 40);
        assertEquals(etc.getArmourClass(), 12);
        assertEquals(etc.getModifier(), 4);
        assertFalse(etc.onkoKuollut());
        assertFalse(etc.getSpecialStatus());
    }
    
    @Test
    public void toStringTekeeMitaKuuluu() {
        assertEquals(etc.toString(), "Minotauri");
    }
    
    @Test
    public void vahinkoAsettuuKunnolla() {
        etc.otaVahinkoa(12);
        assertEquals(etc.getKesto(), 28);
        assertFalse(etc.onkoKuollut());
    }
    
    @Test
    public void josSeVuotaaVerta() {
        etc.otaVahinkoa(50);
        assertTrue(etc.onkoKuollut());
    }
    
    @Test
    public void hyokkaysOnRajoissa() {
        for (int i = 0; i < 75; i++) {
            int vrt = etc.hyokkaa();
            assertTrue(vrt < 17 && vrt > 4);
        }
    }
    
    @Test
    public void spesiaaliToimiiTeoriassa() {
        etc.spesialisoi();
        assertTrue(etc.getSpecialStatus());
    }
    
    @Test
    public void spesiaaliEiJaaElamaan() {
        etc.spesialisoi();
        etc.hyokkaa();
        assertFalse(etc.getSpecialStatus());
    }
    
    @Test
    public void spesiaaliHyokkaysOnRajoissa() {
        for (int i = 0; i < 75; i++) {
            etc.spesialisoi();
            int vrt = etc.hyokkaa();
            assertTrue(vrt < 33 && vrt > 9);
        }
    }
    
    @Test
    public void toimintametodiPalauttaaToimintamalliaVastaavanKaskyn() {
        for (int i = 0; i < 10; i++) {
            Toiminto vrt = etc.toimi();
            assertTrue(vrt == HYOKKAYS || vrt == ERIKOISTAITO);
        }
    }
    
    @Test
    public void spesiaaliPalauttaaOikeaaTekstia() {
        assertEquals(etc.spesialisoi(), "Hirviömäinen otus polkee sorkkajalkaansa maata vasten ja laskee päätään.\n Tämä ei vaikuta hyvältä...\n");
    }
    
    @Test
    public void postMortemPelaa() {
        assertEquals(etc.postMortem(), "Minotauri kaatuu kuolleena maahan.\n");
    }
}
