package pasianssi.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka ohjaa käyttöliittymän toimintaa.
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Pelialusta pelialusta;

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
        frame.setPreferredSize(new Dimension(950, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        Piirtaja piirtoalusta = new Piirtaja(pelialusta);
        
        KorttienSijainninPaivittaja paivittaja = new KorttienSijainninPaivittaja(pelialusta);
        paivittaja.asetaKaikkienKorttienSijainti();
        lisaaKuuntelija(paivittaja, piirtoalusta);
 
        container.add(piirtoalusta);
    }
    
        
    private void lisaaKuuntelija(KorttienSijainninPaivittaja paivittaja, Piirtaja piirtaja) {
        HiirenKuuntelija kuuntelija = new HiirenKuuntelija(piirtaja, pelialusta, paivittaja);
        piirtaja.addMouseListener(kuuntelija);
        piirtaja.addMouseMotionListener(kuuntelija);
    }

    public JFrame getFrame() {
        return frame;
    }
}
