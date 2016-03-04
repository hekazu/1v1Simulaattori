
package taistelupeli.vastus;

import taistelupeli.sovelluslogiikka.Toiminto;
import static taistelupeli.sovelluslogiikka.Toiminto.ERIKOISTAITO;
import static taistelupeli.sovelluslogiikka.Toiminto.HYOKKAYS;

/**
 * Luokka edustaa yhtä vihollistyypeistä mitä pelissä voi kohdata, hiittä.
 * 
 * @author henpeura
 */
public class Hiisi extends Morko {
    /**
     * Konstruktori luo Mörkötyypin Hiisi edustajan.
     */
    public Hiisi() {
        super(12, 14, 2);
    }
    
    /**
     * Metodi kertoo kuinka hiisi tulee toimimaan.
     * 
     * @return enum joka kertoo toivotun hyökkäystyypin
     */
    @Override
    public Toiminto toimi() {
        if (r.nextBoolean()) {
            return HYOKKAYS;
        } else {
            return ERIKOISTAITO;
        }
    }

    /**
     * Metodi toimittaa otuksen tekemän vahingon.
     * 
     * @see taistelupeli.pelaaja.Sankari#hyokkaa() 
     * 
     * @see taistelupeli.vastus.Minotauri#hyokkaa()
     * 
     * @see taistelupeli.vastus.Luurankokuningas#hyokkaa() 
     * 
     * @return hyökkäyskohtainen vahinko
     */
    @Override
    public int hyokkaa() {
        return r.nextInt(6) + 1 + getModifier();
    }

    /**
     * Metodi suorittaa Hiiden erikoistaidon.
     * 
     * Kyllä, se on juuri niin hyödytön kuin siinä lukee. En suosittele käyttämään.
     * @return hiiden erikoistaito koonaisuudessaan
     */
    @Override
    public String spesialisoi() {
        return "Hiisi irvistää ilkeästi, muttei tee muuten mitään hyödyllistä.\n";
    }
    
    /**
     * Metodi palauttaa Hiiden tyypin merkkijonoesityksenä.
     * 
     * @return String "hiisi"
     */
    @Override
    public String toString() {
        return "Hiisi";
    }
    
    /**
     * Metodi palauttaa mörön kuolinkorinat ja niihin liittyvä tarinan eteneminen.
     * @return Hiiden kuolinkorinat ja niihin liittyvä tarinan eteneminen
     */
    @Override
    public String postMortem() {
        return super.postMortem()
                + " Ystäväsi nyökkäilevät. Ehkä sinusta onkin tähän.\n"
                + " Näin ollen aloitat itsevarmana matkasi vuorille.\n"
                + " Muutaman päivän jälkeen löydät pahaenteisen luolan...\n";
    }
}
