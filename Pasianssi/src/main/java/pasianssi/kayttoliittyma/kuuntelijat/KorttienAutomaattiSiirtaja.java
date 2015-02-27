package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pasianssi.kayttoliittyma.Kayttoliittyma;
import pasianssi.kayttoliittyma.Piirtaja;
import pasianssi.logiikka.domain.Pelialusta;
import pasianssi.logiikka.util.AutomaattiSiirtaja;

public class KorttienAutomaattiSiirtaja implements ActionListener {

    private Kayttoliittyma kayttoliittyma;
    private Pelialusta alusta;
    private AutomaattiSiirtaja siirtaja;
    private KaynnistaSiirtajaNappulanKuuntelija ajastaja;

    public KorttienAutomaattiSiirtaja(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        this.alusta = kayttoliittyma.getPelialusta();
        this.siirtaja = new AutomaattiSiirtaja(alusta, kayttoliittyma.getPaivittaja());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Piirtaja piirtaja = kayttoliittyma.getPiirtaja();

        if (!siirtaja.siirraKortti()) {
            kayttoliittyma.naytaAutomaattiEiOsaaSiirtaaIkkuna();
            return;
        }
        
        if (alusta.pakassaTaiRivistossaEiKortteja()) {
            kayttoliittyma.naytaVoittoIkkuna();
        }

        piirtaja.repaint();
    }
    
    public void lisaaAjastaja(KaynnistaSiirtajaNappulanKuuntelija ajastaja) {
        this.ajastaja = ajastaja;
    }
}
