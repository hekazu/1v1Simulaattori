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
            assertTrue(smack < 7 && smack > 0);
        }
    }
    
    // Spesiaalin toteutettuani testaan sen tässä
}
