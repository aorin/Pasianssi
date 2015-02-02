package pasianssi.kayttoliittyma;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import pasianssi.logiikka.domain.Pelialusta;

public class HiirenKuuntelija extends MouseInputAdapter {

    private Pelialusta pelilauta;
    private Component piirtaja;
    private Korttikuva siirrettava;
    private List<Korttikuva> listaKorttikuvista;
    private int tarttumaKohtaX, tarttumaKohtaY;

    public HiirenKuuntelija(Component piirtaja, List<Korttikuva> lista, Pelialusta lauta) {
        this.pelilauta = lauta;
        this.piirtaja = piirtaja;
        this.siirrettava = null;
        this.listaKorttikuvista = lista;
        this.tarttumaKohtaX = 0;
        this.tarttumaKohtaY = 0;
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
        if (siirrettava != null) {
            siirrettava.setSiirrettavana(false);
        }

        Korttikuva kuva = klikattuKuva(e);
        int x = e.getX();

        if (kuva != null && kuva.getKortti().oikeinPain()) {
            //lisaa riviin, jos onnistuu
        }

        siirrettava = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //korttien kaantaminen?
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (siirrettava != null) {
            siirrettava.setX(me.getX() - tarttumaKohtaX);
            siirrettava.setY(me.getY() - tarttumaKohtaY);
            piirtaja.repaint();
        }
    }

    private Korttikuva klikattuKuva(MouseEvent e) {
        for (int i = listaKorttikuvista.size() - 1; i >= 0; i--) {
            Korttikuva korttikuva = listaKorttikuvista.get(i);

            if (!korttikuva.getKortti().oikeinPain()) {
                continue;
            }

            if (e.getX() >= korttikuva.getX() && e.getX() <= korttikuva.getX() + 121) {
                if (e.getY() >= korttikuva.getY() && e.getY() <= korttikuva.getY() + 172) {
                    tarttumaKohtaX = e.getX() - korttikuva.getX();
                    tarttumaKohtaY = e.getY() - korttikuva.getY();
                    return korttikuva;
                }
            }
        }
        return null;
    }
}
