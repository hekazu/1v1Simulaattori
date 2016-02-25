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
    
    /**
     * Konstruktori luo Sankarityypin Soturi.
     * 
     * Soturin erikoiskyky on nostaa AC:taan.
     */
    public Soturi() {
        super(32, new Miekka(), 3, 17);
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
     * @return spesiaalin käytössäolotilanteesta riippuva inforuutuun siirrettävä ilmoitus
     */
    @Override
    public String spesiaali() {
        if (super.getArmourClass() == pohjaArmourClass) {
            super.modifyArmourClass(2);
            return "Nostat kilpesi puolustusvalmiuteen.\n";
        }
        return "Pidät puolustusvalmiutesi.\n";
    }

}
