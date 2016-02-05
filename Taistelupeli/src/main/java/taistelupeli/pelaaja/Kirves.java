
package taistelupeli.pelaaja;

import java.util.Random;

public class Kirves implements Ase {
    private Random r;
    
    public Kirves() {
        r = new Random();
    }
    
    @Override
    public int teeVahinkoa() {
        return r.nextInt(12) + 1;
    }
}
