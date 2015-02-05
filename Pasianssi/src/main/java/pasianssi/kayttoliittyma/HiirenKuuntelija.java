package pasianssi.kayttoliittyma;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import pasianssi.logiikka.domain.Pelialusta;

public class HiirenKuuntelija extends MouseInputAdapter {

    private Pelialusta pelilauta;
    private Component piirtaja;
    private KuvienSijainninPaivittaja sijainninPaivittaja;
    private Korttikuva siirrettava;
    private List<Korttikuva> listaKorttikuvista;
    private int tarttumaKohtaX, tarttumaKohtaY;
    private int lahtoX, lahtoY;

    public HiirenKuuntelija(Component piirtaja, List<Korttikuva> lista, Pelialusta lauta, KuvienSijainninPaivittaja paivittaja) {
        this.pelilauta = lauta;
        this.piirtaja = piirtaja;
        this.sijainninPaivittaja = paivittaja;
        this.siirrettava = null;
        this.listaKorttikuvista = lista;
        this.tarttumaKohtaX = 0;
        this.tarttumaKohtaY = 0;
        this.lahtoX = 0;
        this.lahtoY = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        siirrettava = klikattuKuva(e);

        if (siirrettava != null) {
            siirrettava.setSiirrettavana(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (siirrettava == null) {
            return;
        }

        Korttikuva kuva = klikattuKuva(e);

        if (kuva != null && kuva.getKortti().getSijainti().lisaaKorttiEhdolla(siirrettava.getKortti())) {
            sijainninPaivittaja.paivitaSijainti(siirrettava);
        } else {
            siirrettava.x = lahtoX;
            siirrettava.y = lahtoY;

        }

        siirrettava.setSiirrettavana(false);

        siirrettava = null;
        piirtaja.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Korttikuva klikattu = klikattuKuva(e);
        if (klikattu != null && !klikattu.getKortti().oikeinPain()) {
            //kaanna kortti
        }

        piirtaja.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (siirrettava != null) {
            siirrettava.x = me.getX() - tarttumaKohtaX;
            siirrettava.y = me.getY() - tarttumaKohtaY;
            piirtaja.repaint();
        }
    }

    private Korttikuva klikattuKuva(MouseEvent e) {
        Rectangle kosketusAlue = new Rectangle(e.getX(), e.getY(), 1, 1);

        for (int i = listaKorttikuvista.size() - 1; i >= 0; i--) {
            Korttikuva korttikuva = listaKorttikuvista.get(i);

            if (siirrettava == korttikuva) {
                continue;
            }

            if (kosketusAlue.intersects(korttikuva)) {
                if (siirrettava == null) {
                    siirrettava = korttikuva;

                    int x = korttikuva.x;
                    int y = korttikuva.y;

                    tarttumaKohtaX = e.getX() - x;
                    tarttumaKohtaY = e.getY() - y;

                    lahtoX = x;
                    lahtoY = y;
                }

                return korttikuva;
            }

        }
        return null;
    }
}
