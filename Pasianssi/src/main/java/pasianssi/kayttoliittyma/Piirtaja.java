package pasianssi.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import pasianssi.logiikka.domain.*;

public class Piirtaja extends JPanel {

    private Pelialusta pelialusta;
    private List<KorttiKuva> kuvatKorteista;
    private int kortinLeveys, kortinKorkeus;
    private BufferedImage kuvaPohja;

    public Piirtaja(Pelialusta alusta) {
        super.setBackground(new Color(28, 63, 126));

        this.kortinLeveys = 121;
        this.kortinKorkeus = 172;
        
        this.pelialusta = alusta;
        this.kuvatKorteista = new ArrayList<>();
        
        try {
            File kuva = new File("./korttipohja.png");
            kuvaPohja = ImageIO.read(kuva);
        } catch (IOException e) {
            System.out.println("Kuvatiedoston lukeminen ei onnistunut.");
        }
        
        luoKaikkienKorttienKuvat();
    }
    
    private void luoKaikkienKorttienKuvat() {
        luoKorttirivistonKuvat(pelialusta.getKorttirivisto(), 10, 10);
        
        luoKorttirivistonKuvat(pelialusta.getTavoiterivisto(), 400, 400);
        
        luoKorttipakanKuvat(pelialusta.getKorttipakka(), 10, 400);
    }
    
    private void luoKorttirivistonKuvat(Korttirivisto rivisto, int x, int y) {
        for (int i = 0; i < rivisto.koko(); i++) {
            luoKorttipakanKuvat(rivisto.haeRivi(i), x, y);
            x += kortinLeveys + 10;
        }
    }
    
    private void luoKorttipakanKuvat(Korttipakka pakka, int x, int y) {
        for (Kortti kortti : pakka.listaKorteista()) {
            luoKuva(kortti, x, y);
            
            if (pakka instanceof KorttipakkaVuoroVareinJaJarjestyksessa) {
                y += 10;
            }
        }
    }
    
    private void luoKuva(Kortti kortti, int xSijainti, int ySijainti) {
        int y;
        int x;

        if (!kortti.oikeinPain()) {
            y = 0;
            x = kortinLeveys * 13;
        } else {
            y = kortti.getMaa().getArvo() * kortinKorkeus;
            x = (kortti.getArvo() - 1) * kortinLeveys;
        }
        
        Image kuva = kuvaPohja.getSubimage(x, y, kortinLeveys, kortinKorkeus);
        
        this.kuvatKorteista.add(new KorttiKuva(kuva, xSijainti, ySijainti, kortti));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (KorttiKuva kuva : kuvatKorteista) {
            piirraKuva(g, kuva);
        }
    }
    
    private void piirraKuva(Graphics g, KorttiKuva kuva) {
        g.drawImage(kuva.getKuva(), kuva.getX(), kuva.getY(), this);
    }
    
    private void piirraTyhja(Graphics g, int x, int y) {
        g.setColor(new Color(28, 40, 126));
        
        g.fillRect(x, y, kortinLeveys, kortinKorkeus);
    }
}
