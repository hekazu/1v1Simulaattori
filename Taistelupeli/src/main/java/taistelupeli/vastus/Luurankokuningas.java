/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taistelupeli.vastus;

import taistelupeli.pelaaja.Sankari;
import taistelupeli.sovelluslogiikka.Toiminto;
import static taistelupeli.sovelluslogiikka.Toiminto.ERIKOISTAITO;
import static taistelupeli.sovelluslogiikka.Toiminto.HYOKKAYS;

/**
 * Luokka edustaa viimeistä ja voimakkainta kohdattavissa olevaa vihollista, Luurankokuningasta.
 * 
 * @author henpeura
 */
public class Luurankokuningas extends Morko {
    private int ultimaLaskuri;
    private final Sankari kohde;

    /**
     * Konstruktori luo ilmenenmän Luurankokuninkaasta ja asettaa tämän spesiaalin kohteen.
     * 
     * @param kohde Luurankokuninkaan vastustaja, jonka spesiaali toteutuessaan tappaa
     */
    public Luurankokuningas(Sankari kohde) {
        super(45, 15, 4);
        ultimaLaskuri = -1;
        this.kohde = kohde;
    }

    /**
     * Metodi palauttaa tämän vuoron toiminnan tyypin.
     * 
     * @return suoritettava toiminto
     */
    @Override
    public Toiminto toimi() {
        if (ultimaLaskuri < 0) {
            if (getKesto() > 20) {
                return HYOKKAYS;
            } else {
                return ERIKOISTAITO;
            }
        }
        return ERIKOISTAITO;
    }

    /**
     * Metodi tuottaa hyökkäyskohtaisen vahingon.
     * 
     * @see taistelupeli.pelaaja.Sankari#hyokkaa() 
     * 
     * @see taistelupeli.vastus.Minotauri#hyokkaa() 
     * 
     * @see taistelupeli.vastus.Hiisi#hyokkaa() 
     * 
     * @return hyökkäyksen vahinko
     */
    @Override
    public int hyokkaa() {
        return r.nextInt(6) + r.nextInt(6) + 2 + super.getModifier();
    }

    /**
     * Metodi aloittaa ja ylläpitää Luurankokuninkaan erikoistaidon toiminnan.
     * 
     * Luurankokuninkaan erikoitaito eroaahuomattavasti muista.
     * Aloitettuaan sen, Luurankokuningas ei siitä poikkea ja päästessään loppuun lopettaa se pelaajan pelin siihen paikkaan.
     */
    @Override
    public void spesialisoi() {
        switch (ultimaLaskuri) {
            case -1 :
                System.out.println("Tämä riittää! On aika lopettaa taistelumme!\n"
                                 + this + "alkaa manata jotain suurta...");
                ultimaLaskuri = 3;
                break;
            case 3 :
                System.out.println("Tuhosi koittaa pian! " + this + " julistaa.");
                ultimaLaskuri--;
                break;
            case 2 :
                System.out.println(this + " naurahtaa: \"Nyeh-heh-heh!\"");
                ultimaLaskuri--;
                break;
            case 1 :
                System.out.println(this + " on melkein valmis loitsunsa kanssa!\n"
                                 + "Hänen on kaaduttava nyt!");
                ultimaLaskuri--;
                break;
            case 0 :
                System.out.println("Loppu on tullut. " + this + " laukaisee loitsunsa!");
                kohde.otaVahinkoa(kohde.getKesto());
                break;
        }
    }

    /**
     * Metodi palauttaa luokkaa vastaavan merkkijonoesityksen.
     * 
     * @return String "Luurankokuningas"
     */
    @Override
    public String toString() {
        return "Luurankokuningas";
    }
    
    /**
     * Metodia käytetään vain luokan testaamiseen.
     * 
     * @return oliomuuttuja ultimaLaskurin arvo
     */
    public int getUltimaLaskuri() {
        return ultimaLaskuri;
    }
    
    /**
     * Metodia käytetään luokan testaamiseen.
     * 
     * @return Luurankokuninkaan poloinen vastustaja
     */
    public Sankari getKohde() {
        return kohde;
    }
}
