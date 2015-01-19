package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

public class Pelialusta {
    private Korttirivisto korttirivisto;
    private Korttipakka korttipakka;
    private KorttipakkaMaittainJaJarjestyksessa pakka1, pakka2, pakka3, pakka4;

    public Pelialusta() {
        this.korttipakka = new Korttipakka();
        this.korttirivisto = new Korttirivisto();
        this.pakka1 = new KorttipakkaMaittainJaJarjestyksessa();
        this.pakka2 = new KorttipakkaMaittainJaJarjestyksessa();
        this.pakka3 = new KorttipakkaMaittainJaJarjestyksessa();
        this.pakka4 = new KorttipakkaMaittainJaJarjestyksessa();
    }
    
    public void lisaaKorttirivi(Korttirivi rivi) {
        korttirivisto.lisaaRivi(rivi);
    }

    public Korttipakka getKorttipakka() {
        return korttipakka;
    }
}
