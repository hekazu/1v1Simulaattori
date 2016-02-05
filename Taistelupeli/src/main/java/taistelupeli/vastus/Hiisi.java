/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taistelupeli.vastus;

/**
 *
 * @author henpeura
 */
public class Hiisi extends Morko {
    public Hiisi() {
        super(12, 14, 2);
    }

    @Override
    public int hyokkaa() {
        if (r.nextBoolean()) {
            return r.nextInt(6) + 1 + getModifier();
        }
        spesialisoi();
        return 0;
    }

    @Override
    public void spesialisoi() {
        System.out.println("Hiisi irvistää ilkeästi, muttei tee muuten mitään hyödyllistä.");
    }
    
    @Override
    public String toString() {
        return "Hiisi";
    }
}
