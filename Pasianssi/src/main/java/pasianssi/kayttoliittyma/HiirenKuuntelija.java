package pasianssi.kayttoliittyma;

import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class HiirenKuuntelija extends MouseInputAdapter {

    private Component piirtaja;
    private boolean siirrettava;
    private KorttiKuva kortti;

    public HiirenKuuntelija(Component piirtaja, KorttiKuva kortti) {
        this.piirtaja = piirtaja;
        this.siirrettava = false;
        this.kortti = kortti;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
