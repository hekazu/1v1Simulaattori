package taistelupeli.sovelluslogiikka;

import java.util.Stack;
import taistelupeli.kayttoliittyma.Kayttis;
import taistelupeli.kayttoliittyma.UusiPeliRuutu;
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

    private final Kayttis kayttoliittyma;
    private Sankari pelaaja;
    private Stack<Morko> kampanja;
    private Morko pahis;
    private final Osumanoppa hitscan;

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
        kayttoliittyma.paivitaInforuutu("Kotikylääsi ja naapurikaupunkia uhkaa tuntematon vihollinen kaukana vuorilla.\n"
                + " Olet päättänyt lähteä matkalle estämään tämän tuntemattoman pahuuden aikeet, niin hullulta kuin se vaikuttaakin.\n"
                + " Ystäväsi eivät vain meinaa uskoa sinuun. He ovat väärässä, ja todistat sen haastamalla tämän...\n"
                + " OTUKSEN taistoon.\n"
                + pahis + " seisoo edessäsi!\n");
        kayttoliittyma.paivitaSankarinTiedot(pelaaja.toString());
    }

    private void paivitaPelitilanne() {
        boolean kasitteleVihollisenVuoro = true;
        if (pahis.onkoKuollut()) {
            kasitteleVihollisenVuoro = false;
            if (onkoKampanjaOhi()) {
                kayttoliittyma.paivitaInforuutu("Voitit!");
                kayttoliittyma.setEnabled(false);
                UusiPeliRuutu newGame = new UusiPeliRuutu(this);
                newGame.peliLapaistiin();
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        newGame.setVisible(true);
                    }
                });
            }
        }
        if (kasitteleVihollisenVuoro) {
            pahiksenVuoro();
            kayttoliittyma.paivitaSankarinTiedot(pelaaja.toString());
            if (pelaaja.havisitkoPelin()) {
                kayttoliittyma.paivitaInforuutu("Turpiin tuli.\nRIP");
                kayttoliittyma.setEnabled(false);
                UusiPeliRuutu newGame = new UusiPeliRuutu(this);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        newGame.setVisible(true);
                    }
                });
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
        kayttoliittyma.paivitaInforuutu(pahis.postMortem());
        if (kampanja.empty()) {
            return true;
        } else {
            if (pahis.toString().equals("Minotauri")) {
                pelaaja.aseenVaihto(new Kirves());
                kayttoliittyma.paivitaInforuutu("Poimit minotaurin aseen käyttöösi.\n Onhan se hieman isokokoinen, mutta toiminee hyvin.\n"
                        + " Taitosi tuntuvat muutenkin kehittyneen seikkailun edetessä!\n  Taitoarvosi nousee!\n"
                        + " Varjoista astuu esiin itse paholainen, tai niin aurasta ainakin tuntuu.\n"
                        + " Tämä taistelu ratkaisee kaiken!\n");
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

    /**
     * Metodi aloittaa pelin alusta.
     */
    public void pelaaUudestaan() {
        pelaaja = new Soturi();

        kampanja = new Stack();
        kampanja.push(new Luurankokuningas(pelaaja));
        kampanja.push(new Minotauri());
        kampanja.push(new Hiisi());

        pahis = kampanja.pop();
        kayttoliittyma.paivitaSankarinTiedot(pelaaja.toString());
        kayttoliittyma.tyhjennaInforuutu();
        kayttoliittyma.setEnabled(true);
        this.aloita();
    }
}
