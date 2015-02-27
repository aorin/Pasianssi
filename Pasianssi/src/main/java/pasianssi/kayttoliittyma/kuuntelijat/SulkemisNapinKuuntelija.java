package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * Luokka sulkee halutun käyttöliittymä-ikkunan.
 */
public class SulkemisNapinKuuntelija implements ActionListener {
    private JFrame frame;
    
/**
 * Konstruktori asettaa oliolle ikkunan, joka
 * on tarkoitus sulkea.
 * 
 * @param frame Käyttöliittymä-ikkuna.
 */    
    public SulkemisNapinKuuntelija(JFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        frame.dispose();
    }
}
