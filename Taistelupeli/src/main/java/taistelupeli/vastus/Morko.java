
package taistelupeli.vastus;

import java.util.Random;


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
    
    public boolean onkoKuollut() {
        if (kesto > 0) {
            return false;
        }
        return true;
    }
    
    public void otaVahinkoa(int damaskuukkeli) {
        this.kesto -= damaskuukkeli;
    }
    
    public int getArmourClass() {
        return this.armourClass;
    }
    
    public int getModifier() {
        return ominaisuusMuuttuja;
    }
    
    public abstract int hyokkaa();
    
    public abstract void spesialisoi();
    
    @Override
    public abstract String toString();
}
