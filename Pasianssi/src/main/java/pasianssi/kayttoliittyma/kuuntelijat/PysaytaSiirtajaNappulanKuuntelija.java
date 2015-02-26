package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class PysaytaSiirtajaNappulanKuuntelija implements ActionListener {
    private Timer timer;

    public PysaytaSiirtajaNappulanKuuntelija(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.stop();
    }
}
