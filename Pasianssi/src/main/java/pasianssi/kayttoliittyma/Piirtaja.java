package pasianssi.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import pasianssi.logiikka.domain.*;

public class Piirtaja extends JPanel {

    private Pelialusta pelialusta;
    private List<Korttikuva> kuvatKorteista;
    private List<Rectangle> pohjaSuorakulmiot;

    public Piirtaja(Pelialusta alusta, List<Korttikuva> listaKuvista, List<Rectangle> pohjaSuorakulmiot) {
        super.setBackground(new Color(28, 63, 126));
        
        this.pelialusta = alusta;
        this.kuvatKorteista = listaKuvista;
        this.pohjaSuorakulmiot = pohjaSuorakulmiot;
    }
    

    

    
  

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Collections.sort(kuvatKorteista);
        
        for (Rectangle suorakulmio : pohjaSuorakulmiot) {
            piirraSuorakulmio(g, suorakulmio);
        }
        
        for (Korttikuva kuva : kuvatKorteista) {
            piirraKorttikuva(g, kuva);
        }
    }
    
    private void piirraKorttikuva(Graphics g, Korttikuva kuva) {
        g.drawImage(kuva.getKuva(), (int) kuva.getX(), (int) kuva.getY(), this);
    }
    
    private void piirraSuorakulmio(Graphics g, Rectangle suorakulmio) {
        g.setColor(new Color(28, 40, 126));
        
        g.fillRect(suorakulmio.x, suorakulmio.y, suorakulmio.width, suorakulmio.height);
    }
}
