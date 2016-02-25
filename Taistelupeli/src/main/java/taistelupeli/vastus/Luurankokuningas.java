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
 * Luokka edustaa viimeistä ja voimakkainta kohdattavissa olevaa vihollista,
 * Luurankokuningasta.
 *
 * @author henpeura
 */
public class Luurankokuningas extends Morko {

    private int ultimaLaskuri;
    private final Sankari kohde;

    /**
     * Konstruktori luo ilmenenmän Luurankokuninkaasta ja asettaa tämän
     * spesiaalin kohteen.
     *
     * @param kohde Luurankokuninkaan vastustaja, jonka spesiaali toteutuessaan
     * tappaa
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
     * Luurankokuninkaan erikoitaito eroaahuomattavasti muista. Aloitettuaan
     * sen, Luurankokuningas ei siitä poikkea ja päästessään loppuun lopettaa se
     * pelaajan pelin siihen paikkaan.
     * @return erikoistaidon vaihekohtainen tilannepäivitys
     */
    @Override
    public String spesialisoi() {
        switch (ultimaLaskuri) {
            case -1:
                ultimaLaskuri = 3;
                return "Tämä riittää! On aika lopettaa taistelumme!\n"
                        + this + "alkaa manata jotain suurta...\n";
            case 3:
                ultimaLaskuri--;
                return "Tuhosi koittaa pian! " + this + " julistaa.\n";
            case 2:
                ultimaLaskuri--;
                return this + " naurahtaa: \"Nyeh-heh-heh!\"\n";
            case 1:
                ultimaLaskuri--;
                return this + " on melkein valmis loitsunsa kanssa!\nHänen on kaaduttava nyt!\n";
            case 0:
                kohde.otaVahinkoa(kohde.getKesto());
                return "Loppu on tullut. " + this + " laukaisee loitsunsa!\n";
            default :
                return "Nyt ollaan jännän äärellä.";
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
