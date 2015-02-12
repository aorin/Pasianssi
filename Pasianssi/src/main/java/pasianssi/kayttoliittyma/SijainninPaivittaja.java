package pasianssi.kayttoliittyma;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.KorttipakkaVuoroVareinJaJarjestyksessa;
import pasianssi.logiikka.domain.Korttirivisto;
import pasianssi.logiikka.domain.Pelialusta;

public class SijainninPaivittaja {

    private Pelialusta alusta;
    private int korttipakkaX, korttipakkaY;
    private int korttirivistoX, korttirivistoY;
    private int tavoiterivistoX, tavoiterivistoY;
    public static final int korttienValiRivistossa = 20;
    private int pakkojenValiRivistossa;
    private int leveys, korkeus;

    public SijainninPaivittaja(Pelialusta alusta) {
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

    public void paivitaKortinSijainti(Korttipakka pakka, Kortti kortti) {
        if (pakka instanceof KorttipakkaVuoroVareinJaJarjestyksessa) {
            kortti.setX(pakka.getX());

            int kortinIndeksi = pakka.haeIndeksi(kortti);

            kortti.setY(pakka.getY() + kortinIndeksi * korttienValiRivistossa);
        } else {
            kortti.setX(pakka.getX());
            kortti.setY(pakka.getY());
        }
    }

    public List<TapahtumaAlue> annaKlikatunTapahtumaAlueet() {
        List<TapahtumaAlue> alueet = new ArrayList<>();

        lisaaPakanAlue(alueet, alusta.getKorttipakka());
        lisaaRivistonKlikatutAlueet(alueet);

        return alueet;
    }

    public List<TapahtumaAlue> annaPainetunTapahtumaAlueet() {
        List<TapahtumaAlue> alueet = new ArrayList<>();

        lisaaPakanAlue(alueet, alusta.getKorttipakka());
        lisaaRivistonPainetutAlueet(alueet);
        lisaaTavoitepakkojenAlueet(alueet);

        return alueet;
    }

    public List<TapahtumaAlue> annaIrtiPaastetynTapahtumaAlueet() {
        List<TapahtumaAlue> alueet = new ArrayList<>();

        lisaaRivistonIrtipaastetytAlueet(alueet);
        lisaaTavoitepakkojenAlueet(alueet);

        return alueet;
    }

    private void lisaaPakanAlue(List<TapahtumaAlue> alueet, Korttipakka pakka) {
        Kortti kortti;

        if (pakka.koko() != 0) {
            kortti = pakka.haePaallimmainenKortti();
        } else {
            kortti = null;
        }

        alueet.add(new TapahtumaAlue(pakka.getX(), pakka.getY(), leveys, korkeus, pakka, kortti));
    }

    private void lisaaTavoitepakkojenAlueet(List<TapahtumaAlue> alueet) {
        Korttirivisto rivisto = alusta.getTavoiterivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            lisaaPakanAlue(alueet, rivisto.haePakka(i));
        }
    }

    private void lisaaRivistonKlikatutAlueet(List<TapahtumaAlue> alueet) {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            if (pakka.koko() == 0) {
                continue;
            }

            Kortti kortti = pakka.haePaallimmainenKortti();
            if (!kortti.oikeinPain()) {
                alueet.add(new TapahtumaAlue(kortti.getX(), kortti.getY(), leveys, korkeus, pakka, kortti));
            }
        }
    }

    private void lisaaRivistonPainetutAlueet(List<TapahtumaAlue> alueet) {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            for (Kortti kortti : pakka.listaKorteista()) {
                if (kortti.oikeinPain()) {
                    alueet.add(new TapahtumaAlue(kortti.getX(), kortti.getY(), leveys, korkeus, pakka, kortti));
                }
            }
        }
    }

    private void lisaaRivistonIrtipaastetytAlueet(List<TapahtumaAlue> alueet) {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        for (int i = 0; i < rivisto.koko(); i++) {
            Korttipakka pakka = rivisto.haePakka(i);

            int x = pakka.getX();
            int y;

            if (pakka.koko() == 0) {
                y = pakka.getY();
            } else {
                y = pakka.haePaallimmainenKortti().getY() + korttienValiRivistossa;
            }

            alueet.add(new TapahtumaAlue(x, y, leveys, korkeus, pakka, null));
        }
    }
}
