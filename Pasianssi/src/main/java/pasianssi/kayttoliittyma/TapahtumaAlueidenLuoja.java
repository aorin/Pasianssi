package pasianssi.kayttoliittyma;

import java.util.ArrayList;
import java.util.List;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.Korttirivisto;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka tarjoaa toiminnallisuuden tapahtuma-alueiden luomiseen.
 */
public class TapahtumaAlueidenLuoja {

    private Pelialusta alusta;
    private int leveys, korkeus;

/**
 * Konstruktori asettaa luokkalle pelialustan.
 * 
 * @param alusta Käytössä oleva pelialusta.
 */
    public TapahtumaAlueidenLuoja(Pelialusta alusta) {
        this.alusta = alusta;
        this.leveys = KuvanAntaja.kortinLeveys;
        this.korkeus = KuvanAntaja.kortinKorkeus;
    }

/**
 * 
 * @return 
 */    
    public List<TapahtumaAlue> annaKlikatunTapahtumaAlueet() {
        List<TapahtumaAlue> alueet = new ArrayList<>();

        lisaaPakanAlue(alueet, alusta.getKorttipakka());
        lisaaRivistonKlikatutAlueet(alueet);

        return alueet;
    }

    public List<TapahtumaAlue> annaPainetunTapahtumaAlueet() {
        List<TapahtumaAlue> alueet = new ArrayList<>();

        lisaaPakanAlue(alueet, alusta.getKorttipakka());
        lisaaRivistonPainetutAlueet(alueet);
        lisaaTavoitepakkojenAlueet(alueet);

        return alueet;
    }

    public List<TapahtumaAlue> annaIrtiPaastetynTapahtumaAlueet() {
        List<TapahtumaAlue> alueet = new ArrayList<>();

        lisaaRivistonIrtipaastetytAlueet(alueet);
        lisaaTavoitepakkojenAlueet(alueet);

        return alueet;
    }

    private void lisaaPakanAlue(List<TapahtumaAlue> alueet, Korttipakka pakka) {
        Kortti kortti;

        if (pakka.koko() != 0) {
            kortti = pakka.haePaallimmainenKortti();
        } else {
            kortti = null;
        }

        alueet.add(new TapahtumaAlue(pakka.getX(), pakka.getY(), leveys, korkeus, pakka, kortti));
    }

    private void lisaaTavoitepakkojenAlueet(List<TapahtumaAlue> alueet) {
        Korttirivisto rivisto = alusta.getTavoiterivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            lisaaPakanAlue(alueet, rivisto.haePakka(i));
        }
    }

    private void lisaaRivistonKlikatutAlueet(List<TapahtumaAlue> alueet) {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            if (pakka.koko() == 0) {
                continue;
            }

            Kortti kortti = pakka.haePaallimmainenKortti();
            if (!kortti.oikeinPain()) {
                alueet.add(new TapahtumaAlue(kortti.getX(), kortti.getY(), leveys, korkeus, pakka, kortti));
            }
        }
    }

    private void lisaaRivistonPainetutAlueet(List<TapahtumaAlue> alueet) {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            for (Kortti kortti : pakka.listaKorteista()) {
                if (kortti.oikeinPain()) {
                    alueet.add(new TapahtumaAlue(kortti.getX(), kortti.getY(), leveys, korkeus, pakka, kortti));
                }
            }
        }
    }

    private void lisaaRivistonIrtipaastetytAlueet(List<TapahtumaAlue> alueet) {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            int x = pakka.getX();
            int y;

            if (pakka.koko() == 0) {
                y = pakka.getY();
            } else {
                y = pakka.haePaallimmainenKortti().getY() + KorttienSijainninPaivittaja.korttienValiRivistossa;
            }

            alueet.add(new TapahtumaAlue(x, y, leveys, korkeus, pakka, null));
        }
    }
}
