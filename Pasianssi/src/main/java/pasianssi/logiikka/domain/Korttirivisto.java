package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee käsitteen korttirivistö. Korttirivistö on käytännössä
 * lista korttipakkoja.
 */
public class Korttirivisto {
    private List<Korttipakka> korttirivisto;

/**
 * Konstruktori luo tyhjän uuden korttirivistön.
 */
    public Korttirivisto() {
        this.korttirivisto = new ArrayList<>();
    }
    
/**
 * Metodi lisää rivistöön uuden korttipakan. Samalla se asettaa korttipakan
 * sijainniksi itsensä.
   
   @param rivi Lisättävä rivi
 */    
    public void lisaaRivi(Korttipakka rivi) {
        korttirivisto.add(rivi);
        rivi.setSijainti(this);
    }
    
    public Korttipakka haePakka(int indeksi) {
        return korttirivisto.get(indeksi);
    }
    
    public int haeIndeksi(Korttipakka pakka) {
        return korttirivisto.indexOf(pakka);
    }
    
    public int koko() {
        return korttirivisto.size();
    }
}
