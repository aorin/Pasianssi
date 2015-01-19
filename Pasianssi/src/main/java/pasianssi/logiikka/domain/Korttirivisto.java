package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

public class Korttirivisto {
    private List<Korttirivi> korttirivisto;

    public Korttirivisto() {
        this.korttirivisto = new ArrayList<>();
    }
    
    public void lisaaRivi(Korttirivi rivi) {
        korttirivisto.add(rivi);
    }
    
    public Korttirivi haeRivi(int indeksi) {
        return korttirivisto.get(indeksi);
    }
}
