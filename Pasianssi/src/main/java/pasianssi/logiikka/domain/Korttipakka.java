package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee käsitteen korttipakka.
 */
public class Korttipakka {
    private List<Kortti> korttipakka;

/**
 * Luo uuden tyhjän korttipakan. Korttipakka ei sijaitse aluksi missään korttirivistössä.
 */
    public Korttipakka() {
        this.korttipakka = new ArrayList<>();
    }

/**
 * Metodi lisää kortin, jos ehto sen lisäämiselle täyttyy. Normaalissa
 * korttipakassa ehto ei koskaan täyty.
 * 
 * @param kortti Lisättävä kortti
 * 
 * @return Palauttaa totta, jos lisäys onnistuu
 */
    public boolean lisaaKorttiEhdolla(Kortti kortti) {
        return false;
    }
   
/**
 * Metodi lisää kortin korttipakkaan.
 * 
 * @param kortti Lisättävä kortti
 */    
    public void lisaaKortti(Kortti kortti) {
        korttipakka.add(kortti);
    }
    
/**
 * Metodi poistaa kortin korttipakasta.
 * 
 * @param kortti Poistettava kortti
 */    
    public void poistaKortti(Kortti kortti) {
        korttipakka.remove(kortti);
    }
    
/**
 * Metodi kertoo, voiko kortin kääntää oikeinpäin. Kortin voi kääntää,
 * jos se on pakan viimeinen kortti.
 * 
 * @param kortti Tutkittava kortti
 * 
 * @return Palauttaa true, jos kortin voi kääntää
 */    
    public boolean kortinVoiKaantaaOikeinpain(Kortti kortti) {
        return haeIndeksi(kortti) == korttipakka.size() - 1;
    }
    
    public void siirryYhdellaEteenpain() {
        Kortti paallimmainen = korttipakka.get(korttipakka.size() - 1);
        
        for (int i = korttipakka.size() - 1; i > 0; i--) {
            korttipakka.add(i + 1, korttipakka.get(i));
        }
        
        korttipakka.add(0, paallimmainen);
    }
    
    public int pakanKoko() {
        return korttipakka.size();
    }
    
    public Kortti haeKortti(int indeksi) {
        return korttipakka.get(indeksi);
    }
    
    public int haeIndeksi(Kortti kortti) {
        return korttipakka.indexOf(kortti);
    }

    public List<Kortti> listaKorteista() {
        return korttipakka;
    }
}
