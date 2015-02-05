package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee käsitteen kortin maa. Jokaisen maahan kuuluu myös
 * jokin arvo, jotka ovat eroavat toisistaan.
 */
public enum Maa {
    RUUTU(0), RISTI(1), HERTTA(2), PATA(3);
    
    private int arvo;

    private Maa(int arvo) {
        this.arvo = arvo;
    }

    public int getArvo() {
        return arvo;
    }
}
