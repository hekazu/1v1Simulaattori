package taistelupeli.sovelluslogiikka;

import java.util.Random;
import java.util.Scanner;
import taistelupeli.pelaaja.Sankari;
import taistelupeli.pelaaja.Soturi;
import taistelupeli.vastus.Hiisi;
import taistelupeli.vastus.Minotauri;
import taistelupeli.vastus.Morko;

public class Peli {

    private Sankari pelaaja;
    private Morko pahis;
    private Scanner lukija;
    private Random hitscan;

    // toistaiseksi luodaan versio pelistä joka debugataan tekstipohjaisen käyttiksen kautta
    // todennäköisesti ensi viikolla aletaan siirtää kokonaisuutta graafiseen käyttöliittymään
    public Peli() {
        pelaaja = new Soturi();
        pahis = new Minotauri();
        hitscan = new Random();
        lukija = new Scanner(System.in);
    }

    public void aloita() {
        System.out.println("Komennot:\n"
                + " 1. Hyökkää\n"
                + " 2. Spesiaali\n"
                + " 3. Seuraava\n");
        while (true) {
            pelaajanVuoro();
            if (pahis.onkoKuollut()) {
                System.out.println("Voitit!");
                break;
            }
            pahiksenVuoro();
            if (pelaaja.havisitkoPelin()) {
                System.out.println("Turpiin tuli.");
                break;
            }
        }
    }

    private void pelaajanVuoro() {
        System.out.println("Sinulla on vielä " + pelaaja.getKesto() +
                           " pistettä kestoa jäljellä.");
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
        if (osuukoPahis()) {
            int auts = pahis.hyokkaa();
            pelaaja.otaVahinkoa(auts);
            System.out.println(pahis + " osuu sinuun hyökkäyksellään, "
                             + "aiheuttaen " + auts + " vahinkoa!\n");
        } else {
            System.out.println("Kaikeksi onneksi " + pahis + " lyö ohi.\n");
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
        return  osumaHeitto() + pelaaja.getModifier()
                >= pahis.getArmourClass();
    }

    private boolean osuukoPahis() {
        return osumaHeitto() + pahis.getModifier()
               >= pelaaja.getArmourClass();
    }
    
    public int osumaHeitto() {
        return hitscan.nextInt(20) + 1;
    }
}
