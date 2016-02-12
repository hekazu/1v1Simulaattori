package taistelupeli.sovelluslogiikka;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import taistelupeli.pelaaja.Sankari;
import taistelupeli.pelaaja.Soturi;
import taistelupeli.vastus.Hiisi;
import taistelupeli.vastus.Minotauri;
import taistelupeli.vastus.Morko;

/**
 * Luokka toimii sankarin ja hirviöiden välikätenä, antaen näille mahdollisuuden hakata toisiaan.
 * 
 * @author henpeura
 */

public class Peli {

    private Sankari pelaaja;
    private Stack<Morko> kampanja;
    private Morko pahis;
    private Scanner lukija;
    private Random hitscan;

    // toistaiseksi luodaan versio pelistä joka debugataan tekstipohjaisen käyttiksen kautta
    public Peli() {
        pelaaja = new Soturi();
        
        kampanja = new Stack();
        kampanja.push(new Minotauri());
        kampanja.push(new Hiisi());
        
        pahis = kampanja.pop();
        hitscan = new Random();
        lukija = new Scanner(System.in);
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
        String toiminto = pahis.toimi();
        switch (toiminto) {
            case "hyokkays":
                int auts = pahis.hyokkaa();
                if (osuukoPahis()) {
                    pelaaja.otaVahinkoa(auts);
                    System.out.println(pahis + " osuu sinuun hyökkäyksellään, "
                            + "aiheuttaen " + auts + " vahinkoa!\n");
                } else {
                    System.out.println("Kaikeksi onneksi " + pahis + " lyö ohi.\n");
                }
                break;
            case "erikoistaito" :
                pahis.spesialisoi();
                break;
        }
    }

    private void pelaajanHyokkays() {
        if (osuukoPelaaja()) {
            int vahinko = pelaaja.hyokkaa();
            pahis.otaVahinkoa(vahinko);
            System.out.println("Lyöntisi osuu, tehden " + vahinko + " vahinkoa.\n");
        } else {
            System.out.println("Lyöntisi menee ohi.\n");
        }
    }

    private boolean osuukoPelaaja() {
        return osumaHeitto() + pelaaja.getModifier()
                >= pahis.getArmourClass();
    }

    private boolean osuukoPahis() {
        return osumaHeitto() + pahis.getModifier()
                >= pelaaja.getArmourClass();
    }

    /**
     * Metodi heittää d20:n osana osumisen varmistamista
     * 
     * @return nopanheiton arvo 
     */
    public int osumaHeitto() {
        return hitscan.nextInt(20) + 1;
    }
    
    private boolean onkoKampanjaOhi() {
        if (kampanja.empty()) {
            return true;
        } else {
            System.out.println(pahis + " kaatuu kuolleena maahan.");
            pahis = kampanja.pop();
            System.out.println(pahis + " nousee varjoista valmiina taisteluun!\n"
                            + "Päättäväisyys vyöryy ylitsesi. Paranet täysin vammoistasi!\n");
            pelaaja.parane();
            return false;
        }
    }
}
