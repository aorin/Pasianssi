package pasianssi.kayttoliittyma;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.Korttirivisto;
import pasianssi.logiikka.domain.Pelialusta;

public class KuvienLuoja {
    public static final int kortinLeveys = 121, kortinKorkeus = 172;
    private Pelialusta pelialusta;
    private BufferedImage kuvapohja;
    private List<Korttikuva> kuvat;
    private KuvienSijainninPaivittaja paivittaja;

    public KuvienLuoja(Pelialusta alusta, KuvienSijainninPaivittaja paivittaja) {
        this.pelialusta = alusta;
        this.paivittaja = paivittaja;

        kuvapohja = lueKuvatiedosto();
        
        this.kuvat = new ArrayList<>();
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
    
    public List<Korttikuva> luoKaikkiKorttikuvat() {
        luoKorttirivistonKuvat(pelialusta.getKorttirivisto());
        
        luoKorttirivistonKuvat(pelialusta.getTavoiterivisto());
        
        luoKorttipakanKuvat(pelialusta.getKorttipakka());
        
        return kuvat;
    }
    
    private void luoKorttirivistonKuvat(Korttirivisto rivisto) {
        for (int i = 0; i < rivisto.koko(); i++) {
            luoKorttipakanKuvat(rivisto.haePakka(i));
        }
    }
    
    private void luoKorttipakanKuvat(Korttipakka pakka) {
        for (Kortti kortti : pakka.listaKorteista()) {
            luoKuva(kortti);
        }
    }
    
    private void luoKuva(Kortti kortti) {
        Image vaarinpainKuva = kuvapohja.getSubimage(kortinLeveys * 13, 0, kortinLeveys, kortinKorkeus);

        int y = kortti.getMaa().getArvo() * kortinKorkeus;
        int x = (kortti.getArvo() - 1) * kortinLeveys;
        
        Image oikeinpainKuva = kuvapohja.getSubimage(x, y, kortinLeveys, kortinKorkeus);
        
        Korttikuva uusiKuva = new Korttikuva(0, 0, kortinLeveys, kortinKorkeus, oikeinpainKuva, vaarinpainKuva, kortti);
        this.kuvat.add(uusiKuva);
        paivittaja.paivitaSijainti(uusiKuva);
    }
    
    public List<Rectangle> luoSuorakulmiot() {
        List<Rectangle> tyhjatKuvat = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            Rectangle kuva = new Rectangle(kortinLeveys, kortinKorkeus);
            tyhjatKuvat.add(kuva);
        }
        
        paivittaja.paivitaPohjaSuorakulmienSijainnit(tyhjatKuvat);
        
        return tyhjatKuvat;
    }
}
