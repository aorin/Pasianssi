package pasianssi.logiikka.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttirivistoTest {
    private Korttirivisto korttirivisto;
    private KorttipakkaVuoroVareinJaJarjestyksessa korttirivi;
    
    public KorttirivistoTest() {
    }

    @Before
    public void setUp() {
        this.korttirivisto = new Korttirivisto();
        this.korttirivi = new KorttipakkaVuoroVareinJaJarjestyksessa();
        
        Kortti kortti1 = new Kortti(Maa.RISTI, 10);
        Kortti kortti2 = new Kortti(Maa.RUUTU, 12);
        kortti2.kaannaKorttiOikeinpain();
        
        korttirivi.lisaaKortti(kortti1);
        korttirivi.lisaaKortti(kortti2);
    }
    
    @Test
    public void lisaaYhdenRivin() {
        korttirivisto.lisaaRivi(korttirivi);
        assertEquals(korttirivi, korttirivisto.haePakka(0));
    }
    
    @Test
    public void palauttaaOikeanKoon() {
        korttirivisto.lisaaRivi(korttirivi);
        korttirivisto.lisaaRivi(new KorttipakkaVuoroVareinJaJarjestyksessa());
        korttirivisto.lisaaRivi(new KorttipakkaVuoroVareinJaJarjestyksessa());
        korttirivisto.lisaaRivi(new KorttipakkaVuoroVareinJaJarjestyksessa());
        
        assertEquals(4, korttirivisto.koko());
    }
}
