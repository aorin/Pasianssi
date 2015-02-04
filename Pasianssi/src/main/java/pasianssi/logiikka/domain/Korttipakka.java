package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

public class Korttipakka {
    private List<Kortti> korttipakka;
    private Korttirivisto sijainti;

    public Korttipakka() {
        this.korttipakka = new ArrayList<>();
    }
    
    public boolean lisaaKortti(Kortti kortti) {
        kortti.setSijainti(this);
        return korttipakka.add(kortti);
    }
    
    public boolean poistaKortti(Kortti kortti) {
        korttipakka.remove(kortti);
        return false;
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
