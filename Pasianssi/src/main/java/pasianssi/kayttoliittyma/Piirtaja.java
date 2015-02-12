package pasianssi.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import pasianssi.logiikka.domain.*;

/**
 * Luokka tarjoaa toiminnallisuuden korttien kuvien piirtämiseen.
 */
public class Piirtaja extends JPanel {

    private KuvanAntaja kuvanAntaja;
    private Pelialusta pelialusta;
    private List<Kortti> siirrettavat;

    public Piirtaja(Pelialusta alusta) {
        super.setBackground(new Color(28, 63, 126));

        this.pelialusta = alusta;
        this.kuvanAntaja = new KuvanAntaja();
        this.siirrettavat = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Korttipakka pakka = pelialusta.getKorttipakka();
        Korttirivisto rivisto = pelialusta.getKorttirivisto();
        Korttirivisto tavoiterivisto = pelialusta.getTavoiterivisto();
        
        piirraKorttipakka(g, pakka);
        piirraKorttirivisto(g, rivisto);
        piirraKorttirivisto(g, tavoiterivisto);
        
        piirraListaKorteista(g, siirrettavat);
    }

    private void piirraKorttirivisto(Graphics g, Korttirivisto rivisto) {
        for (int i = 0; i < rivisto.koko(); i++) {
            piirraKorttipakka(g, rivisto.haePakka(i));
        }
    }

    private void piirraKorttipakka(Graphics g, Korttipakka pakka) {
        piirraTyhjaNelio(g, pakka.getX(), pakka.getY());
        
        for (int i = 0; i < pakka.koko(); i++) {
            piirraKortti(g, pakka.haeKortti(i));
        }
    }

    private void piirraKortti(Graphics g, Kortti kortti) {
        Image kuva = kuvanAntaja.annaKortilleKuva(kortti);

        g.drawImage(kuva, kortti.getX(), kortti.getY(), null);
    }
    
    private void piirraListaKorteista(Graphics g, List<Kortti> kortit) {
        for (Kortti kortti : kortit) {
            piirraKortti(g, kortti);
        }
    }

    private void piirraTyhjaNelio(Graphics g, int x, int y) {
        g.setColor(new Color(28, 40, 126));
        g.fillRect(x, y, KuvanAntaja.kortinLeveys, KuvanAntaja.kortinKorkeus);
    }

    public void setSiirrettavat(List<Kortti> siirrettavat) {
        this.siirrettavat = siirrettavat;
    }
}
