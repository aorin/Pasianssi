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
        korttipakka.lisaaKortti(kortti1);
        assertEquals(kortti1, korttipakka.haeKortti(0));
    }
    
    @Test
    public void eiLisaaVaaraaEnsimmaistaKorttia() {
        korttipakka.lisaaKortti(kortti2);
        assertEquals(0, korttipakka.pakanKoko());
    }
    
    @Test
    public void lisaaOikeanToisenKortin() {
        korttipakka.lisaaKortti(kortti1);
        korttipakka.lisaaKortti(kortti2);
        assertEquals(kortti2, korttipakka.haeKortti(1));
    }
    
    @Test
    public void eiLisaaVaaranMaanKorttia() {
        korttipakka.lisaaKortti(kortti1);
        korttipakka.lisaaKortti(new Kortti(Maa.HERTTA, 2));
        assertEquals(1, korttipakka.pakanKoko());
    }
    
    @Test
    public void eiLisaaVaaranArvoistaKorttia() {
        korttipakka.lisaaKortti(kortti1);
        korttipakka.lisaaKortti(new Kortti(Maa.PATA, 5));
        assertEquals(1, korttipakka.pakanKoko());
    }
}
