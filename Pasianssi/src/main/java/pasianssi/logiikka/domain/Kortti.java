package pasianssi.logiikka.domain;

public class Kortti {
    private Maa maa;
    private int arvo;
    private boolean oikeinpain;

    public Kortti(Maa maa, int arvo) {
        this.maa = maa;
        this.arvo = arvo;
        this.oikeinpain = false;
    }
    
    public void kaannaKorttiOikeinpain() {
        oikeinpain = true;
    }
    
    public void kaannaKorttiVaarinpain() {
        oikeinpain = false;
    }
}
