
package taistelupeli.vastus;

import java.util.Random;

/**
 * Luokka toimii pohjana kaikille pelaajan kohtaamille vastustajille.
 * 
 * @author henpeura
 */
public abstract class Morko {
    private int kesto;
    protected Random r;
    private final int armourClass;
    private final int ominaisuusMuuttuja;
    
    public Morko(int hp, int ac, int mod) {
        this.kesto = hp;
        this.r = new Random();
        this.armourClass = ac;
        this.ominaisuusMuuttuja = mod;
    }
    
    public int getKesto() {
        return this.kesto;
    }
    
    /**
     * Metodi selvitää onko kyseinen hirviö menettänyt kaikki kestopisteensä
     * ja tuupertunut.
     * 
     * @return otuksen kuolematilanne
     */
    public boolean onkoKuollut() {
        if (kesto > 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Metodilla vähennetään mörön kestoa syötteen verran.
     * 
     * @param damaskuukkeli mörön kestosta vähennettävä määrä
     */
    public void otaVahinkoa(int damaskuukkeli) {
        this.kesto -= damaskuukkeli;
    }
    
    public int getArmourClass() {
        return this.armourClass;
    }
    
    public int getModifier() {
        return ominaisuusMuuttuja;
    }
    
    public abstract String toimi();
    
    public abstract int hyokkaa();
    
    public abstract void spesialisoi();
    
    @Override
    public abstract String toString();
}
