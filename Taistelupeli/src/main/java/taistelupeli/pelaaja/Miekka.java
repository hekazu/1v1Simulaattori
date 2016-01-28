package taistelupeli.pelaaja;

import java.util.Random;

public class Miekka implements Ase {

    private Random r;

    public Miekka() {
        r = new Random();
    }

    @Override
    public int teeVahinkoa() {
        return r.nextInt(8) + 1;
    }

}
