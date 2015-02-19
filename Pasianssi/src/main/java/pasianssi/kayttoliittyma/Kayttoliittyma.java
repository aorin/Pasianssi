package pasianssi.kayttoliittyma;

import pasianssi.kayttoliittyma.kuuntelijat.HiirenKuuntelija;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import pasianssi.kayttoliittyma.kuuntelijat.UusiPeliNappulanKuuntelija;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka ohjaa käyttöliittymän toimintaa.
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Pelialusta pelialusta;
    private Piirtaja piirtaja;

/**
 * Konstruktori asettaa käyttöliittymälle pelialustan.
 * 
 * @param alusta Käytössä oleva pelialusta.
 */
    public Kayttoliittyma(Pelialusta alusta) {
        this.pelialusta = alusta;
    }

    @Override
    public void run() {
        frame = new JFrame("Pasianssi");
        frame.setPreferredSize(new Dimension(950, 730));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoValikko();
        luoKomponentit(frame.getContentPane());

        frame.pack();
        
        frame.setVisible(true);
    }
    
    private void luoValikko() {
        JMenuBar valikko = new JMenuBar();
        frame.setJMenuBar(valikko);
        JMenuItem uusiPeli = new JMenuItem("Uusi peli");
        valikko.add(uusiPeli);
        
        UusiPeliNappulanKuuntelija kuuntelija = new UusiPeliNappulanKuuntelija(this);
        uusiPeli.addActionListener(kuuntelija);
    }

    private void luoKomponentit(Container container) {
        Piirtaja piirtoalusta = new Piirtaja(pelialusta);
        
        KorttienSijainninPaivittaja paivittaja = new KorttienSijainninPaivittaja(pelialusta);
        paivittaja.asetaKaikkienKorttienSijainti();
        lisaaHiirenKuuntelija(paivittaja, piirtoalusta);
 
        container.add(piirtoalusta);
    }
    
        
    private void lisaaHiirenKuuntelija(KorttienSijainninPaivittaja paivittaja, Piirtaja piirtaja) {
        HiirenKuuntelija kuuntelija = new HiirenKuuntelija(piirtaja, pelialusta, paivittaja);
        piirtaja.addMouseListener(kuuntelija);
        piirtaja.addMouseMotionListener(kuuntelija);
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public void paivita(Pelialusta alusta) {
        this.pelialusta = alusta;
        Container c = frame.getContentPane();
        c.removeAll();
        luoKomponentit(c);
        frame.setVisible(true);
    }
}
