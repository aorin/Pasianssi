package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee käsitteen korttipakka.
 */
public class Korttipakka {
    private List<Kortti> korttipakka;
    private Korttirivisto sijainti;

/**
 * Luo uuden tyhjän korttipakan. Korttipakka ei sijaitse aluksi missään korttirivistössä.
 */
    public Korttipakka() {
        this.korttipakka = new ArrayList<>();
        this.sijainti = null;
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
 * Metodi lisää kortin korttipakkaan. Samalla se päivittää kortin
 * sijainniksi itsensä.
 * 
 * @param kortti Lisättävä kortti
 */    
    public void lisaaKortti(Kortti kortti) {
        korttipakka.add(kortti);
        kortti.setSijainti(this);
    }
    
/**
 * Metodi poistaa kortin korttipakasta.
 * 
 * @param kortti Poistettava kortti
 */    
    public void poistaKortti(Kortti kortti) {
        korttipakka.remove(kortti);
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

    public Korttirivisto getSijainti() {
        return sijainti;
    }

    public void setSijainti(Korttirivisto sijainti) {
        this.sijainti = sijainti;
    }

    public List<Kortti> listaKorteista() {
        return korttipakka;
    }
}
