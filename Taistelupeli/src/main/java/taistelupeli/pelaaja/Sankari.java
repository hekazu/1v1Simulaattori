package taistelupeli.pelaaja;

public abstract class Sankari {
    private int kesto;
    private Ase ase;
    private int statMod;
    private int armourClass;
    
    public Sankari(int kesto, Ase ase, int mod, int ac) {
        this.kesto = kesto;
        this.ase = ase;
        this.statMod = mod;
        this.armourClass = ac;
    }
    
    public int getKesto() {
        return this.kesto;
    }
    
    public int hyokkaa() {
        return ase.teeVahinkoa() + statMod;
    }
    
    public void muutaModifier(int uusiMod) {
        this.statMod = uusiMod;
    }
    
    public void swappinWeapons(Ase uusiAse) {
        this.ase = uusiAse;
    }
    
    public void otaVahinkoa(int damaskuukkeli) {
        this.kesto -= damaskuukkeli;
    }
    
    public boolean havisitkoPelin() {
        if (kesto > 0) {
            return false;
        }
        return true;
    }
    
    public int getArmourClass() {
        return this.armourClass;
    }
    
    public void modifyArmourClass(int mod) {
        this.armourClass += mod;
    }
    
    public abstract void spesiaali();
}
