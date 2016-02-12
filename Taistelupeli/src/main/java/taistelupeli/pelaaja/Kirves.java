
package taistelupeli.pelaaja;

import java.util.Random;

/**
 * Luokka toimii pelaajan hahmon aseena toimittamalla satunnaisgeneroitua
 * vahinkoa.
 * 
 * @author henpeura
 * 
 */
public class Kirves implements Ase {
    private Random r;
    
    public Kirves() {
        r = new Random();
    }
    
    /**
     * Metodi palauttaa hyökkäyksen aseeseen pohjautuvan vahinkoarvon.
     * 
     * @see taistelupeli.pelaaja.Miekka#teeVahinkoa() 
     * 
     * @return aseen osuus kyseisen hyökkäyksen vahingosta
     */
    
    @Override
    public int teeVahinkoa() {
        return r.nextInt(12) + 1;
    }
}
