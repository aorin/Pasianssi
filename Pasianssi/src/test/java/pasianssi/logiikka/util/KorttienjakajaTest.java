package pasianssi.logiikka.util;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pasianssi.logiikka.domain.Pelialusta;

public class KorttienjakajaTest {
    private Korttienjakaja jakaja;
    private Pelialusta pelialusta;

    public KorttienjakajaTest() {
    }
    
    @Before
    public void setUp() {
        this.pelialusta = new Pelialusta();
        this.jakaja = new Korttienjakaja(pelialusta);
    }
    
    @Test
    public void pakassa24korttia() {
        jakaja.jaaKortit();
        assertEquals(24, pelialusta.getKorttipakka().pakanKoko());
    }
}
