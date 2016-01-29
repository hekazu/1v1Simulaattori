package taistelupeli.pelaaja;

public class Soturi extends Sankari {
    private final int pohjaArmourClass;
    // olio luodaan placeholder stateilla, tarkasta manuaaleista joku kerta osuvammat
    public Soturi() {
        super(26, new Miekka(), 3, 17);
        this.pohjaArmourClass = 17;
    }
    
    @Override
    public int hyokkaa() {
        if (super.getArmourClass() != pohjaArmourClass) {
            super.modifyArmourClass(pohjaArmourClass - super.getArmourClass());
        }
        return super.hyokkaa();
    }

    @Override
    public void spesiaali() {
        if (super.getArmourClass() == pohjaArmourClass) {
            super.modifyArmourClass(2);
        }
    }

}
