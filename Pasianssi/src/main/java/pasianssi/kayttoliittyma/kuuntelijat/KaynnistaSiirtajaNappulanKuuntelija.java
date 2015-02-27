package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pasianssi.kayttoliittyma.Kayttoliittyma;

public class KaynnistaSiirtajaNappulanKuuntelija implements ActionListener {

    private Timer timer;
    private Kayttoliittyma kayttoliittyma;

    public KaynnistaSiirtajaNappulanKuuntelija(Timer timer, Kayttoliittyma k) {
        this.timer = timer;
        this.kayttoliittyma = k;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        kayttoliittyma.getPiirtaja().removeMouseListener(kayttoliittyma.getHiirenkuuntelija());
        timer.start();
    }
    
    public void siirtajaPysahtyi() {
        timer.stop();
        kayttoliittyma.naytaAutomaattiEiOsaaSiirtaaIkkuna();
    }
}
