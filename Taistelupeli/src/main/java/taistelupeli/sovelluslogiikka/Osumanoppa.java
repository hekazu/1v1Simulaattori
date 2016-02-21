
package taistelupeli.sovelluslogiikka;

import java.util.Random;

/**
 * Luokka korvaa ennen Peli-luokassa metodeina toteutetun osumatarkkuuden toteutuksen.
 * 
 * @author henpeura
 */
public class Osumanoppa {
    private Random r;

    /**
     * Konstruktori luo "nopan" ominaisuuden toteuttavan satunnaisgeneroijan.
     */
    public Osumanoppa() {
        r = new Random();
    }
    
    /**
     * Metodi heittää d20:n osana osumisen varmistamista.
     * 
     * @return nopanheiton arvo 
     */
    public int osumaHeitto() {
        return r.nextInt(20) + 1;
    }
    
    /**
     * Metodi kertoo osuiko tehty hyökkäys.
     * 
     * @param statMuuttuja Hyökkääjän osumatarkkuutta parantava muuttuja
     * 
     * @param kohdeHaarniska Kohteen Armour Class, joka tulee saavuttaa tai ylittää hyökkäyksen osumiseksi
     * 
     * @return osuiko tehty hyökkäys
     */
    public boolean osuuko(int statMuuttuja, int kohdeHaarniska) {
        return osumaHeitto() + statMuuttuja >= kohdeHaarniska;
    }
}
