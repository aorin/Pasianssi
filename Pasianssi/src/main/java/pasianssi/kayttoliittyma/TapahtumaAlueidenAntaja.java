package pasianssi.kayttoliittyma;

import java.util.List;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.KorttipakkaVuoroVareinJaJarjestyksessa;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka tarjoaa toiminnallisuuden oikean tapahtuma-alueen löytämiseen.
 */
public class TapahtumaAlueidenAntaja {
    private Pelialusta alusta;
    private int leveys, korkeus;
    private List<Korttipakka> pakat;

/**
 * Konstruktori asettaa luokkalle pelialustan.
 * 
 * @param alusta Käytössä oleva pelialusta.
 */
    public TapahtumaAlueidenAntaja(Pelialusta alusta) {
        this.alusta = alusta;
        this.leveys = KuvanAntaja.kortinLeveys;
        this.korkeus = KuvanAntaja.kortinKorkeus;
        this.pakat = alusta.kaikkiKorttipakat();
    }

    public TapahtumaAlue annaKlikatunTapahtumaAlue(int x, int y) {
        Korttipakka pakka = kohdallaOlevaPakka(x, y);
        
        if (pakka != null) {
            if (pakka.koko() != 0) {
                Kortti kortti = pakka.haePaallimmainenKortti();
                return new TapahtumaAlue(pakka, kortti);
            }
        }
        
        return null;
    }
    
    public TapahtumaAlue annaPainetunTapahtumaAlue(int x, int y) {
        Korttipakka pakka = kohdallaOlevaPakka(x, y);
        
        if (pakka == null) {
            return null;
        }
        
        Kortti kortti;
        
        if (pakka.koko() == 0) {
            kortti = null;
        } else if (pakka instanceof KorttipakkaVuoroVareinJaJarjestyksessa) {
            kortti = kohdallaOlevaKortti(x, y, pakka);
        } else {
            kortti = pakka.haePaallimmainenKortti();
        }
        
        return new TapahtumaAlue(pakka, kortti);
    }
    
    public TapahtumaAlue annaIrtiPaastetynTapahtumaAlue(int x, int y) {
        Korttipakka pakka = kohdallaOlevaPakka(x, y);
        
        if (pakka == null) {
            return null;
        }
        return new TapahtumaAlue(pakka, null);
    }
    
    private Korttipakka kohdallaOlevaPakka(int x, int y) {
        for (Korttipakka pakka : pakat) {
            int pakkaX = pakka.getX();
            int pakkaY = pakka.getY();
            
            int pakanKorkeus = korkeus;
            if (pakka instanceof KorttipakkaVuoroVareinJaJarjestyksessa && pakka.koko() > 0) {
                pakanKorkeus += (pakka.koko() - 1) * KorttienSijainninPaivittaja.korttienValiRivistossa;
            }
            
            if (x >= pakkaX && x <= pakkaX + leveys && y >= pakkaY && y <= pakkaY + pakanKorkeus) {
                return pakka;
            }
        }
        
        return null;
    }
    
    private Kortti kohdallaOlevaKortti(int x, int y, Korttipakka pakka) {
        for (int i = pakka.koko() - 1; i >= 0; i--) {
            Kortti kortti = pakka.haeKortti(i);
            int korttiX = kortti.getX();
            int korttiY = kortti.getY();
            
            if (x >= korttiX && x <= korttiX + leveys && y >= korttiY && y <= korttiY + korkeus) {
                return kortti;
            }
        }
        
        return null;
    }
}
