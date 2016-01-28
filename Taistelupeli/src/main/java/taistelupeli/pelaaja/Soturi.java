package taistelupeli.pelaaja;

public class Soturi extends Sankari {
    // olio luodaan placeholder stateilla, tarkasta manuaaleista joku kerta osuvammat
    public Soturi() {
        super(26, new Miekka(), 3, 17);
    }

    @Override
    public void spesiaali() {
        super.modifyArmourClass(2);
    }

}
