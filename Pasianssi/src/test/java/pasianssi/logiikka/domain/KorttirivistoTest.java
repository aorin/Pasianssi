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
        
        Kortti kortti1 = new Kortti(Maa.RISTI, 10);
        Kortti kortti2 = new Kortti(Maa.RUUTU, 12);
        kortti2.kaannaKorttiOikeinpain();
        
        korttirivi.lisaaKortti(kortti1);
        korttirivi.lisaaKortti(kortti2);
    }
    
    @Test
    public void lisaaYhdenRivin() {
        korttirivisto.lisaaRivi(korttirivi);
        assertEquals(korttirivi, korttirivisto.haeRivi(0));
    }
}
