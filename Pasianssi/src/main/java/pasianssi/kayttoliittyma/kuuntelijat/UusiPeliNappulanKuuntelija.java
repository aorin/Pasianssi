package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pasianssi.kayttoliittyma.Kayttoliittyma;
import pasianssi.logiikka.Sovellus;

/**
 * Luokka ohjaa uuden pelin aloittamista.
 */
public class UusiPeliNappulanKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;

/**
 * Konstuktorissa oliolle asetetaan käyttöliittymä.
 * 
 * @param kayttoliittyma Käytössä oleva käyttöliittymä.
 */    
    public UusiPeliNappulanKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Sovellus sovellus = new Sovellus();
        sovellus.kaynnista();
        
        kayttoliittyma.paivita(sovellus.getPelialusta());
    }
}
