package pasianssi.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import pasianssi.kayttoliittyma.kuuntelijat.OknapinKuuntelija;

public class TekstiIkkuna implements Runnable {
    private JFrame frame;
    private String teksti;

    public TekstiIkkuna(String teksti) {
        this.teksti = teksti;
    }
    
    @Override
    public void run() {
        frame = new JFrame();
        frame.getContentPane().setPreferredSize(new Dimension(400, 100));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container c) {
        JLabel voittoteksti = new JLabel(teksti);
        JButton okNappi = new JButton("Ok");
        
        OknapinKuuntelija kuuntelija = new OknapinKuuntelija(frame);
        okNappi.addActionListener(kuuntelija);
        
        GridLayout layout = new GridLayout(2, 1);
        c.setLayout(layout);
        c.add(voittoteksti);
        c.add(okNappi);
    }
}
