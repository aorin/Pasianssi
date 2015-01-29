package pasianssi.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import pasianssi.logiikka.domain.Pelialusta;

public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Pelialusta pelialusta;

    public Kayttoliittyma(Pelialusta alusta) {
        this.pelialusta = alusta;
    }

    @Override
    public void run() {
        frame = new JFrame("Pasianssi");
        frame.setPreferredSize(new Dimension(1000, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        Piirtaja piirtoalusta = new Piirtaja(pelialusta);
        container.add(piirtoalusta);
    }

    public JFrame getFrame() {
        return frame;
    }
}
