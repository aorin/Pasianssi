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
    
/**
 * Metodi hakee korttipakan indeksin perusteella.
 * 
 * @param indeksi Indeksi, jolla haetaan.
 * @return Korttipakka, joka vastaa haettua indeksiä.
 */    
    public Korttipakka haePakka(int indeksi) {
        return korttirivisto.get(indeksi);
    }
    
/**
 * Metodi hakee korttipakan indeksin.
 * 
 * @param pakka Korttipakka, jonka indeksiä haetaan.
 * @return Haetun pakan indeksi rivistössä.
 */    
    public int haeIndeksi(Korttipakka pakka) {
        return korttirivisto.indexOf(pakka);
    }
    
/**
 * Metodi hakee rivistön koon.
 * 
 * @return Rivistössä olevien korttipakkojen määrä. 
 */    
    public int koko() {
        return korttirivisto.size();
    }
}
