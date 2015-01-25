package pasianssi.kayttoliittyma;

import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class HiirenKuuntelija extends MouseInputAdapter {
    private Component piirtaja;
    
    @Override
    public void mouseClicked(MouseEvent me) {
        super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        super.mouseDragged(me); //To change body of generated methods, choose Tools | Templates.
    }
}
