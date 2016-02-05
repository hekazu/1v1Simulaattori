/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taistelupeli.pelaaja;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henpeura
 */
public class KirvesTest {
    
    public Kirves andMuhAx;
    
    public KirvesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        andMuhAx = new Kirves();
    }

    @Test
    public void vahinkoOnMitaKuuluu() {
        for (int i = 0; i < 50; i++) {
            int vah = andMuhAx.teeVahinkoa();
            assertTrue(vah > 0);
            assertTrue(vah < 13);
        }
    }
}
