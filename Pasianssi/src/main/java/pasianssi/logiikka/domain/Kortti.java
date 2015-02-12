package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee käsitteen kortti.
 */
public class Kortti {
    private final Maa maa;
    private final int arvo;
    private boolean oikeinpain;
    private Korttipakka sijainti;
    private int x, y;

/**
 * Konstruktori luo uuden kortin annetulla maalla ja arvolla.
 * <p>
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
    
/**
 * Metodi kertoo, onko kortti oikein- vai väärinpäin.
 * 
 * @return Palauttaa true, jos kortti on oikeinpäin, muulloin false.
 */
    public boolean oikeinPain() {
        return oikeinpain;
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

    public void setSijainti(Korttipakka sijainti) {
        this.sijainti = sijainti;
    }

    public Korttipakka getSijainti() {
        return sijainti;
    }
}
