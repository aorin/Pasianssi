package pasianssi.logiikka.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttiriviTest {
    private Korttirivi korttirivi;
    private Kortti kortti;
    
    public KorttiriviTest() {
    }

    @Before
    public void setUp() {
        korttirivi = new Korttirivi();
        kortti = new Kortti(Maa.RISTI, 13);
    }
    
    @Test
    public void lisaaYhdenKortin() {
        korttirivi.lisaaKortti(kortti);
        assertEquals(kortti, korttirivi.haeKortti(0));
    }
}

