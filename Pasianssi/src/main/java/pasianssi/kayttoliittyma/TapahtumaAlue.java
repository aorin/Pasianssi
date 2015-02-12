package pasianssi.kayttoliittyma;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;

/**
 * Luokka ohjaa korttipakka-oliota toimimaan käyttäjän hiiren avulla annettujen
 * ohjeiden mukaisesti.
 */
public class TapahtumaAlue extends Rectangle {

    private Korttipakka pakka;
    private Kortti kortti;

    public TapahtumaAlue(int x, int y, int leveys, int korkeus, Korttipakka pakka, Kortti kortti) {
        super(x, y, leveys, korkeus);
        this.pakka = pakka;
        this.kortti = kortti;
    }

    public void alueeseenKlikattu(int x, int y) {
        if (kortti == null) {
            return;
        }

        if (!kortti.oikeinPain()) {
            kortti.kaannaKorttiOikeinpain();
        } else {
            pakka.siirryYhdellaEteenpain();
        }
    }

    public List<Kortti> alueenPaallaPainettu(int x, int y) {
        List<Kortti> kortit = new ArrayList<>();

        if (kortti != null && kortti.oikeinPain()) {
            for (int i = pakka.haeIndeksi(kortti); i < pakka.koko(); i++) {
                kortit.add(pakka.haeKortti(i));
            }
        }

        return kortit;
    }

    public void alueenPaallaPaastettyIrti(List<Kortti> siirrettava, int x, int y) {
        Korttipakka edellinenPakka = siirrettava.get(0).getSijainti();
        if (pakka.lisaaKorttiEhdolla(siirrettava.get(0))) {
            for (int i = 1; i < siirrettava.size(); i++) {
                pakka.lisaaKorttiEhdolla(siirrettava.get(i));
            }

            for (int i = 0; i < siirrettava.size(); i++) {
                edellinenPakka.poistaKortti(siirrettava.get(i));
            }
        }
    }
}
