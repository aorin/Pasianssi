package pasianssi.logiikka;

import java.util.HashSet;
import java.util.Set;
import pasianssi.kayttoliittyma.KorttienSijainninPaivittaja;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.Korttirivisto;
import pasianssi.logiikka.domain.Pelialusta;

public class AutomaattiSiirtaja {

    private Pelialusta alusta;
    private KorttienSijainninPaivittaja paivittaja;
    private Set<Kortti> sijainnit;

    public AutomaattiSiirtaja(Pelialusta alusta, KorttienSijainninPaivittaja paivittaja) {
        this.alusta = alusta;
        this.paivittaja = paivittaja;
        this.sijainnit = new HashSet<>();
    }

    public boolean siirraKortti() {
        if (siirraRivistostaTavoitepakkaan()) {
            return true;
        }

        if (siirraRivistostaToiseen()) {
            return true;
        }

        if (siirraPakastaTavoiterivistoon()) {
            return true;
        }

        if (siirraPakastaRivistoon()) {
            return true;
        }

        if (kayPakkaaLapi()) {
            return true;
        }

        return false;
    }

    private boolean siirraRivistostaTavoitepakkaan() {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            if (pakka.koko() == 0) {
                continue;
            }

            Kortti kortti = pakka.haePaallimmainenKortti();

            if (kaannaKortti(kortti)) {
                return true;
            }

            if (lisaaRivistoon(kortti, alusta.getTavoiterivisto())) {
                return true;
            }
        }

        return false;
    }

    private boolean siirraRivistostaToiseen() {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            for (int j = 0; j < pakka.koko(); j++) {
                Kortti kortti = pakka.haeKortti(j);

                if ((pakka.haeKortti(0).getArvo() == 13 && pakka.haeKortti(0).oikeinPain()) || sijainnit.contains(kortti)) {
                    continue;
                }

                if (kortti.oikeinPain() && lisaaRivistoon(kortti, rivisto)) {
                    int kertoja = pakka.koko() - j;

                    for (int k = 0; k < kertoja; k++) {
                        lisaaRivistoon(pakka.haeKortti(j), rivisto);
                    }

                    if (pakka.koko() != 0 && pakka.haePaallimmainenKortti().getArvo() == kortti.getArvo() + 1) {
                        sijainnit.add(kortti);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private boolean siirraPakastaTavoiterivistoon() {
        Korttipakka pakka = alusta.getKorttipakka();
        if (pakka.koko() == 0) {
            return false;
        }

        Kortti kortti = pakka.haePaallimmainenKortti();

        return kaannaKortti(kortti) || lisaaRivistoon(kortti, alusta.getTavoiterivisto());
    }

    private boolean siirraPakastaRivistoon() {
        Korttipakka pakka = alusta.getKorttipakka();
        if (pakka.koko() == 0) {
            return false;
        }
        Kortti kortti = pakka.haePaallimmainenKortti();

        return lisaaRivistoon(kortti, alusta.getKorttirivisto());
    }

    private boolean lisaaRivistoon(Kortti kortti, Korttirivisto rivisto) {
        Korttipakka sijainti = kortti.getSijainti();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            if (rivisto.haePakka(i).lisaaKorttiEhdolla(kortti)) {
                sijainti.poistaKortti(kortti);
                paivittaja.paivitaKortinSijainti(kortti);
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

    private boolean kayPakkaaLapi() {
        Korttipakka pakka = alusta.getKorttipakka();

        if (pakka.koko() == 0) {
            return false;
        }

        pakka.siirryYhdellaEteenpain();
        return true;
    }
}
