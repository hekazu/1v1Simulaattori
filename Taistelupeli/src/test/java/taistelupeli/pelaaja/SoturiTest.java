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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void soturinLuontiarvotOvatOikeat() {
        assertEquals(tauno.getKesto(), 26);
        assertEquals(tauno.getArmourClass(), 17);
    }
    
    @Test
    public void peliEiOleHavittyLuotaessa() {
        assertFalse(tauno.havisitkoPelin());
    }
    
    @Test
    public void vahinkoAsettuuKestoon() {
        tauno.otaVahinkoa(13);
        assertEquals(tauno.getKesto(), 13);
    }
    
    @Test
    public void kuolemaSattuu() {
        tauno.otaVahinkoa(26);
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
    public void acMuunteluToimiiAlspain() {
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
    
// Kunhan peliin saadaan lisää toimivia aseluokkia, saavat ne ja aseenvaihto testinsä
}
