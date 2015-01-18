package pasianssi.logiikka.domain;

public class Korttirivi {
    private Korttipakka vaarinpainOlevatKortit, oikeinpainOlevatKortit;

    public Korttirivi() {
        this.oikeinpainOlevatKortit = new Korttipakka();
        this.vaarinpainOlevatKortit = new Korttipakka();
    }
    
    public void lisaaOikeinPainOlevaKortti(Kortti kortti) {
        kortti.kaannaKorttiOikeinpain();
        oikeinpainOlevatKortit.lisaaKortti(kortti);
    }
    
    public void lisaaVaarinPainOlevaKortti(Kortti kortti) {
        vaarinpainOlevatKortit.lisaaKortti(kortti);
    }
    
}
