package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee käsitteen korttirivistö.
 * <p>
 * Korttirivistö on käytännössä lista korttipakkoja.
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
 * Metodi lisää rivistöön uuden korttipakan.
 * 
 * @param pakka Lisättävä korttipakka.
 */    
    public void lisaaPakka(Korttipakka pakka) {
        korttirivisto.add(pakka);
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
