package taistelupeli.pelaaja;

/**
 * Luokka toimii pohjana kaikille pelaajan kontrolloimille sankareille.
 * 
 * @author henpeura
 */

public abstract class Sankari {
    private int kesto;
    private final int pohjaKesto;
    private Ase ase;
    private int statMod;
    private int armourClass;
    
    /**
     * Konstruktori luo pelattavan sankarin.
     * 
     * @param kesto kuinka paljon vahinkoa sankari kestää
     * @param ase millä Ase-interfacen toteuttavalla aseella sankari taistelee
     * @param mod sankarin "heittoihin" vaikuttava muuttuja
     * @param ac sankarin väistely- ja puolustautumiskykyä kuvaava muuttuja
     */
    public Sankari(int kesto, Ase ase, int mod, int ac) {
        this.kesto = kesto;
        this.pohjaKesto = kesto;
        this.ase = ase;
        this.statMod = mod;
        this.armourClass = ac;
    }
    
    public int getKesto() {
        return this.kesto;
    }
    
    /**
     * Metodi palauttaa sankarin hyökkäyksen vahingon kokonaisuudessaan.
     * 
     * @see taistelupeli.pelaaja.Miekka#teeVahinkoa() 
     * 
     * @see taistelupeli.pelaaja.Kirves#teeVahinkoa() 
     * 
     * @return aseen tuottama vahinko johon lisätään sankarin perusvoiman tuoma
     * lisävahinko
     */
    
    public int hyokkaa() {
        return ase.teeVahinkoa() + statMod;
    }
    
    public int getModifier() {
        return this.statMod;
    }
    
    /**
     * Metodi muuttaa sankarin perusominaisuuksien myöntämää voimanlisäystä.
     * 
     * @param uusiMod haluttu voimanlisäyksen korvaava uusi arvo 
     */
    
    public void muutaModifier(int uusiMod) {
        if (uusiMod > 0) {
            this.statMod = uusiMod;
        }
    }
    
    /**
     * Metodilla vaihdetaan sankarin kätössä oleva ase.
     * 
     * @param uusiAse sankari käyttöön tuleva uusi Ase-olio
     */
    
    public void aseenVaihto(Ase uusiAse) {
        this.ase = uusiAse;
    }
    
    /**
     * Metodi vähentää sankarin kestoa halutulla määrällä.
     * 
     * @param damaskuukkeli vahinko, joka vähennetään sankarin kestosta
     */
    
    public void otaVahinkoa(int damaskuukkeli) {
        if (damaskuukkeli > 0) {
            this.kesto -= damaskuukkeli;
        }
    }
    
    /**
     * Metodi selvittää onko pelin häviön aiheuttava sankarin kuolema tapahtunut.
     * 
     * @return kertoo onko sankari kuollut
     */
    public boolean havisitkoPelin() {
        if (kesto > 0) {
            return false;
        }
        return true;
    }
    
    public int getArmourClass() {
        return this.armourClass;
    }
    
    /**
     * Metodin muuntaa sankarin vahingontorjuntakykyä.
     * 
     * @param mod kertoo paljollako haarniskataso muuttuu
     */
    public void modifyArmourClass(int mod) {
        this.armourClass += mod;
    }
    
    /**
     * Metodi palauttaa sankarin täysiin sielun ja ruumiin voimiin.
     */
    public void parane() {
        if (kesto > 0) {
            this.kesto = this.pohjaKesto;
        }
    }
    
    /**
     * Kunkin sankarityypin itse toteutettava erikistaito.
     * @return käyttöliittymän näyttämä info taidosta
     */
    public abstract String spesiaali();
    
    /**
     * Metodi toimittaa sankarin tiedot inforuudulle.
     * @return sankarin tämän hetkiset tiedot
     */
    @Override
    public String toString() {
        return "Kesto: " + kesto + "\n"
             + "Ase: " + ase + "\n"
             + "AC: " + armourClass + "\n"
             + "Mod: " + statMod;
    }
}
