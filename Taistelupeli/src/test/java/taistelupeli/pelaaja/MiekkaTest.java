package taistelupeli.pelaaja;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MiekkaTest {
    
    public Miekka raimo;
    
    public MiekkaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        raimo = new Miekka();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void miekanVahinkoOnOikeissaRajoissa() {
        for (int i = 0; i < 50; i++) {
            int osuma = raimo.teeVahinkoa();
            assertTrue(osuma > 0 && osuma < 9);
        }
    }
    
    @Test
    public void toStringOnToimiva() {
        assertEquals(raimo.toString(), "Miekka");
    }
}
