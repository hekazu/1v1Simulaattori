package taistelupeli.pelaaja;

/**
 * Luokka on yksi sankarin mahdollisista luokista, soturi.
 * 
 * @see taistelupeli.pelaaja.Sankari
 * 
 * @author henpeura
 */

public class Soturi extends Sankari {
    private final int pohjaArmourClass;
    // olio luodaan placeholder stateilla, tarkasta manuaaleista joku kerta osuvammat
    public Soturi() {
        super(26, new Miekka(), 3, 17);
        this.pohjaArmourClass = 17;
    }
    
    /**
     * Metodi toimittaa hyökkäysvahingon yläluokan avulla tarkistettuaan ettei
     * erikoistoiminto ole jäänyt elämään taustalle.
     * 
     * @see taistelupeli.pelaaja.Sankari#hyokkaa() 
     * 
     * @return kutsuu yläluokan hyökkäysmetodia
     */
    
    @Override
    public int hyokkaa() {
        if (super.getArmourClass() != pohjaArmourClass) {
            super.modifyArmourClass(pohjaArmourClass - super.getArmourClass());
        }
        return super.hyokkaa();
    }
    /**
     * Metodi kasvattaa soturin haarniskointiarvoa seuraavaan hyökkäykseen saakka.
     */
    @Override
    public void spesiaali() {
        if (super.getArmourClass() == pohjaArmourClass) {
            super.modifyArmourClass(2);
        }
    }

}
