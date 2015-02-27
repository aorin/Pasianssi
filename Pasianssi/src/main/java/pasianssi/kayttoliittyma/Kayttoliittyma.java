package pasianssi.kayttoliittyma;

import pasianssi.kayttoliittyma.kuuntelijat.HiirenKuuntelija;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import pasianssi.kayttoliittyma.kuuntelijat.*;
import pasianssi.logiikka.domain.Pelialusta;

/**
 * Luokka luo käyttöliittymäikkunan ja ohjaa sen toimintaa.
 */
public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Pelialusta pelialusta;
    private Piirtaja piirtaja;
    private KorttienSijainninPaivittaja paivittaja;
    private HiirenKuuntelija hiirenkuuntelija;
    private Timer timer;

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
        frame.setResizable(false);
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
        timer = new Timer(300, siirtaja);

        KaynnistaSiirtajaNappulanKuuntelija kaynnistaSiirtajaKuuntelija = new KaynnistaSiirtajaNappulanKuuntelija(timer, this);
        kaynnistaSiirtaja.addActionListener(kaynnistaSiirtajaKuuntelija);
        
        PysaytaSiirtajaNappulanKuuntelija pysaytaSiirtajaKuuntelija = new PysaytaSiirtajaNappulanKuuntelija(timer, this);
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
        hiirenkuuntelija = new HiirenKuuntelija(this);
        piirtaja.addMouseListener(hiirenkuuntelija);
        piirtaja.addMouseMotionListener(hiirenkuuntelija);
    }

    public JFrame getFrame() {
        return frame;
    }

/**
 * Metodi päivittää käyttöliittymän vastaamaan metodina annettua
 * pelialustaa.
 * 
 * @param alusta Uusi käytössä oleva pelialusta. 
 */    
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
    
/**
 * Metodi avaa uuden ikkunan, jossa ilmoitetaan pelin voitosta.
 */    
    public void naytaVoittoIkkuna() {
        timer.stop();
        TekstiIkkuna ikkuna = new TekstiIkkuna("     Voitit pelin! \\(^o^)/");
        piirtaja.addMouseListener(hiirenkuuntelija);
        SwingUtilities.invokeLater(ikkuna);
    }

/**
 * Metodi avaa uuden ikkunan, jossa ilmoitetaan, että automaattinen
 * siirtäjä ei pysty enää siirtämään mitään korttia.
 */
    public void naytaAutomaattiEiOsaaSiirtaaIkkuna() {
        timer.stop();
        TekstiIkkuna ikkuna = new TekstiIkkuna("      Siirtäjä ei osaa siirtää enää mitään korttia. :(");
        piirtaja.addMouseListener(hiirenkuuntelija);
        SwingUtilities.invokeLater(ikkuna);
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

    public HiirenKuuntelija getHiirenkuuntelija() {
        return hiirenkuuntelija;
    }
}
