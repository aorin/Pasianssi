package pasianssi.kayttoliittyma;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pasianssi.logiikka.domain.Kortti;

/**
 * Luokka tarjoaa toiminnallisuuden kuvan hakemiseen kortin perusteella.
 */
public class KuvanAntaja {
    public static final int kortinLeveys = 121;
    public static final int kortinKorkeus = 172;
    private final BufferedImage kuvapohja;

    public KuvanAntaja() {
        kuvapohja = lueKuvatiedosto();
    }
    
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
            File kuva = new File("./korttipohja.png");
            return ImageIO.read(kuva);
        } catch (IOException e) {
            System.out.println("Kuvatiedoston lukeminen ei onnistunut.");
            return null;
        }
    }
}
