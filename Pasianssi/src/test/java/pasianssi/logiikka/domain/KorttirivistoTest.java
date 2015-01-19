package pasianssi.logiikka.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttirivistoTest {
    private Korttirivisto korttirivisto;
    private Korttirivi korttirivi;
    
    public KorttirivistoTest() {
    }

    @Before
    public void setUp() {
        this.korttirivisto = new Korttirivisto();
        this.korttirivi = new Korttirivi();
        korttirivi.lisaaVaarinPainOlevaKortti(new Kortti(Maa.RISTI, 10));
        korttirivi.lisaaOikeinPainOlevaKortti(new Kortti(Maa.RUUTU, 12));
    }
    
    @Test
    public void lisaaYhdenRivin() {
        korttirivisto.lisaaRivi(korttirivi);
        assertEquals(korttirivi, korttirivisto.haeRivi(0));
    }
}
