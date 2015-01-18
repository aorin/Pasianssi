package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

public class Pelialusta {
    private List<Korttirivi> korttirivisto;
    private Korttipakka korttipakka;

    public Pelialusta() {
        this.korttipakka = new Korttipakka();
        this.korttirivisto = new ArrayList<>();
    }
    
    public void lisaaKorttirivi(Korttirivi rivi) {
        korttirivisto.add(rivi);
    }

    public Korttipakka getKorttipakka() {
        return korttipakka;
    }
}
