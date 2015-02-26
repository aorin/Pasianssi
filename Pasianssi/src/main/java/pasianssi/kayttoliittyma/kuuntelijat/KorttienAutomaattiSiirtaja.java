package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pasianssi.kayttoliittyma.Kayttoliittyma;
import pasianssi.kayttoliittyma.Piirtaja;
import pasianssi.logiikka.AutomaattiSiirtaja;

public class KorttienAutomaattiSiirtaja implements ActionListener {

    private Kayttoliittyma kayttoliittyma;
    private AutomaattiSiirtaja siirtaja;

    public KorttienAutomaattiSiirtaja(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        siirtaja = new AutomaattiSiirtaja(kayttoliittyma.getPelialusta(), kayttoliittyma.getPaivittaja());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Piirtaja piirtaja = kayttoliittyma.getPiirtaja();

        if (!siirtaja.siirraKortti()) {
            return;
        }

        piirtaja.repaint();
    }
}
