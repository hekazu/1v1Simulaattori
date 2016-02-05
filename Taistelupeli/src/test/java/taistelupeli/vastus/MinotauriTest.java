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
    
    //Pitää vielä miettiä Minotaurin spesiaalin toteutusta. Toistaiseksi se sotkee hyökkäystestit...
}
