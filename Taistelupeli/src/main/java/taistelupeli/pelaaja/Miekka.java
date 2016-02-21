package taistelupeli.pelaaja;

import java.util.Random;

/**
 * Luokka toimii peaajan hahmon aseena toimittamalla satunnaisgeneroitua
 * vahinkoa.
 * 
 * @author henpeura
 * 
 */

public class Miekka implements Ase {

    private Random r;

    /**
     * Konstrukstori luo olion ja siihen kuuluvan satunnaisgeneroijan.
     */
    public Miekka() {
        r = new Random();
    }
    
    /**
     * Metodi palauttaa hyökkäyksen aseeseen pohjautuvan vahinkoarvon.
     * 
     * @see taistelupeli.pelaaja.Kirves#teeVahinkoa() 
     * 
     * @return aseen osuus kyseisen hyökkäyksen vahingosta 
     */

    @Override
    public int teeVahinkoa() {
        return r.nextInt(8) + 1;
    }

}
