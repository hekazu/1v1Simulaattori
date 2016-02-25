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
import taistelupeli.pelaaja.Soturi;
import static taistelupeli.sovelluslogiikka.Toiminto.ERIKOISTAITO;
import static taistelupeli.sovelluslogiikka.Toiminto.HYOKKAYS;

/**
 *
 * @author henpeura
 */
public class LuurankokuningasTest {
    
    Luurankokuningas leoric;
    
    public LuurankokuningasTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        leoric = new Luurankokuningas(new Soturi());
    }

    @Test
    public void luurankokuninkaanMaallinenTomumajaOnOikeanVahvuinen() {
        assertEquals(leoric.getKesto(), 45);
        assertEquals(leoric.getArmourClass(), 15);
        assertEquals(leoric.getModifier(), 4);
        assertEquals(leoric.getUltimaLaskuri(), -1);
    }
    
    @Test
    public void toimintaOnAinaHyokkaysKunKestoOnYli20() {
        for(int i = 0; i < 50; i++) {
            assertTrue(leoric.toimi() == HYOKKAYS);
        }
    }
    
    @Test
    public void kestonPudottuaTarpeeksiAlasOnSpesiaaliUltima() {
        leoric.otaVahinkoa(25);
        assertTrue(leoric.toimi() == ERIKOISTAITO);
    }
    
    @Test
    public void ultimaToimiiMyosAlleRajatapauksen() {
        leoric.otaVahinkoa(30);
        assertTrue(leoric.toimi() == ERIKOISTAITO);
    }
    
    @Test
    public void pelkkaVahingonOttaminenEiKuitenkaanLaukaiseUltimaaEtuajassa() {
        leoric.otaVahinkoa(2);
        assertTrue(leoric.toimi() == HYOKKAYS);
    }
    
    @Test
    public void ultimaTappaaPikkuSankarinKuoliaaksi() {
        for (int i = 0; i < 5; i++) {
            leoric.spesialisoi();
        }
        assertTrue(leoric.getKohde().havisitkoPelin());
    }
    
    @Test
    public void spesiaaliSinallaanEiTapaPikkuSankaria() {
        leoric.spesialisoi();
        assertFalse(leoric.getKohde().havisitkoPelin());
    }
    
    @Test
    public void toStringToimii() {
        assertEquals(leoric.toString(), "Luurankokuningas");
    }
    
    @Test
    public void hyokkaysVahinkoOnSiellaMissaSenKuuluukin() {
        for (int i = 0; i < 75; i++) {
            int vrt = leoric.hyokkaa();
            assertTrue(vrt >= 6 && vrt <= 16);
        }
    }
    
    @Test
    public void spesiaalinReturnitOvatOikein() {
        assertEquals(leoric.spesialisoi(), "Tämä riittää! On aika lopettaa taistelumme!\n"
                        + leoric + "alkaa manata jotain suurta...\n");
        assertEquals(leoric.spesialisoi(), "Tuhosi koittaa pian! " + leoric + " julistaa.\n");
        assertEquals(leoric.spesialisoi(), leoric + " naurahtaa: \"Nyeh-heh-heh!\"\n");
        assertEquals(leoric.spesialisoi(), leoric + " on melkein valmis loitsunsa kanssa!\nHänen on kaaduttava nyt!\n");
        assertEquals(leoric.spesialisoi(), "Loppu on tullut. " + leoric + " laukaisee loitsunsa!\n");
    }
}
