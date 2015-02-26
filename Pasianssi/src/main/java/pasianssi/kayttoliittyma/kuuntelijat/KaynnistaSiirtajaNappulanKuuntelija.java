package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pasianssi.kayttoliittyma.Kayttoliittyma;

public class KaynnistaSiirtajaNappulanKuuntelija implements ActionListener {

    private Timer timer;

    public KaynnistaSiirtajaNappulanKuuntelija(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.start();
    }
}
