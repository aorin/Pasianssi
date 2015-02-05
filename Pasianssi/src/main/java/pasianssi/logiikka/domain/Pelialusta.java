package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee pelialustan sisällön. 
 */
public class Pelialusta {
    private Korttirivisto korttirivisto;
    private Korttirivisto tavoiterivisto;
    private Korttipakka korttipakka;

/**
 * Konstruktorissa luodaan pelilaudalle yksi korttipakka ja kaksi korttirivistöä.
 */
    public Pelialusta() {
        this.korttipakka = new Korttipakka();
        this.korttirivisto = new Korttirivisto();
        this.tavoiterivisto = new Korttirivisto();
    }

    public Korttirivisto getKorttirivisto() {
        return korttirivisto;
    }

    public Korttipakka getKorttipakka() {
        return korttipakka;
    }

    public Korttirivisto getTavoiterivisto() {
        return tavoiterivisto;
    }
}
