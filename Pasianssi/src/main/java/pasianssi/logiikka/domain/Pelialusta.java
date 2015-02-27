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
    
/**
 * Metodi palauttaa kaikki pelialustan sisältämät korttipakat.
 * 
 * @return Lista kaikista korttipakoista. 
 */    
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
  
/**
 * Metodi kertoo, onko pakassa tai korttirivistössä kortteja.
 * 
 * @return Palauttaa false, jos pakassa tai korttirivistössä on kortteja, muulloin true.
 */    
    public boolean pakassaTaiRivistossaEiKortteja() {
        if (korttipakka.koko() != 0) {
            return false;
        }
        
        for (int i = 0; i < korttirivisto.koko(); i++) {
            if (korttirivisto.haePakka(i).koko() != 0) {
                return false;
            }
        }
        
        return true;
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
