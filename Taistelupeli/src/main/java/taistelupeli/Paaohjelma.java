
package taistelupeli;

import taistelupeli.sovelluslogiikka.Peli;

/**
 *
 * @author henpeura
 * 
 * Käynnistää ohjelman toiminnan.
 */
public class Paaohjelma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO luo graafinen käyttöliittymä
        new Peli().aloita();
    }

}
