package pasianssi.kayttoliittyma;

import java.awt.Rectangle;
import java.util.List;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.KorttipakkaVuoroVareinJaJarjestyksessa;
import pasianssi.logiikka.domain.Korttirivisto;

public class KuvienSijainninPaivittaja {

    private int korttirivistoX, korttirivistoY;
    private int tavoiterivistoX, tavoiterivistoY;
    private int rivistonVali, korttienVali;
    private int korttipakkaX, korttipakkaY;
    private int kortinLeveys, kortinKorkeus;

    public KuvienSijainninPaivittaja() {
        korttirivistoX = 10;
        korttirivistoY = 10;
        tavoiterivistoX = 400;
        tavoiterivistoY = 400;
        rivistonVali = 10;
        korttienVali = 10;
        korttipakkaX = 10;
        korttipakkaY = 400;
        kortinLeveys  = KuvienLuoja.kortinLeveys;
        kortinKorkeus = KuvienLuoja.kortinKorkeus;
    }

    public void paivitaSijainti(Korttikuva kuva) {
        int x, y;

        Kortti kortti = kuva.getKortti();
        Korttipakka pakka = kortti.getSijainti();
        Korttirivisto rivisto = pakka.getSijainti();

        if (rivisto == null) {
            kuva.x = korttipakkaX;
            kuva.y = korttipakkaY;
            return;
        }

        int i = rivisto.haeIndeksi(pakka);
        int j = pakka.haeIndeksi(kortti);

        if (pakka instanceof KorttipakkaVuoroVareinJaJarjestyksessa) {
            x = korttirivistoX + (i * kortinLeveys + i * rivistonVali);
            y = korttirivistoY + (j * korttienVali);
        } else {
            x = tavoiterivistoX + (i * kortinLeveys + i * rivistonVali);
            y = tavoiterivistoY;
        }

        kuva.x = x;
        kuva.y = y;
    }
    
    public void paivitaPohjaSuorakulmienSijainnit(List<Rectangle> suorakulmiot) {
        for (int i = 0; i < suorakulmiot.size(); i++) {
            suorakulmiot.get(i).x = tavoiterivistoX + (i * kortinLeveys + i * rivistonVali);
            suorakulmiot.get(i).y = tavoiterivistoY;
        }
    }
}
