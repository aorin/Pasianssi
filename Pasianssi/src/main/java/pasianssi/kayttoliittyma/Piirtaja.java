package pasianssi.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import pasianssi.logiikka.domain.*;

/**
 * Luokka tarjoaa toiminnallisuuden korttien kuvien ja tapahtuma-alueiden piirtämiseen.
 */
public class Piirtaja extends JPanel {

    private KuvanAntaja kuvanAntaja;
    private Pelialusta pelialusta;
    private Kortti siirrettava;
    private List<TapahtumaAlue> tapahtumaAlueet;
    private int korttipakkaX, korttipakkaY;
    private int korttirivistoX, korttirivistoY;
    private int tavoiterivistoX, tavoiterivistoY;
    private int rivistonKorttipakkojenVali, korttienValiRivistossa;

    public Piirtaja(Pelialusta alusta) {
        super.setBackground(new Color(28, 63, 126));

        this.pelialusta = alusta;
        this.kuvanAntaja = new KuvanAntaja();

        asetaPakkojenSijainnit();
        luoTapahtumaAlueet();
        lisaaKuuntelija();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        piirraTapahtumaAlueet(g);

        piirraKorttipakka(g, pelialusta.getKorttipakka(), korttipakkaX, korttipakkaY);
        piirraKorttirivisto(g, pelialusta.getKorttirivisto(), korttirivistoX, korttirivistoY);
        piirraKorttirivisto(g, pelialusta.getTavoiterivisto(), tavoiterivistoX, tavoiterivistoY);
    }

    private void asetaPakkojenSijainnit() {
        korttirivistoX = 10;
        korttirivistoY = 10;
        tavoiterivistoX = 400;
        tavoiterivistoY = 480;
        rivistonKorttipakkojenVali = 10;
        korttienValiRivistossa = 10;
        korttipakkaX = 10;
        korttipakkaY = 480;
    }

    private void piirraKorttirivisto(Graphics g, Korttirivisto rivisto, int x, int y) {
        for (int i = 0; i < rivisto.koko(); i++) {
            piirraKorttipakka(g, rivisto.haePakka(i), x, y);

            x += KuvanAntaja.kortinLeveys + rivistonKorttipakkojenVali;
        }
    }

    private void piirraKorttipakka(Graphics g, Korttipakka pakka, int x, int y) {
        for (int i = 0; i < pakka.pakanKoko(); i++) {
            piirraKortti(g, pakka.haeKortti(i), x, y);

            if (pakka instanceof KorttipakkaVuoroVareinJaJarjestyksessa) {
                if (pakka.haeKortti(i).oikeinPain()) {
                    y += 2 * korttienValiRivistossa;
                } else {
                    y += korttienValiRivistossa;
                }
            }
        }
    }

    public void piirraKortti(Graphics g, Kortti kortti, int x, int y) {
        Image kuva = kuvanAntaja.annaKortilleKuva(kortti);

        g.drawImage(kuva, x, y, null);
    }

    private void piirraTapahtumaAlueet(Graphics g) {
        g.setColor(new Color(28, 40, 126));

        for (TapahtumaAlue alue : tapahtumaAlueet) {
            g.fillRect(alue.x, alue.y, alue.width, alue.height);
        }
    }

    private void luoTapahtumaAlueet() {
        tapahtumaAlueet = new ArrayList<>();
        int x = tavoiterivistoX;
        int y = tavoiterivistoY;

        for (int i = 0; i < pelialusta.getTavoiterivisto().koko(); i++) {
            TapahtumaAlue alue = new TapahtumaAlue(x, y, KuvanAntaja.kortinLeveys, KuvanAntaja.kortinKorkeus, pelialusta.getTavoiterivisto().haePakka(i));
            tapahtumaAlueet.add(alue);

            x += KuvanAntaja.kortinLeveys + korttienValiRivistossa;
        }

        TapahtumaAlue alue = new TapahtumaAlue(korttipakkaX, korttipakkaY, KuvanAntaja.kortinLeveys, KuvanAntaja.kortinKorkeus, pelialusta.getKorttipakka());
        tapahtumaAlueet.add(alue);
    }
    
    private void lisaaKuuntelija() {
        HiirenKuuntelija kuuntelija = new HiirenKuuntelija(this, tapahtumaAlueet, pelialusta);
        this.addMouseListener(kuuntelija);
        this.addMouseMotionListener(kuuntelija);
    }
}
