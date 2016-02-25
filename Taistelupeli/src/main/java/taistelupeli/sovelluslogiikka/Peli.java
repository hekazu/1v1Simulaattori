package taistelupeli.sovelluslogiikka;

import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import taistelupeli.kayttoliittyma.Kayttis;
import taistelupeli.pelaaja.Kirves;
import taistelupeli.pelaaja.Sankari;
import taistelupeli.pelaaja.Soturi;
import taistelupeli.vastus.Hiisi;
import taistelupeli.vastus.Luurankokuningas;
import taistelupeli.vastus.Minotauri;
import taistelupeli.vastus.Morko;

/**
 * Luokka toimii sankarin ja hirviöiden välikätenä, antaen näille mahdollisuuden
 * hakata toisiaan.
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

    /**
     * Konstruktori luo monista hajanaisista luokista kokonaisuuden jota Peli
     * käsittelee.
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

        kayttoliittyma = new Kayttis(this);
    }

    /**
     * Metodi käynnistää pelin.
     */
    public void aloita() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                kayttoliittyma.setVisible(true);
            }
        });
        kayttoliittyma.paivitaInforuutu(pahis + " seisoo tielläsi!\n");
        kayttoliittyma.paivitaSankarinTiedot(pelaaja.toString());
    }

    private void paivitaPelitilanne() {
        boolean kasitteleVihollisenVuoro = true;
        if (pahis.onkoKuollut()) {
            kasitteleVihollisenVuoro = false;
            if (onkoKampanjaOhi()) {
                kayttoliittyma.paivitaInforuutu("Voitit!");
                kayttoliittyma.setEnabled(false);
            }
        }
        if (kasitteleVihollisenVuoro) {
            pahiksenVuoro();
            kayttoliittyma.paivitaSankarinTiedot(pelaaja.toString());
            if (pelaaja.havisitkoPelin()) {
                kayttoliittyma.paivitaInforuutu("Turpiin tuli.\nRIP");
                kayttoliittyma.setEnabled(false);
            }
        }
    }

    /**
     * Metodi toimii käyttöliittymän linkkinä saada pelaajan käskyt mukaan
     * peliin.
     *
     * Loppupuolella kutsutaan vuorojärjestyksen ylläpitävää metodia
     * paivitaPelitilanne()
     *
     * @param hereWeGo pelaajan painamaa näppäintä vastaava komento
     */
    public void pelaajanVuoro(Toiminto hereWeGo) {
        switch (hereWeGo) {
            case HYOKKAYS:
                pelaajanHyokkays();
                kayttoliittyma.paivitaSankarinTiedot(pelaaja.toString());
                break;
            case ERIKOISTAITO:
                kayttoliittyma.paivitaInforuutu(pelaaja.spesiaali());
                kayttoliittyma.paivitaSankarinTiedot(pelaaja.toString());
                break;
            default:
                kayttoliittyma.paivitaInforuutu("\n\nTapahtui tuntematon komentovirhe. Sulje käyttöliittymä ja yritä myöhemmin uudestaan.");
                kayttoliittyma.setEnabled(false);
                break;
        }
        paivitaPelitilanne();
    }

//      Tekstipohjaiseen debuggaukseen
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
            case HYOKKAYS:
                int auts = pahis.hyokkaa();
                if (hitscan.osuuko(pahis.getModifier(), pelaaja.getArmourClass())) {
                    pelaaja.otaVahinkoa(auts);
                    kayttoliittyma.paivitaInforuutu(pahis + " osuu sinuun hyökkäyksellään, "
                            + "aiheuttaen " + auts + " vahinkoa!\n");
                } else {
                    kayttoliittyma.paivitaInforuutu("Kaikeksi onneksi " + pahis + " lyö ohi.\n");
                }
                break;
            case ERIKOISTAITO:
                kayttoliittyma.paivitaInforuutu(pahis.spesialisoi());
                break;
        }
    }

    private void pelaajanHyokkays() {
        int vahinko = pelaaja.hyokkaa();
        if (hitscan.osuuko(pelaaja.getModifier(), pahis.getArmourClass())) {
            pahis.otaVahinkoa(vahinko);
            kayttoliittyma.paivitaInforuutu("Lyöntisi osuu, tehden " + vahinko + " vahinkoa.\n");
        } else {
            kayttoliittyma.paivitaInforuutu("Lyöntisi menee ohi.\n");
        }
    }

    private boolean onkoKampanjaOhi() {
        kayttoliittyma.paivitaInforuutu(pahis + " kaatuu kuolleena maahan.\n");
        if (kampanja.empty()) {
            return true;
        } else {
            if (pahis.toString().equals("Minotauri")) {
                pelaaja.aseenVaihto(new Kirves());
                kayttoliittyma.paivitaInforuutu("Poimit minotaurin aseen käyttöösi.\nOnhan se hieman isokokoinen, mutta toiminee hyvin.\n"
                        + "Taitosi tuntuvat muutenkin kehittyneen seikkailun edetessä!\nTaitoarvosi nousee!\n");
                pelaaja.muutaModifier(pelaaja.getModifier() + 1);
            }
            pahis = kampanja.pop();
            kayttoliittyma.paivitaInforuutu(pahis + " nousee varjoista valmiina taisteluun!\n"
                    + "Päättäväisyys vyöryy ylitsesi. Paranet täysin vammoistasi!\n");
            pelaaja.parane();
            kayttoliittyma.paivitaSankarinTiedot(pelaaja.toString());
            return false;
        }
    }

    private void pelaaUudestaan() {
        pelaaja = new Soturi();

        kampanja = new Stack();
        kampanja.push(new Luurankokuningas(pelaaja));
        kampanja.push(new Minotauri());
        kampanja.push(new Hiisi());

        pahis = kampanja.pop();
    }
}
