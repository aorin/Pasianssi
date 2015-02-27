package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import pasianssi.kayttoliittyma.Kayttoliittyma;
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
    private Kayttoliittyma kayttoliittyma;
    private Pelialusta pelilauta;
    private Piirtaja piirtaja;
    private KorttienSijainninPaivittaja paivittaja;
    private TapahtumaAlueidenAntaja tapahtumaAlueidenAntaja;
    private List<Kortti> siirrettavat;
    private int tarttumaKohtaX, tarttumaKohtaY;

    /**
     * Konstruktori asettaa kuuntelijalle annetun käyttöliittymän perusteella
     * pelilaudan, piirtäjän ja sijainnin päivittäjän.
     * <p>
     * Lisäksi konstruktori luo uuden tapahtumaAlueidenPaivittaja-olion ja
     * luo tyhjän listan siirrossa oleville korteille.
     * 
     * @param kayttoliittyma Käytössä oleva käyttöliittymä.
     */
    public HiirenKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        this.pelilauta = kayttoliittyma.getPelialusta();
        this.piirtaja = kayttoliittyma.getPiirtaja();
        this.paivittaja = kayttoliittyma.getPaivittaja();
        this.tapahtumaAlueidenAntaja = new TapahtumaAlueidenAntaja(pelilauta);
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
            
            if (pelilauta.pakassaTaiRivistossaEiKortteja()) {
                kayttoliittyma.naytaVoittoIkkuna();
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
