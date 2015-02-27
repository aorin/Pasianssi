package pasianssi.logiikka.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.Korttirivisto;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka tarjoaa toiminnallisuuden korttien siirtämiseen automaattisesti
 * pakoista toiseen niin, että tavoitteena on saada kaikki kortit tavoitepakkoihin.
 */
public class AutomaattiSiirtaja {
    private Pelialusta alusta;
    private Set<Kortti> sijainnit;
    private Kortti ensimmainenKorttipakanKortti;
    private List<Kortti> siirretytKortit;

/**
 * Konstruktori asettaa siirtäjälle pelialustan, luo uuden 
 * tyhjän joukon korteille, joita ei siirretä ja luo uuden
 * tyhjän listan korteille, jotka on viimeksi siirretty.
 * 
 * @param alusta Käytettävänä oleva pelialusta.
 */    
    public AutomaattiSiirtaja(Pelialusta alusta) {
        this.alusta = alusta;
        this.sijainnit = new HashSet<>();
        this.siirretytKortit = new ArrayList<>();
    }

/**
 * Metodi kääntää yhden kortin oikeinpäin tai siirtää kortin korttipakasta
 * toiseen.
 * <p>
 * Jos siirrettävä kortti ei ole pakan päällimmäinen kortti, siirrettään
 * samalla kaikki kortin päällä olevat kortit.
 * 
 * @return Palauttaa listan korteista, joita on siirretty. 
 */    
    public List<Kortti> siirraKortti() {
        siirretytKortit.clear();
        
        siirraRivistostaTavoitepakkaan();

        if (siirretytKortit.isEmpty()) {
            siirraRivistostaToiseen();
        }

        if (siirretytKortit.isEmpty()) {
            siirraPakastaTavoiterivistoon();
        }

        if (siirretytKortit.isEmpty()) {
            siirraPakastaRivistoon();
        }

        if (siirretytKortit.isEmpty()) {
            kayPakkaaLapi();
        } else {
            ensimmainenKorttipakanKortti = null;
        }

        return siirretytKortit;
    }

    private void siirraRivistostaTavoitepakkaan() {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            if (pakka.koko() == 0) {
                continue;
            }

            Kortti kortti = pakka.haePaallimmainenKortti();

            if (kaannaKortti(kortti)) {
                siirretytKortit.add(kortti);
                return;
            }

            if (lisaaRivistoon(kortti, alusta.getTavoiterivisto())) {
                siirretytKortit.add(kortti);
                return;
            }
        }
    }

    private void siirraRivistostaToiseen() {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            for (int j = 0; j < pakka.koko(); j++) {
                Kortti kortti = pakka.haeKortti(j);

                if ((pakka.haeKortti(0).getArvo() == 13 && pakka.haeKortti(0).oikeinPain()) || sijainnit.contains(kortti)) {
                    continue;
                }

                if (kortti.oikeinPain() && lisaaRivistoon(kortti, rivisto)) {
                    siirretytKortit.add(kortti);
                    int kertoja = pakka.koko() - j;

                    for (int k = 0; k < kertoja; k++) {
                        Kortti seuraavaKortti = pakka.haeKortti(j);
                        lisaaRivistoon(seuraavaKortti, rivisto);
                        siirretytKortit.add(seuraavaKortti);
                    }

                    if (pakka.koko() != 0 && pakka.haePaallimmainenKortti().getArvo() == kortti.getArvo() + 1) {
                        sijainnit.add(kortti);
                    }
                    return;
                }
            }
        }
    }

    private void siirraPakastaTavoiterivistoon() {
        Korttipakka pakka = alusta.getKorttipakka();
        if (pakka.koko() == 0) {
            return;
        }

        Kortti kortti = pakka.haePaallimmainenKortti();
        
        if (kaannaKortti(kortti)) {
            siirretytKortit.add(kortti);
            return;
        }
        
        if (lisaaRivistoon(kortti, alusta.getTavoiterivisto())) {
            siirretytKortit.add(kortti);
        }
    }

    private void siirraPakastaRivistoon() {
        Korttipakka pakka = alusta.getKorttipakka();
        if (pakka.koko() == 0) {
            return;
        }
        Kortti kortti = pakka.haePaallimmainenKortti();

        if(lisaaRivistoon(kortti, alusta.getKorttirivisto())) {
            siirretytKortit.add(kortti);
        }
    }

    private boolean lisaaRivistoon(Kortti kortti, Korttirivisto rivisto) {
        Korttipakka sijainti = kortti.getSijainti();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            if (rivisto.haePakka(i).lisaaKorttiEhdolla(kortti)) {
                sijainti.poistaKortti(kortti);
                return true;
            }
        }

        return false;
    }

    private boolean kaannaKortti(Kortti kortti) {
        if (!kortti.oikeinPain()) {
            kortti.kaannaKorttiOikeinpain();
            return true;
        }

        return false;
    }

    private void kayPakkaaLapi() {
        Korttipakka pakka = alusta.getKorttipakka();

        if (pakka.koko() < 2) {
            return;
        }

        Kortti paallimmainenKortti = pakka.haePaallimmainenKortti();
        if (paallimmainenKortti.equals(ensimmainenKorttipakanKortti)) {
            return;
        } else if (ensimmainenKorttipakanKortti == null) {
            ensimmainenKorttipakanKortti = paallimmainenKortti;
        }
        
        pakka.siirryYhdellaEteenpain();
        siirretytKortit.add(paallimmainenKortti);
    }
}
