package pasianssi.logiikka;

import pasianssi.logiikka.domain.Pelialusta;
import pasianssi.logiikka.util.Korttienjakaja;

/** 
 * Luokka luo uuden pelialustan ja korttienjakajan, jonka avulla
 * se pystyy jakamaan kortit.
 * 
 * @see pasianssi.logiikka.util.Korttienjakaja
 */
public class Sovellus {
    private Pelialusta pelialusta;
    private Korttienjakaja jakaja;

/**
 * Konstruktori luo uuden tyhjän pelialustan ja korttienjakajan.
 */
    public Sovellus() {
        this.pelialusta = new Pelialusta();
        this.jakaja = new Korttienjakaja(pelialusta);
    }
    
/**
 * Metodi käynnistää sovelluksen pyytämällä korttienjakajaa jakamaan kortit.
 */    
    public void kaynnista() {
        jakaja.jaaKortit();
    }

    public Pelialusta getPelialusta() {
        return pelialusta;
    }
}
