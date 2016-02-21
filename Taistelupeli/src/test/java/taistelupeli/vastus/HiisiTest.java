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
public class HiisiTest {
    
    public Hiisi klarg;
    
    public HiisiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        klarg = new Hiisi();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hiidetOvatLuodessaOikeanlaisia() {
        assertEquals(klarg.getKesto(), 12);
        assertEquals(klarg.getArmourClass(), 14);
        assertEquals(klarg.getModifier(), 2);
        assertFalse(klarg.onkoKuollut());
    }
    
    @Test
    public void hiidetVahingoittuvatAsianmukaisesti() {
        klarg.otaVahinkoa(3);
        assertEquals(klarg.getKesto(), 9);
        assertFalse(klarg.onkoKuollut());
    }
    
    @Test
    public void ifItBleeds() {
        klarg.otaVahinkoa(12);
        assertTrue(klarg.onkoKuollut());
    }
    
    @Test
    public void hyokkaysOnRajoissa() {
        for (int i = 0; i < 50; i++) {
            int smack = klarg.hyokkaa();
            assertTrue(smack < 9 && smack > 2);
        }
    }
    
    @Test
    public void toimintametodiPalauttaaToimintamalliaVastaavanKaskyn() {
        for (int i = 0; i < 10; i++) {
            Toiminto vrt = klarg.toimi();
            assertTrue(vrt == HYOKKAYS || vrt == ERIKOISTAITO);
        }
    }
    
    @Test
    public void toStringTekeeMitaKuuluu() {
        assertEquals(klarg.toString(), "Hiisi");
    }
    
    //spesiaali pitäis kai teoriassa testata. Osaispa PrintStreamien käsittelyn.
}
