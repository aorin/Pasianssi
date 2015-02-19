package pasianssi.kayttoliittyma;

import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.KorttipakkaVuoroVareinJaJarjestyksessa;
import pasianssi.logiikka.domain.Korttirivisto;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka tarjoaa toiminnallisuuden korttien sijainnin päivittämiseen.
 */
public class KorttienSijainninPaivittaja {
    private Pelialusta alusta;
    private int korttipakkaX, korttipakkaY;
    private int korttirivistoX, korttirivistoY;
    private int tavoiterivistoX, tavoiterivistoY;
    public static final int korttienValiRivistossa = 25;
    private int pakkojenValiRivistossa;
    private int leveys, korkeus;

/**
 * Konstruktori asettaa luokalle käytössä olevan pelialustan ja
 * määrittää korttien sijaintiin liittyvät muuttujat.
 * 
 * @param alusta Käytössä oleva pelialusta.
 */
    public KorttienSijainninPaivittaja(Pelialusta alusta) {
        this.alusta = alusta;

        asetaSijaintiMuuttujat();

        leveys = KuvanAntaja.kortinLeveys;
        korkeus = KuvanAntaja.kortinKorkeus;
    }

    private void asetaSijaintiMuuttujat() {
        korttirivistoX = 10;
        korttirivistoY = 10;
        tavoiterivistoX = 400;
        tavoiterivistoY = 480;
        pakkojenValiRivistossa = 10;
        korttipakkaX = 10;
        korttipakkaY = 480;
    }

/**
 * Metodi asettaa kaikille kortille sijainnin.
 */    
    public void asetaKaikkienKorttienSijainti() {
        asetaKorttipakanSijainti(alusta.getKorttipakka(), korttipakkaX, korttipakkaY);

        asetaRivistonSijainti(alusta.getKorttirivisto(), korttirivistoX, korttirivistoY);

        asetaRivistonSijainti(alusta.getTavoiterivisto(), tavoiterivistoX, tavoiterivistoY);
    }

    private void asetaRivistonSijainti(Korttirivisto rivisto, int x, int y) {
        for (int i = 0; i < rivisto.koko(); i++) {
            asetaKorttipakanSijainti(rivisto.haePakka(i), x, y);

            x += leveys + pakkojenValiRivistossa;
        }
    }

    private void asetaKorttipakanSijainti(Korttipakka pakka, int x, int y) {
        pakka.setX(x);
        pakka.setY(y);

        for (Kortti kortti : pakka.listaKorteista()) {
            kortti.setX(x);
            kortti.setY(y);

            if (pakka instanceof KorttipakkaVuoroVareinJaJarjestyksessa) {
                y += korttienValiRivistossa;
            }
        }
    }

/**
 * Metodi päivittää yhden kortin sijainnin.
 * 
 * @param kortti Kortti, jonka sijainti halutaan päivittää.
 */
    public void paivitaKortinSijainti(Kortti kortti) {
        Korttipakka pakka = kortti.getSijainti();
        
        if (pakka instanceof KorttipakkaVuoroVareinJaJarjestyksessa) {
            kortti.setX(pakka.getX());

            int kortinIndeksi = pakka.haeIndeksi(kortti);

            kortti.setY(pakka.getY() + kortinIndeksi * korttienValiRivistossa);
        } else {
            kortti.setX(pakka.getX());
            kortti.setY(pakka.getY());
        }
    }

}
