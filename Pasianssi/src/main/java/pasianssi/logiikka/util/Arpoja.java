package pasianssi.logiikka.util;

import java.util.Random;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;

/**
 * Luokka sisältää toiminnallisuuden satunnaisen luvun arpomiseen
 * korttipakasta.
 */
public class Arpoja {
    private Random arpoja;

    public Arpoja() {
        this.arpoja = new Random();
    }

/**
 * Metodi arpoo satunnaisen luvun annetusta korttipakasta.
 * 
 * @param korttipakka Korttipakka, jonka korteista arvonta tapahtuu
 * @return Arvottu kortti
 */
    public Kortti arvoKortti(Korttipakka korttipakka) {
        int arvottuLuku = arvoLuku(korttipakka.pakanKoko());
        
        return korttipakka.haeKortti(arvottuLuku);
    }
    
    private int arvoLuku(int raja) {
        return arpoja.nextInt(raja);
    }
}
