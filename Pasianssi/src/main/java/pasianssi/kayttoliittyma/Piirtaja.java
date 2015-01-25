package pasianssi.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import pasianssi.logiikka.domain.*;

public class Piirtaja extends JPanel {

    private Pelialusta pelialusta;
    private Image kuvaKorteista;
    private int kortinLeveys, kortinKorkeus;

    public Piirtaja(Pelialusta alusta) {
        super.setBackground(new Color(28, 63, 126));

        this.kortinLeveys = 121;
        this.kortinKorkeus = 172;
        
        this.pelialusta = alusta;
        
        try {
            File kuva = new File("./korttipohja.png");
            kuvaKorteista = ImageIO.read(kuva);
        } catch (Exception e) {
            System.out.println("Kuvatiedoston lukeminen ei onnistunut.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        piirraKorttirivisto(pelialusta.getKorttirivisto(), g, 10, 10);
                
                
    }
    
    private void piirraKorttirivisto(Korttirivisto rivisto, Graphics g, int x, int y) {
        for (int i = 0; i < 7; i++) {
            Korttirivi rivi = rivisto.haeRivi(i);
            piirraKorttirivi(rivi, g, x, y);
            x += kortinLeveys + 5;
        }
        
    }
    
    private void piirraKorttirivi(Korttirivi rivi, Graphics g, int x, int y) {
        for (Kortti kortti : rivi.listaKorteista()) {
            piirraKortti(kortti, g, x, y);
            y += 10;
        }
    }

    private void piirraKortti(Kortti kortti, Graphics g, int x, int y) {
        int yAlku;
        int xAlku;

        if (!kortti.oikeinPain()) {
            yAlku = 0;
            xAlku = kortinLeveys * 13;
        } else {
            yAlku = kortti.getMaa().getArvo() * kortinKorkeus;
            xAlku = (kortti.getArvo() - 1) * kortinLeveys;
        }

        g.drawImage(kuvaKorteista, x, y, kortinLeveys + x, kortinKorkeus + y, xAlku, yAlku, kortinLeveys + xAlku, kortinKorkeus + yAlku, null);
    }
    
    private void piirraTyhja(Graphics g, int x, int y) {
        g.setColor(new Color(28, 40, 126));
        
        g.fillRect(x, y, kortinLeveys, kortinKorkeus);
    }
}
