package pasianssi.logiikka.util;

import java.util.Collections;
import pasianssi.logiikka.domain.*;

public class Korttienjakaja {

    private Pelialusta pelialusta;
    private Korttipakka korttipakka;
    private Arpoja arpoja;

    public Korttienjakaja(Pelialusta pelialusta) {
        this.pelialusta = pelialusta;
        this.korttipakka = pelialusta.getKorttipakka();
        this.arpoja = new Arpoja();
    }

    public void jaaKortit() {
        luoKortit();

        lisaaOsaKorteistaRiveihin();
    }

    private void luoKortit() {
        for (Maa maa : Maa.values()) {
            for (int i = 1; i <= 13; i++) {
                korttipakka.lisaaKortti(new Kortti(maa, i));
            }
        }
        
        Collections.shuffle(korttipakka.listaKorteista());
    }

    private void lisaaOsaKorteistaRiveihin() {
        int rivinKoko = 7;

        while (rivinKoko > 0) {
            Korttirivi korttirivi = new Korttirivi();

            for (int i = 1; i <= rivinKoko; i++) {
                Kortti arvottuKortti = arpoja.arvoKortti(korttipakka);

                korttipakka.poistaKortti(arvottuKortti);
                
                if (i == rivinKoko) {
                    arvottuKortti.kaannaKorttiOikeinpain();
                }
                
                korttirivi.lisaaKortti(arvottuKortti);
            }
            
            pelialusta.getKorttirivisto().lisaaRivi(korttirivi);
            rivinKoko--;
        }
    }
}
