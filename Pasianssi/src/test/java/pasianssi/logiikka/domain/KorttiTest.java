package pasianssi.logiikka.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttiTest {
    private Kortti kortti;
    
    public KorttiTest() {
    }

    @Before
    public void setUp() {
        kortti = new Kortti(Maa.HERTTA, 7);
    }
   
    @Test
    public void asettaaKortilleOikeanArvon() {
        assertEquals(7, kortti.getArvo());
    }
    
    @Test
    public void asettaaKortilleOikeanMaan() {
        assertEquals(Maa.HERTTA, kortti.getMaa());
    }
    
    @Test
    public void kaantaaKortinOikenpain() {
        kortti.kaannaKorttiOikeinpain();
        assertTrue(kortti.oikeinPain());
    }
    
    @Test
    public void kaantaaKortinVaarinpain() {
        kortti.kaannaKorttiOikeinpain();
        kortti.kaannaKorttiVaarinpain();
        assertTrue(!kortti.oikeinPain());
    }
}
