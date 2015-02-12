package pasianssi.logiikka.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttipakkaMaittainJaJarjestyksessaTest {
    private KorttipakkaMaittainJaJarjestyksessa korttipakka;
    private Kortti kortti1, kortti2;
    
    public KorttipakkaMaittainJaJarjestyksessaTest() {
    }

    @Before
    public void setUp() {
        korttipakka = new KorttipakkaMaittainJaJarjestyksessa();
        kortti1 = new Kortti(Maa.PATA, 1);
        kortti2 = new Kortti(Maa.PATA, 2);
    }
    
    @Test
    public void lisaaOikeanEnsimmaisenKortin() {
        korttipakka.lisaaKorttiEhdolla(kortti1);
        assertEquals(kortti1, korttipakka.haeKortti(0));
    }
    
    @Test
    public void eiLisaaVaaraaEnsimmaistaKorttia() {
        korttipakka.lisaaKorttiEhdolla(kortti2);
        assertEquals(0, korttipakka.koko());
    }
    
    @Test
    public void lisaaOikeanToisenKortin() {
        korttipakka.lisaaKorttiEhdolla(kortti1);
        korttipakka.lisaaKorttiEhdolla(kortti2);
        assertEquals(kortti2, korttipakka.haeKortti(1));
    }
    
    @Test
    public void eiLisaaVaaranMaanKorttia() {
        korttipakka.lisaaKorttiEhdolla(kortti1);
        korttipakka.lisaaKorttiEhdolla(new Kortti(Maa.HERTTA, 2));
        assertEquals(1, korttipakka.koko());
    }
    
    @Test
    public void eiLisaaVaaranArvoistaKorttia() {
        korttipakka.lisaaKorttiEhdolla(kortti1);
        korttipakka.lisaaKorttiEhdolla(new Kortti(Maa.PATA, 5));
        assertEquals(1, korttipakka.koko());
    }
    
    @Test
    public void palauttaaTrueJosLisaysOnnistuu() {
        assertTrue(korttipakka.lisaaKorttiEhdolla(kortti1));
    }
    
    @Test
    public void palauttaaFalseJosLisaysEiOnnistu() {
        assertTrue(!korttipakka.lisaaKorttiEhdolla(kortti2));
    }
}
