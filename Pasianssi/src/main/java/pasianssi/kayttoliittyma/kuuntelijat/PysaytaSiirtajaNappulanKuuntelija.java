package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pasianssi.kayttoliittyma.Kayttoliittyma;

public class PysaytaSiirtajaNappulanKuuntelija implements ActionListener {
    private Timer timer;
    private Kayttoliittyma kayttoliittyma;

    public PysaytaSiirtajaNappulanKuuntelija(Timer timer, Kayttoliittyma k) {
        this.timer = timer;
        this.kayttoliittyma = k;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.stop();
        kayttoliittyma.getPiirtaja().addMouseListener(kayttoliittyma.getHiirenkuuntelija());
    }
}
