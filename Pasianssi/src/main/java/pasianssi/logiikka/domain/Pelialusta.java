package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee pelialustan sisällön. 
 */
public class Pelialusta {
    private Korttirivisto korttirivisto;
    private Korttirivisto tavoiterivisto;
    private Korttipakka korttipakka;

/**
 * Konstruktorissa luodaan pelilaudalle yksi korttipakka ja kaksi korttirivistöä.
 */
    public Pelialusta() {
        this.korttipakka = new Korttipakka();
        this.korttirivisto = new Korttirivisto();
        this.tavoiterivisto = new Korttirivisto();
    }
    
    public List<Korttipakka> kaikkiKorttipakat() {
        List<Korttipakka> pakat = new ArrayList<>();
        
        pakat.add(korttipakka);
        
        for (int i = 0; i < korttirivisto.koko(); i++) {
            pakat.add(korttirivisto.haePakka(i));
        }
        
        for (int i = 0; i < tavoiterivisto.koko(); i++) {
            pakat.add(tavoiterivisto.haePakka(i));
        }
        
        return pakat;
    }

    public Korttirivisto getKorttirivisto() {
        return korttirivisto;
    }

    public Korttipakka getKorttipakka() {
        return korttipakka;
    }

    public Korttirivisto getTavoiterivisto() {
        return tavoiterivisto;
    }
}
