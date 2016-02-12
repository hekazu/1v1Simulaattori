
package taistelupeli.vastus;

/**
 * Luokka edustaa yhtä vihollistyypeistä mitä pelissä voi kohdata, hiittä.
 * 
 * @author henpeura
 */
public class Hiisi extends Morko {
    public Hiisi() {
        super(12, 14, 2);
    }
    
    /**
     * Metodi kertoo kuinka hiisi tulee toimimaan.
     * 
     * @return muuttuja joka kertoo toivotun hyökkäystyypin
     */
    @Override
    public String toimi() {
        if (r.nextBoolean()) {
            return "hyokkays";
        } else {
            return "erikoistaito";
        }
    }

    /**
     * Metodi toimittaa otuksen tekemän vahingon.
     * 
     * @see taistelupeli.pelaaja.Sankari#hyokkaa() 
     * 
     * @see taistelupeli.vastus.Minotauri#hyokkaa() 
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
     */
    @Override
    public void spesialisoi() {
        System.out.println("Hiisi irvistää ilkeästi, muttei tee muuten mitään hyödyllistä.");
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
}
