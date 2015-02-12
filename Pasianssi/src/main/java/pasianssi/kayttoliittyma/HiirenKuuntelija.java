package pasianssi.kayttoliittyma;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka tarkkailee mitä komentoja käyttäjä antaa hiirelle ja ohjaa pelin
 * toimintaa sen mukaan.
 */
public class HiirenKuuntelija extends MouseInputAdapter {
    private Pelialusta pelilauta;
    private Piirtaja piirtaja;
    private SijainninPaivittaja paivittaja;
    private List<TapahtumaAlue> tapahtumaAlueet;
    private List<Kortti> siirrettavat;
    private int tarttumaKohtaX, tarttumaKohtaY;

/**
 * Konstukrodi asettaa luokalle piirtäjän, pelilaudan ja sijainnin päivittäjän
 * ja lisäksi luo uuden tyhjän listan edustamaan tällä hetkellä siirrossa
 * olevia kortteja.
 * 
 * @param piirtaja Piirtäjä, joka piirtää korttien kuvat.
 * @param lauta Pelialusta.
 * @param paivittaja Päivittäjä, joka päivittää korttien sijainnit ja
 * antaa oikeat tapahtuma-alueet.
 */
    public HiirenKuuntelija(Piirtaja piirtaja, Pelialusta lauta, SijainninPaivittaja paivittaja) {
        this.pelilauta = lauta;
        this.piirtaja = piirtaja;
        this.paivittaja = paivittaja;
        this.siirrettavat = new ArrayList<>();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        tapahtumaAlueet = paivittaja.annaPainetunTapahtumaAlueet();
        TapahtumaAlue alue = osuttuTapahtumaAlue(e);

        if (alue != null && siirrettavat.isEmpty()) {
            siirrettavat = alue.alueenPaallaPainettu(e.getX(), e.getY());
            
            if (siirrettavat.isEmpty()) {
                return;
            }
            
            Kortti kortti = siirrettavat.get(0);
            
            tarttumaKohtaX = kortti.getX() - e.getX();
            tarttumaKohtaY = kortti.getY() - e.getY();

            piirtaja.setSiirrettavat(siirrettavat);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tapahtumaAlueet = paivittaja.annaIrtiPaastetynTapahtumaAlueet();
        TapahtumaAlue alue = osuttuTapahtumaAlue(e);

        if (alue != null && !siirrettavat.isEmpty()) {
            alue.alueenPaallaPaastettyIrti(siirrettavat, e.getX(), e.getY());
        }

        if (!siirrettavat.isEmpty()) {
            for (Kortti kortti : siirrettavat) {
                paivittaja.paivitaKortinSijainti(kortti.getSijainti(), kortti);
            }
        }

        siirrettavat.clear();
        piirtaja.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        tapahtumaAlueet = paivittaja.annaKlikatunTapahtumaAlueet();
        TapahtumaAlue alue = osuttuTapahtumaAlue(e);

        if (alue != null) {
            alue.alueeseenKlikattu(e.getX(), e.getY());
        }

        piirtaja.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (!siirrettavat.isEmpty()) {
            int a = 0;
            for (Kortti kortti : siirrettavat) {
                kortti.setX(me.getX() + tarttumaKohtaX);
                kortti.setY(me.getY() + tarttumaKohtaY + a);

                a += SijainninPaivittaja.korttienValiRivistossa;
            }
        }

        piirtaja.repaint();
    }

    private TapahtumaAlue osuttuTapahtumaAlue(MouseEvent e) {
        Rectangle kosketusAlue = new Rectangle(e.getX(), e.getY(), 1, 1);

        for (int i = tapahtumaAlueet.size() - 1; i >= 0; i--) {
            TapahtumaAlue alue = tapahtumaAlueet.get(i);
            if (kosketusAlue.intersects(alue)) {
                return alue;
            }
        }
        return null;
    }
}
