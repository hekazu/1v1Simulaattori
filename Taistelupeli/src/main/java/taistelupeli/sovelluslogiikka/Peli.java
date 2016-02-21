package taistelupeli.sovelluslogiikka;

import java.util.Scanner;
import java.util.Stack;
import taistelupeli.kayttoliittyma.Kayttis;
import taistelupeli.pelaaja.Kirves;
import taistelupeli.pelaaja.Sankari;
import taistelupeli.pelaaja.Soturi;
import taistelupeli.vastus.Hiisi;
import taistelupeli.vastus.Luurankokuningas;
import taistelupeli.vastus.Minotauri;
import taistelupeli.vastus.Morko;

/**
 * Luokka toimii sankarin ja hirviöiden välikätenä, antaen näille mahdollisuuden hakata toisiaan.
 * 
 * @author henpeura
 */

public class Peli {

    private Kayttis kayttoliittyma;
    private Sankari pelaaja;
    private Stack<Morko> kampanja;
    private Morko pahis;
    private Scanner lukija;
    private Osumanoppa hitscan;

    // toistaiseksi luodaan versio pelistä joka debugataan tekstipohjaisen käyttiksen kautta
    /**
     * Konstruktori luo monista hajanaisista luokista kokonaisuuden jota Peli käsittelee.
     */
    public Peli() {
        pelaaja = new Soturi();
        
        kampanja = new Stack();
        kampanja.push(new Luurankokuningas(pelaaja));
        kampanja.push(new Minotauri());
        kampanja.push(new Hiisi());
        
        pahis = kampanja.pop();
        hitscan = new Osumanoppa();
        lukija = new Scanner(System.in);
        
        kayttoliittyma = new Kayttis();
    }

    /**
     * Metodi käynnistää pelin ja organisoi vuorojärjestyksen.
     */
    public void aloita() {    
        System.out.println("Komennot:\n"
                + " 1. Hyökkää\n"
                + " 2. Spesiaali\n"
                + " 3. Seuraava\n");
        System.out.println(pahis + " seisoo tielläsi!");
        while (true) {
            pelaajanVuoro();
            if (pahis.onkoKuollut()) {
                if (onkoKampanjaOhi()) {
                    System.out.println("Voitit!");
                    break;
                } else {
                    continue;
                }
            }
            pahiksenVuoro();
            if (pelaaja.havisitkoPelin()) {
                System.out.println("Turpiin tuli.");
                break;
            }
        }
    }

    private void pelaajanVuoro() {
        System.out.println("Sinulla on vielä " + pelaaja.getKesto()
                + " pistettä kestoa jäljellä.");
        System.out.print("> ");
        String komento = lukija.nextLine();
        switch (komento) {
            case "1":
                pelaajanHyokkays();
                break;
            case "2":
                pelaaja.spesiaali();
                break;
            case "3":
                System.out.println("Tämä tulee toimintaan kunnolla vasta graafisessa liittymässä.");
                break;
            default:
                System.out.println("Epäkelpo komento.");
        }
    }

    private void pahiksenVuoro() {
        Toiminto toiminto = pahis.toimi();
        switch (toiminto) {
            case HYOKKAYS :
                int auts = pahis.hyokkaa();
                if (hitscan.osuuko(pahis.getModifier(), pelaaja.getArmourClass())) {
                    pelaaja.otaVahinkoa(auts);
                    System.out.println(pahis + " osuu sinuun hyökkäyksellään, "
                            + "aiheuttaen " + auts + " vahinkoa!\n");
                } else {
                    System.out.println("Kaikeksi onneksi " + pahis + " lyö ohi.\n");
                }
                break;
            case ERIKOISTAITO :
                pahis.spesialisoi();
                break;
        }
    }

    private void pelaajanHyokkays() {
        if (hitscan.osuuko(pelaaja.getModifier(), pahis.getArmourClass())) {
            int vahinko = pelaaja.hyokkaa();
            pahis.otaVahinkoa(vahinko);
            System.out.println("Lyöntisi osuu, tehden " + vahinko + " vahinkoa.\n");
        } else {
            System.out.println("Lyöntisi menee ohi.\n");
        }
    }
    
    private boolean onkoKampanjaOhi() {
        System.out.println(pahis + " kaatuu kuolleena maahan.");
        if (kampanja.empty()) {
            return true;
        } else {
            if (pahis.toString().equals("Minotauri")) {
                pelaaja.aseenVaihto(new Kirves());
                System.out.println("Poimit minotaurin aseen käyttöösi. Onhan se hieman isokokoinen, mutta toiminee hyvin.");
            }
            pahis = kampanja.pop();
            System.out.println(pahis + " nousee varjoista valmiina taisteluun!\n"
                            + "Päättäväisyys vyöryy ylitsesi. Paranet täysin vammoistasi!\n");
            pelaaja.parane();
            return false;
        }
    }
}
