package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee käsitteen kortin maa.
 * <p>
 * Jokaisen maalla on muuttujana arvo, jotka eroavat toisistaan.
 */
public enum Maa {
    RUUTU(0), RISTI(1), HERTTA(2), PATA(3);
    
    private final int arvo;

    private Maa(int arvo) {
        this.arvo = arvo;
    }

    public int getArvo() {
        return arvo;
    }
}
