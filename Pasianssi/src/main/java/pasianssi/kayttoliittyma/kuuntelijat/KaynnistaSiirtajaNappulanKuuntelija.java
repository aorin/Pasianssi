package pasianssi.kayttoliittyma.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pasianssi.kayttoliittyma.Kayttoliittyma;

/**
 * Luokka käynnistää korttien automaattisiirtäjän ja estää käyttäjää
 * siirtämästä kortteja.
 */
public class KaynnistaSiirtajaNappulanKuuntelija implements ActionListener {
    private Timer timer;
    private Kayttoliittyma kayttoliittyma;

/**
 * Konstruktori antaa oliolle käyttöliittymän ja ajastimen, joka vastaa
 * korttien automaattisiirrosta.
 * 
 * @param timer Käytössä oleva ajastin.
 * @param k Käytössä oleva käyttöliittymä.
 */    
    public KaynnistaSiirtajaNappulanKuuntelija(Timer timer, Kayttoliittyma k) {
        this.timer = timer;
        this.kayttoliittyma = k;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        kayttoliittyma.getPiirtaja().removeMouseListener(kayttoliittyma.getHiirenkuuntelija());
        timer.start();
    }
}
