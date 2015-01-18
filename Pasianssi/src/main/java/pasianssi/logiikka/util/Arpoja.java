package pasianssi.logiikka.util;

import java.util.Random;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;

public class Arpoja {
    private Random arpoja;

    public Arpoja() {
        this.arpoja = new Random();
    }
    
    public Kortti arvoKortti(Korttipakka korttipakka) {
        int arvottuLuku = arvoLuku(korttipakka.pakanKoko());
        
        return korttipakka.haeKortti(arvottuLuku);
    }
    
    private int arvoLuku(int raja) {
        return arpoja.nextInt(raja);
    }
}
