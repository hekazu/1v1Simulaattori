
package taistelupeli.vastus;

public class Minotauri extends Morko {
    private boolean ryntays;

    public Minotauri() {
        super(40, 12, 4);
        this.ryntays = false;
    }

    @Override
    public int hyokkaa() {
        if (!ryntays) {
            if (r.nextDouble() > 0.3) {
                return r.nextInt(12) + 1 + super.getModifier();
            }
            spesialisoi();
            return 0;
        }
        ryntays = false;
        return r.nextInt(12) + r.nextInt(12) + (super.getModifier() * 2);
    }

    @Override
    public void spesialisoi() {
        System.out.println("Hirviömäinen otus polkee sorkkajalkaansa maata vasten ja "
                         + "laskee päätään. Tämä ei vaikuta hyvältä...");
        ryntays = true;
    }
    
    @Override
    public String toString() {
        return "Minotauri";
    }
}
