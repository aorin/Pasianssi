package pasianssi.logiikka;

import pasianssi.logiikka.domain.Pelialusta;
import pasianssi.logiikka.util.Korttienjakaja;

/** 
 * Luokka luo uuden pelialustan ja jakaa kortit.
 */
public class Sovellus {
    private Pelialusta pelialusta;
    private Korttienjakaja jakaja;

    public Sovellus() {
        this.pelialusta = new Pelialusta();
        this.jakaja = new Korttienjakaja(pelialusta);
    }
    
    public void kaynnista() {
        jakaja.jaaKortit();
    }

    public Pelialusta getPelialusta() {
        return pelialusta;
    }
}
