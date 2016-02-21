
package taistelupeli.vastus;

import taistelupeli.sovelluslogiikka.Toiminto;
import static taistelupeli.sovelluslogiikka.Toiminto.ERIKOISTAITO;
import static taistelupeli.sovelluslogiikka.Toiminto.HYOKKAYS;

/**
 * Luokka edustaa yhtä vihollistyypeistä mitä pelissä voi kohdata, minotauria.
 * 
 * @author henpeura
 */
public class Minotauri extends Morko {
    private boolean ryntays;

    /**
     * Konstruktori luo olion luokasta Minotauri.
     * 
     * Erikoistoiminto ei ole päällä aloittaessa.
     */
    public Minotauri() {
        super(40, 12, 4);
        this.ryntays = false;
    }
    
    /**
     * Metodi palauttaa tämän vuoron toimintatyypin enummuodossa.
     * 
     * @return tällä vuorolla käytettävä hyökkäystyyppi
     */
    @Override
    public Toiminto toimi() {
        if (!ryntays) {
            if (r.nextDouble() > 0.3) {
                return HYOKKAYS;
            }
            return ERIKOISTAITO;
        }
        return HYOKKAYS;
    }

    /**
     * Metodi palauttaa hyökkäyskohtaisen vahinkomäärän.
     * 
     * Erikoistaidon ollessa voimassa hyökkäyksen vahinko on huomattavasti suurempi.
     * 
     * @see taistelupeli.pelaaja.Sankari#hyokkaa() 
     * 
     * @see taistelupeli.vastus.Hiisi#hyokkaa() 
     * 
     * @see taistelupeli.vastus.Luurankokuningas#hyokkaa() 
     * 
     * @return hyökkäyskohtainen vahinkomäärä
     */
    @Override
    public int hyokkaa() {
        if (!ryntays) {
            return r.nextInt(12) + 1 + super.getModifier();
        }
        ryntays = false;
        return r.nextInt(12) + r.nextInt(12) + 2 + (super.getModifier() * 2);
    }

    /**
     * Metodi asettaa Minotaurin erikoiskyvyn voimaan, vahvistaen seuravaa normaalihyökkäystä.
     */
    @Override
    public void spesialisoi() {
        System.out.println("Hirviömäinen otus polkee sorkkajalkaansa maata vasten ja "
                         + "laskee päätään. Tämä ei vaikuta hyvältä...");
        ryntays = true;
    }
    
    /**
     * Metodi palauttaa luokan tyypin mukaisen merkkijonoesityksen.
     * 
     * @return String "Minotauri"
     */
    @Override
    public String toString() {
        return "Minotauri";
    }
    
    /**
     * Testitarkoituksia varten luotu metodi.
     * 
     * Metodilla ei ole käyttötarkoitusta muualla ohjelmassa.
     * 
     * @return onko minotaurin seuraava hyökkäys ryntäys
     */
    public boolean getSpecialStatus() {
        return ryntays;
    }
}
