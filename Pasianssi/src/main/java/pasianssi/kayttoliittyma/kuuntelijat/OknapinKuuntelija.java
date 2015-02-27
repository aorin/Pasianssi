package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class OknapinKuuntelija implements ActionListener {
    private JFrame frame;
    
    public OknapinKuuntelija(JFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        frame.dispose();
    }
}
