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
    }
    
    public Korttipakka haeRivi(int indeksi) {
        return korttirivisto.get(indeksi);
    }
    
    public int koko() {
        return korttirivisto.size();
    }
}
