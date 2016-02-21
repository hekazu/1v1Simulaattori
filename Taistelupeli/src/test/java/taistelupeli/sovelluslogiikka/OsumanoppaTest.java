/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taistelupeli.sovelluslogiikka;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henpeura
 */
public class OsumanoppaTest {
    
    private Osumanoppa d20;
    
    public OsumanoppaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        d20 = new Osumanoppa();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void osumaheitotOvatFiksuja() {
        for (int i = 0; i < 75; i++) {
            int heitto = d20.osumaHeitto();
            assertTrue(heitto > 0 && heitto < 21);
        }
    }
}
