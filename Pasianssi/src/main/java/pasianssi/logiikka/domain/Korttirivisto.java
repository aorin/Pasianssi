package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

public class Korttirivisto {
    private List<Korttipakka> korttirivisto;

    public Korttirivisto() {
        this.korttirivisto = new ArrayList<>();
    }
    
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
