package pasianssi.logiikka.domain;

public class Pelialusta {
    private Korttirivisto korttirivisto;
    private Korttirivisto tavoiterivisto;
    private Korttipakka korttipakka;

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
