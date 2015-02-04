package pasianssi.logiikka.domain;

public class Kortti {
    private Maa maa;
    private int arvo;
    private boolean oikeinpain;
    private Korttipakka sijainti;

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

    public int getArvo() {
        return arvo;
    }

    public Maa getMaa() {
        return maa;
    }

    public Korttipakka getSijainti() {
        return sijainti;
    }

    public void setSijainti(Korttipakka sijainti) {
        this.sijainti = sijainti;
    }
    
    public boolean oikeinPain() {
        return oikeinpain;
    }
}
