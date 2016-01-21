/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taistelupeli.pelaaja;

import java.util.Random;

/**
 *
 * @author henpeura
 */
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
