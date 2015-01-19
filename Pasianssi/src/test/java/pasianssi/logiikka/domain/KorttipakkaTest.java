package pasianssi.logiikka.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttipakkaTest {

    private Korttipakka korttipakka;
    private Kortti kortti1, kortti2;

    public KorttipakkaTest() {
    }

    @Before
    public void setUp() {
        korttipakka = new Korttipakka();
        kortti1 = new Kortti(Maa.PATA, 2);
        kortti2 = new Kortti(Maa.HERTTA, 4);
    }

    @Test
    public void lisaaYhdenKortin() {
        korttipakka.lisaaKortti(kortti1);
        assertEquals(1, korttipakka.pakanKoko());
    }
    
    @Test
    public void lisaaKaksiKorttia() {
        korttipakka.lisaaKortti(kortti1);
        korttipakka.lisaaKortti(kortti2);
        assertEquals(2, korttipakka.pakanKoko());
    }
    
    @Test
    public void poistaaKortin() {
        korttipakka.lisaaKortti(kortti1);
        korttipakka.poistaKortti(kortti1);
        assertEquals(0, korttipakka.pakanKoko());
    }
    
    @Test
    public void hakeeKortinIndeksinPerusteella1() {
        korttipakka.lisaaKortti(kortti1);
        korttipakka.lisaaKortti(kortti2);
        assertEquals(kortti1, korttipakka.haeKortti(0));
    }
    
    @Test
    public void hakeeKortinIndeksinPerusteella2() {
        korttipakka.lisaaKortti(kortti1);
        korttipakka.lisaaKortti(kortti2);
        assertEquals(kortti2, korttipakka.haeKortti(1));
    }
}
