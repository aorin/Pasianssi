package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka määrittelee käsitteen korttipakka.
 */
public class Korttipakka {
    private List<Kortti> korttipakka;
    private int x, y;

/**
 * Konstruktori luo uuden tyhjän korttipakan.
 */
    public Korttipakka() {
        this.korttipakka = new ArrayList<>();
    }

/**
 * Metodi lisää kortin, jos ehto sen lisäämiselle täyttyy.
 * <p>
 * Normaalissa korttipakassa ehto ei koskaan täyty, joten metodi palauttaa aina
 * false.
 * 
 * @param kortti Lisättävä kortti.
 * 
 * @return Palauttaa true, jos lisäys onnistuu.
 */
    public boolean lisaaKorttiEhdolla(Kortti kortti) {
        return false;
    }
   
/**
 * Metodi lisää kortin korttipakkaan ja asettaa kortin sijainniksi itsensä.
 * 
 * @param kortti Lisättävä kortti.
 */    
    public void lisaaKortti(Kortti kortti) {
        korttipakka.add(kortti);
        kortti.setSijainti(this);
    }
    
/**
 * Metodi poistaa kortin korttipakasta.
 * 
 * @param kortti Poistettava kortti.
 */    
    public void poistaKortti(Kortti kortti) {
        korttipakka.remove(kortti);
    }
    
/**
 * Metodi siirtää korttien järjestystä pakassa niin, että pakan pakan päällimmäinen
 * kortti siirtyy viimeiseksi.
 */    
    public void siirryYhdellaEteenpain() {
        Kortti paallimmainen = korttipakka.get(korttipakka.size() - 1);
        paallimmainen.kaannaKorttiVaarinpain();
        List<Kortti> apupakka = new ArrayList<>();
        apupakka.add(paallimmainen);
        
        for (int i = 0; i < korttipakka.size() - 1; i++) {
            apupakka.add(korttipakka.get(i));
        }
        
        apupakka.get(apupakka.size() - 1).kaannaKorttiOikeinpain();
        korttipakka = apupakka;
    }
    
/**
 * Metodi kertoo pakan koon.
 * 
 * @return Pakassa olevien korttien määrä.
 */    
    public int koko() {
        return korttipakka.size();
    }
    
/**
 * Metodi hakee kortin indeksin perusteella.
 * 
 * @param indeksi Indeksi, jolla haetaan.
 * @return Indeksiä vastaava kortti.
 */    
    public Kortti haeKortti(int indeksi) {
        return korttipakka.get(indeksi);
    }
 
/**
 * Metodi hakee päällimmäisenä olevan kortin.
 * 
 * @return Pakan viimeisenä oleva kortti. 
 */    
    public Kortti haePaallimmainenKortti() {
        return korttipakka.get(korttipakka.size() - 1);
    }
    
/**
 * Metodi hakee kortin indeksin.
 * 
 * @param kortti Kortti, jonka indeksi pakassa halutaan selvittää.
 * @return Kortin indeksi.
 */    
    public int haeIndeksi(Kortti kortti) {
        return korttipakka.indexOf(kortti);
    }
/**
 * Metodi hakee listan pakassa olevista korteista.
 * 
 * @return Lista pakassa olevista korteista. 
 */
    public List<Kortti> listaKorteista() {
        return korttipakka;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
