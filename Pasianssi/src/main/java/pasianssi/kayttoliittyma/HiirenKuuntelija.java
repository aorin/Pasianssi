package pasianssi.kayttoliittyma;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka tarkkailee mitä komentoja käyttäjä antaa hiirelle ja ohjaa
 * pelin toimintaa sen mukaan.
 */
public class HiirenKuuntelija extends MouseInputAdapter {

    private Pelialusta pelilauta;
    private Component piirtaja;
    private List<TapahtumaAlue> tapahtumaAlueet;
    private Kortti siirrettava;
    private int tarttumaKohtaX, tarttumaKohtaY;

    public HiirenKuuntelija(Component piirtaja, List<TapahtumaAlue> tapahtumaAlueet, Pelialusta lauta) {
        this.pelilauta = lauta;
        this.piirtaja = piirtaja;
        this.siirrettava = null;
        this.tapahtumaAlueet = tapahtumaAlueet;
        this.tarttumaKohtaX = 0;
        this.tarttumaKohtaY = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TapahtumaAlue alue = kohdallaOlevaAlue(e);

        if (alue != null) {
            alue.alueeseenKlikattu(siirrettava, e.getX(), e.getY());
        }

        piirtaja.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    private TapahtumaAlue kohdallaOlevaAlue(MouseEvent e) {
        Rectangle kosketusAlue = new Rectangle(e.getX(), e.getY(), 1, 1);

        for (TapahtumaAlue alue : tapahtumaAlueet) {
            if (kosketusAlue.intersects(alue)) {
                return alue;
            }
        }

        return null;
    }
}
