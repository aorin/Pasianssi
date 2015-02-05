package pasianssi.logiikka.util;

import java.util.Collections;
import pasianssi.logiikka.domain.*;

/**
 * Luokka luo ja jakaa kortit paikoilleen.
 */
public class Korttienjakaja {
    private Pelialusta pelialusta;
    private Korttipakka korttipakka;
    private Arpoja arpoja;

/**
 * Konstruktori luo uuden pelialustan ja arpojan.  
 */
    public Korttienjakaja(Pelialusta pelialusta) {
        this.pelialusta = pelialusta;
        this.korttipakka = pelialusta.getKorttipakka();
        this.arpoja = new Arpoja();
    }

/**
 * Metodi luo 52 uutta korttia, jakaa osan niistä seitsemään riviin ja lopuksi
 * luo neljä tyhjää tavoitepakkaa.
 */    
    public void jaaKortit() {
        luoKortit();

        lisaaOsaKorteistaRiveihin();
        
        luoTyhjatTavoitePakat();
    }

    private void luoKortit() {
        for (Maa maa : Maa.values()) {
            for (int i = 1; i <= 13; i++) {
                Kortti kortti = new Kortti(maa, i);
                korttipakka.lisaaKortti(kortti);
            }
        }
        Collections.shuffle(korttipakka.listaKorteista());
    }

    private void lisaaOsaKorteistaRiveihin() {
        int rivinKoko = 7;

        while (rivinKoko > 0) {
            KorttipakkaVuoroVareinJaJarjestyksessa korttirivi = new KorttipakkaVuoroVareinJaJarjestyksessa();

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
    
    private void luoTyhjatTavoitePakat() {
        Korttirivisto tavoiterivi = pelialusta.getTavoiterivisto();
        
        for (int i = 0; i < 4; i++) {
            tavoiterivi.lisaaRivi(new KorttipakkaMaittainJaJarjestyksessa());
        }
    }
}
