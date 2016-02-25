package taistelupeli.pelaaja;

/**
 * Interface yhdistää kaikki sankarin käyttämät aseet.
 * 
 * @author henpeura
 */
public interface Ase {

    /**
     * Asekohtaisesti toteutettava tietyn vaihteluvälin vahingontuoton palauttava metodi.
     * @return kyseisen hyökkäyksen vahinkomäärä
     */
    public int teeVahinkoa();
    
    /**
     * Palauttaa aseen merkkijonoesityksen.
     * @return aseen nimi
     */
    @Override
    public String toString();
}
