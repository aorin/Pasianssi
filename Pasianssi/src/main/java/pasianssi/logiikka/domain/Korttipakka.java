package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

public class Korttipakka {
    private List<Kortti> korttipakka;

    public Korttipakka() {
        this.korttipakka = new ArrayList<>();
    }
    
    public void lisaaKortti(Kortti kortti) {
        korttipakka.add(kortti);
    }
    
    public void poistaKortti(Kortti kortti) {
        korttipakka.remove(kortti);
    }
    
    public int pakanKoko() {
        return korttipakka.size();
    }
    
    public Kortti haeKortti(int indeksi) {
        return korttipakka.get(indeksi);
    }

    public List<Kortti> listaKorteista() {
        return korttipakka;
    }
}
