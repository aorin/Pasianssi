package pasianssi.kayttoliittyma;

import java.awt.Rectangle;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;

/**
 * Luokka ohjaa korttipakka-oliota toimimaan käyttäjän hiiren avulla annettujen
 * ohjeiden mukaisesti.
 */
public class TapahtumaAlue extends Rectangle {
    private Korttipakka pakka;

    public TapahtumaAlue(int x, int y, int leveys, int korkeus, Korttipakka pakka) {
        super(x, y, leveys, korkeus);
        this.pakka = pakka;
    }

    public void alueeseenKlikattu(Kortti siirrossaOlevaKortti, int x, int y) {
        if (siirrossaOlevaKortti != null || pakka.listaKorteista().isEmpty()) {
            return;
        }
        
        Kortti paallimmainenKortti = pakka.haeKortti(pakka.pakanKoko() - 1);
        if (!paallimmainenKortti.oikeinPain()) {
            kaannaKortti(paallimmainenKortti);
        }
    }
    
    private void kaannaKortti(Kortti kortti) {
        if (pakka.kortinVoiKaantaaOikeinpain(kortti)) {
            kortti.kaannaKorttiOikeinpain();
        }
    }
    
    public Kortti alueenPaallaPainettu(Kortti siirrettava, int x, int y) {
        if (siirrettava != null) {
            return siirrettava;
        }
        
        return null;
    }

    public Kortti alueenPaallaPaastettyIrti(Kortti siirrettava, int x, int y) {
        if (siirrettava == null) {
            return null;
        }
        
        if (pakka.lisaaKorttiEhdolla(siirrettava)) {
            return null;
        }
        
        return siirrettava;
    }
}
