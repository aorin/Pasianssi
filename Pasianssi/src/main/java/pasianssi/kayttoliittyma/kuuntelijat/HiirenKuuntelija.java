package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import pasianssi.kayttoliittyma.KorttienSijainninPaivittaja;
import pasianssi.kayttoliittyma.Piirtaja;
import pasianssi.kayttoliittyma.TapahtumaAlue;
import pasianssi.kayttoliittyma.TapahtumaAlueidenAntaja;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka tarkkailee mitÃ¤ komentoja kÃ¤yttÃ¤jÃ¤ antaa hiirelle ja ohjaa pelin
 * toimintaa sen mukaan.
 */
public class HiirenKuuntelija extends MouseInputAdapter {

    private Pelialusta pelilauta;
    private Piirtaja piirtaja;
    private KorttienSijainninPaivittaja paivittaja;
    private TapahtumaAlueidenAntaja tapahtumaAlueidenAntaja;
    private List<Kortti> siirrettavat;
    private int tarttumaKohtaX, tarttumaKohtaY;

    /**
     * Konstuktori asettaa luokalle piirtÃ¤jÃ¤n, pelilaudan ja sijainnin
     * pÃ¤ivittÃ¤jÃ¤n ja lisÃ¤ksi luo uuden tyhjÃ¤n listan edustamaan tÃ¤llÃ¤ hetkellÃ¤
     * siirrossa olevia kortteja.
     *
     * @param piirtaja PiirtÃ¤jÃ¤, joka piirtÃ¤Ã¤ korttien kuvat.
     * @param lauta Pelialusta.
     * @param paivittaja PÃ¤ivittÃ¤jÃ¤, joka pÃ¤ivittÃ¤Ã¤ korttien sijainnit.
     */
    public HiirenKuuntelija(Piirtaja piirtaja, Pelialusta lauta, KorttienSijainninPaivittaja paivittaja) {
        this.pelilauta = lauta;
        this.piirtaja = piirtaja;
        this.paivittaja = paivittaja;
        this.tapahtumaAlueidenAntaja = new TapahtumaAlueidenAntaja(lauta);
        this.siirrettavat = new ArrayList<>();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TapahtumaAlue alue = tapahtumaAlueidenAntaja.annaPainetunTapahtumaAlue(e.getX(), e.getY());

        if (alue != null && siirrettavat.isEmpty()) {
            siirrettavat = alue.alueenPaallaPainettu();

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
        TapahtumaAlue alue = tapahtumaAlueidenAntaja.annaIrtiPaastetynTapahtumaAlue(e.getX(), e.getY());

        if (alue != null && !siirrettavat.isEmpty()) {
            alue.alueenPaallaPaastettyIrti(siirrettavat);
        }

        if (!siirrettavat.isEmpty()) {
            for (Kortti kortti : siirrettavat) {
                paivittaja.paivitaKortinSijainti(kortti);
            }
        }

        siirrettavat.clear();
        piirtaja.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TapahtumaAlue alue = tapahtumaAlueidenAntaja.annaKlikatunTapahtumaAlue(e.getX(), e.getY());

        if (alue != null) {
            alue.alueeseenKlikattu();
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

                a += KorttienSijainninPaivittaja.korttienValiRivistossa;
            }
        }

        piirtaja.repaint();
    }
}
