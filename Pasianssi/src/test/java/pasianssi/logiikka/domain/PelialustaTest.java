package pasianssi.logiikka.domain;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pasianssi.logiikka.util.Korttienjakaja;

public class PelialustaTest {

    private Pelialusta pelialusta;
    private KorttipakkaVuoroVareinJaJarjestyksessa korttirivi;

    @Before
    public void setUp() {
        pelialusta = new Pelialusta();
        Korttienjakaja jakaja = new Korttienjakaja(pelialusta);
        jakaja.jaaKortit();
    }

    @Test
    public void palauttaaKaikki12Pakkaa() {
        List<Korttipakka> pakat = pelialusta.kaikkiKorttipakat();

        assertEquals(12, pakat.size());
    }

    @Test
    public void palauttaaFalseJosPakassaTaiRivistossaOnKortteja1() {
        assertTrue(!pelialusta.pakassaTaiRivistossaEiKortteja());
    }

    @Test
    public void palauttaaFalseJosPakassaTaiRivistossaOnKortteja2() {
        pelialusta.getKorttipakka().listaKorteista().clear();
        pelialusta.getKorttirivisto().haePakka(0).listaKorteista().clear();
        
        assertTrue(!pelialusta.pakassaTaiRivistossaEiKortteja());
    }

    @Test
    public void palautaaTrueJosPakassaJaRivistossaEiOleKortteja() {
        for (Korttipakka pakka : pelialusta.kaikkiKorttipakat()) {
            pakka.listaKorteista().clear();
        }

        assertTrue(pelialusta.pakassaTaiRivistossaEiKortteja());
    }
}
