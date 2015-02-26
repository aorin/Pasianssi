package pasianssi.kayttoliittyma;

import pasianssi.kayttoliittyma.kuuntelijat.HiirenKuuntelija;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import pasianssi.kayttoliittyma.kuuntelijat.KorttienAutomaattiSiirtaja;
import pasianssi.kayttoliittyma.kuuntelijat.KaynnistaSiirtajaNappulanKuuntelija;
import pasianssi.kayttoliittyma.kuuntelijat.PysaytaSiirtajaNappulanKuuntelija;
import pasianssi.kayttoliittyma.kuuntelijat.UusiPeliNappulanKuuntelija;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka ohjaa käyttöliittymän toimintaa.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Pelialusta pelialusta;
    private Piirtaja piirtaja;
    private KorttienSijainninPaivittaja paivittaja;

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
        frame.getContentPane().setPreferredSize(new Dimension(950, 680));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());
        luoValikko();

        frame.pack();
        frame.setVisible(true);
    }

    private void luoValikko() {
        JMenuBar valikko = new JMenuBar();
        frame.setJMenuBar(valikko);

        JMenuItem uusiPeli = new JMenuItem("Uusi peli");
        JMenuItem kaynnistaSiirtaja = new JMenuItem("Käynnistä automaattisiirtäjä");
        JMenuItem pysaytaSiirtaja = new JMenuItem("Pysäytä automaattisiirtäjä");

        valikko.add(uusiPeli);
        valikko.add(kaynnistaSiirtaja);
        valikko.add(pysaytaSiirtaja);

        UusiPeliNappulanKuuntelija kuuntelija = new UusiPeliNappulanKuuntelija(this);
        uusiPeli.addActionListener(kuuntelija);

        KorttienAutomaattiSiirtaja siirtaja = new KorttienAutomaattiSiirtaja(this);
        Timer timer = new Timer(1000, siirtaja);

        KaynnistaSiirtajaNappulanKuuntelija kaynnistaSiirtajaKuuntelija = new KaynnistaSiirtajaNappulanKuuntelija(timer);
        kaynnistaSiirtaja.addActionListener(kaynnistaSiirtajaKuuntelija);
        
        PysaytaSiirtajaNappulanKuuntelija pysaytaSiirtajaKuuntelija = new PysaytaSiirtajaNappulanKuuntelija(timer);
        pysaytaSiirtaja.addActionListener(pysaytaSiirtajaKuuntelija);
    }

    private void luoKomponentit(Container container) {
        piirtaja = new Piirtaja(pelialusta);
        paivittaja = new KorttienSijainninPaivittaja(pelialusta);

        paivittaja.asetaKaikkienKorttienSijainti();
        lisaaHiirenKuuntelija();

        container.add(piirtaja);
    }

    private void lisaaHiirenKuuntelija() {
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
        
        JMenuBar menu = frame.getJMenuBar();
        menu.removeAll();
        luoValikko();

        frame.setVisible(true);
    }

    public Pelialusta getPelialusta() {
        return pelialusta;
    }

    public Piirtaja getPiirtaja() {
        return piirtaja;
    }

    public KorttienSijainninPaivittaja getPaivittaja() {
        return paivittaja;
    }
}
