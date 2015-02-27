package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pasianssi.kayttoliittyma.Kayttoliittyma;

/**
 * Luokka ohjaa ajastinta lopettamaan automaattinen korttiensiirtäminen ja
 * antaa käyttäjälle mahdollisuuden korttien siirtämiseen.
 */
public class PysaytaSiirtajaNappulanKuuntelija implements ActionListener {
    private Timer timer;
    private Kayttoliittyma kayttoliittyma;

/**
 * Konstuktori asettaa luokalle ajastimen ja käyttöiittymän.
 * 
 * @param timer Käytössä oleva ajastin.
 * @param k Käytössä oleva käyttöliittymä.
 */    
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
