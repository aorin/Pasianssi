package pasianssi.logiikka.domain;

public class Pelialusta {
    private Korttirivisto korttirivisto;
    private Korttipakka korttipakka;
    private TavoiteRivi tavoitePakat;

    public Pelialusta() {
        this.korttipakka = new Korttipakka();
        this.korttirivisto = new Korttirivisto();
        this.tavoitePakat = new TavoiteRivi();
    }

    public Korttirivisto getKorttirivisto() {
        return korttirivisto;
    }

    public Korttipakka getKorttipakka() {
        return korttipakka;
    }

    public TavoiteRivi getTavoitePakat() {
        return tavoitePakat;
    }
}
