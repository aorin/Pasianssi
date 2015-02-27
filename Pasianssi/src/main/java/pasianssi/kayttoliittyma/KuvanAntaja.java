package pasianssi.kayttoliittyma;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import pasianssi.logiikka.domain.Kortti;

/**
 * Luokka tarjoaa toiminnallisuuden kuvan hakemiseen kortin perusteella.
 */
public class KuvanAntaja {
/**
 * {@value #kortinLeveys} Muuttuja kertoo korttien kuvien leveyden.
 */
    public static final int kortinLeveys = 121;
/**
 * {@value #kortinKorkeus} Muuttuja kertoo korttien kuvien korkeuden.
 */    
    public static final int kortinKorkeus = 172;
    private final BufferedImage kuvapohja;

/**
 * Konstruktori lukee kuvatiedoston Image-tyyppiseen muuttujaansa. 
 */    
    public KuvanAntaja() {
        kuvapohja = lueKuvatiedosto();
    }
 
/**
 * Palauttaa parametrina annettua korttia vastaavan kuvan.
 * @param kortti Kortti, jonka kuvaa haetaan.
 * @return Kortin kuva.
 */    
    public Image annaKortilleKuva(Kortti kortti) {
        if (!kortti.oikeinPain()) {
            return kuvapohja.getSubimage(kortinLeveys * 13, 0, kortinLeveys, kortinKorkeus);
        }
        
        int y = kortti.getMaa().getArvo() * kortinKorkeus;
        int x = (kortti.getArvo() - 1) * kortinLeveys;
        
        return kuvapohja.getSubimage(x, y, kortinLeveys, kortinKorkeus);
    }

    private BufferedImage lueKuvatiedosto() {
        try {
            InputStream kuva = this.getClass().getClassLoader().getResourceAsStream("kuvat/korttipohja.png");
            return ImageIO.read(kuva);
        } catch (IOException e) {
            System.out.println("Kuvatiedoston lukeminen ei onnistunut.");
            return null;
        }
    }
}
