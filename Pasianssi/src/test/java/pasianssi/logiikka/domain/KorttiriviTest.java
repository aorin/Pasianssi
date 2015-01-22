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
    public void lisaaKortinOikeaanListaan1() {
        korttirivi.lisaaOikeinPainOlevaKortti(kortti);
        assertEquals(kortti, korttirivi.getOikeinpainOlevatKortit().haeKortti(0));
    }
    
    @Test
    public void lisaaKortinOikeaanListaan2() {
        korttirivi.lisaaVaarinPainOlevaKortti(kortti);
        assertEquals(kortti, korttirivi.getVaarinpainOlevatKortit().haeKortti(0));
    }
   
    @Test
    public void lisaaKortinOikeinPain() {
        korttirivi.lisaaOikeinPainOlevaKortti(kortti);
        assertTrue(kortti.oikeinPain());
    }
    
    @Test
    public void lisaaKortinVaarinPain() {
        korttirivi.lisaaVaarinPainOlevaKortti(kortti);
        assertTrue(!kortti.oikeinPain());
    }
}

