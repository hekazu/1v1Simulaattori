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
        super(12, 14);
    }

    @Override
    public int hyokkaa() {
        return r.nextInt(6) + 1;
    }

    @Override
    public void spesialisoi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
