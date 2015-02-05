package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee käsitteen kortti.
 */
public class Kortti {
    private final Maa maa;
    private final int arvo;
    private boolean oikeinpain;
    private Korttipakka sijainti;

/**
 * Konstruktori luo uuden kortin annetulla maalla ja arvolla.
 * Alussa kortti on väärinpäin.
 * 
 * @param maa Kortin maa
 * @param arvo Kortin arvo
 */
    public Kortti(Maa maa, int arvo) {
        this.maa = maa;
        this.arvo = arvo;
        this.oikeinpain = false;
    }
    
/**
 * Metodi kääntää kortin oikeinpäin.
 */    
    public void kaannaKorttiOikeinpain() {
        oikeinpain = true;
    }
    
/**
 * Metodi kääntää kortin väärinpäin. 
 */    
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
    
/**
 * Metodi kertoo, onko kortti oikein- vai väärinpäin.
 * 
 * @return Totuusarvo siitä, onko kortti oikeinpäin
 */
    public boolean oikeinPain() {
        return oikeinpain;
    }
}
