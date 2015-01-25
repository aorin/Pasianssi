package pasianssi.logiikka.domain;

public class Korttirivi {
    private Korttipakka kortit;

    public Korttirivi() {
        this.kortit = new Korttipakka();
    }
    
    public void lisaaKortti(Kortti kortti) {
        this.kortit.lisaaKortti(kortti);
    }

    public Korttipakka getKortit() {
        return kortit;
    }
}
