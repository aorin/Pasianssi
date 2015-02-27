package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import pasianssi.kayttoliittyma.Kayttoliittyma;
import pasianssi.kayttoliittyma.KorttienSijainninPaivittaja;
import pasianssi.kayttoliittyma.Piirtaja;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Pelialusta;
import pasianssi.logiikka.util.AutomaattiSiirtaja;

/**
 * Luokka ohjaa automaattisiirtäjää siirtämään kortteja niin, että
 * siirrot näkyvät myös käyttöliittymässä.
 */
public class KorttienAutomaattiSiirtaja implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private Pelialusta alusta;
    private KorttienSijainninPaivittaja paivittaja;
    private AutomaattiSiirtaja siirtaja;

/**
 * Konstruktori lisää oliolle käyttöliittymän, pelialustan, sijainnin päivittäjän
 * ja luo uuden automaattisiirtäjän.
 * 
 * @param kayttoliittyma Käytössä oleva käyttöliittymä. 
 */    
    public KorttienAutomaattiSiirtaja(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        this.alusta = kayttoliittyma.getPelialusta();
        this.siirtaja = new AutomaattiSiirtaja(alusta);
        this.paivittaja = kayttoliittyma.getPaivittaja();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Piirtaja piirtaja = kayttoliittyma.getPiirtaja();
        List<Kortti> siirretytKortit = siirtaja.siirraKortti();

        if (siirretytKortit.isEmpty()) {
            kayttoliittyma.naytaAutomaattiEiOsaaSiirtaaIkkuna();
            return;
        }
        
        for (Kortti kortti : siirretytKortit) {
            paivittaja.paivitaKortinSijainti(kortti);
        }
        
        piirtaja.repaint();
        
        if (alusta.pakassaTaiRivistossaEiKortteja()) {
            kayttoliittyma.naytaVoittoIkkuna();
        }
    }
}
