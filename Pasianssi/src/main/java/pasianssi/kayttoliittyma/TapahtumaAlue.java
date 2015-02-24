package pasianssi.kayttoliittyma;

import java.util.ArrayList;
import java.util.List;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;

/**
 * Luokka ohjaa korttipakka ja kortti -olioita toimimaan
 * annettujen ohjeiden mukaisesti.
 */
public class TapahtumaAlue {

    private Korttipakka pakka;
    private Kortti kortti;

    /**
     * Konstruktori asettaa alueelle korttipakan ja kortin.
     *
     * @param pakka Kohdalla olevan pakka.
     * @param kortti Kohdalla oleva kortti.
     */
    public TapahtumaAlue(Korttipakka pakka, Kortti kortti) {
        this.pakka = pakka;
        this.kortti = kortti;
    }

    /**
     * Metodi kääntää kortin oikeinpäin, jos kortti on väärinpäin, muulloin
     * siirtää päällimmäisen kortin pakan pohjalle.
     */
    public void alueeseenKlikattu() {
        if (kortti == null) {
            return;
        }

        if (!kortti.oikeinPain()) {
            kortti.kaannaKorttiOikeinpain();
        } else {
            if (pakka.getClass() == Korttipakka.class) {
                pakka.siirryYhdellaEteenpain();
            }
        }
    }

    /**
     * Metodi antaa listan korteista, joita on mahdollista siirtää.
     *
     * @return Lista siirrettävistä korteista.
     */
    public List<Kortti> alueenPaallaPainettu() {
        List<Kortti> kortit = new ArrayList<>();

        if (kortti != null && kortti.oikeinPain()) {
            for (int i = pakka.haeIndeksi(kortti); i < pakka.koko(); i++) {
                kortit.add(pakka.haeKortti(i));
            }
        }

        return kortit;
    }

    /**
     * Metodi lisää siirrettävänä olevat kortit kohdalla olevaan korttipakkaan,
     * jos mahdollista.
     *
     * @param siirrettava Siirrettävänä olevat kortit.
     */
    public void alueenPaallaPaastettyIrti(List<Kortti> siirrettava) {
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
