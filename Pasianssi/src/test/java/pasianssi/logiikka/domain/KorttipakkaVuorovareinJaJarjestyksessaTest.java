package pasianssi.logiikka.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttipakkaVuorovareinJaJarjestyksessaTest {
    private KorttipakkaVuoroVareinJaJarjestyksessa pakka;
    private Kortti kortti1, kortti2, kortti3, kortti4;
    
    public KorttipakkaVuorovareinJaJarjestyksessaTest() {
    }

    @Before
    public void setUp() {
        pakka = new KorttipakkaVuoroVareinJaJarjestyksessa();
        kortti1 = new Kortti(Maa.RISTI, 10);
        kortti2 = new Kortti(Maa.HERTTA, 9);
        kortti3 = new Kortti(Maa.RISTI, 9);
        kortti4 = new Kortti(Maa.RUUTU, 2);
    }
    
    @Test
    public void lisaaYhdenVaarinpainOlevanKortin() {
        pakka.lisaaKortti(kortti1);
        assertEquals(kortti1, pakka.haeKortti(0));
    }
    
    @Test
    public void lisaaYhdenOikeinPainOlevanKortin() {
        kortti1.kaannaKorttiOikeinpain();
        pakka.lisaaKortti(kortti1);
        assertEquals(1, pakka.pakanKoko());
    }
    
    @Test
    public void lisaaNeljaVaarinpainOlevaaKorttia() {
        pakka.lisaaKortti(kortti1);
        pakka.lisaaKortti(kortti2);
        pakka.lisaaKortti(kortti3);
        pakka.lisaaKortti(kortti4);
        
        assertEquals(4, pakka.pakanKoko());
    }
    
    @Test
    public void lisaaVaarinpainOlevanKortinPaalleOikeinpainOlevanKortin() {
        pakka.lisaaKortti(kortti4);
        kortti2.kaannaKorttiOikeinpain();
        pakka.lisaaKortti(kortti2);
        
        assertEquals(2, pakka.pakanKoko());
    }
    
    @Test
    public void lisaaVuorovarisenJaJarjestyksessaSeuraavanKortin() {
        kortti1.kaannaKorttiOikeinpain();
        kortti2.kaannaKorttiOikeinpain();
        
        pakka.lisaaKortti(kortti1);
        pakka.lisaaKortti(kortti2);
        
        assertEquals(2, pakka.pakanKoko());
    }
    
    @Test
    public void eiLisaaSamanvaristaKorttia() {
        kortti1.kaannaKorttiOikeinpain();
        kortti3.kaannaKorttiOikeinpain();
        
        pakka.lisaaKortti(kortti1);
        pakka.lisaaKortti(kortti3);
        
        assertEquals(1, pakka.pakanKoko());
    }
    
    @Test
    public void eiLisaaVaaranArvoistaKorttia() {
        kortti1.kaannaKorttiOikeinpain();
        kortti4.kaannaKorttiOikeinpain();
        
        pakka.lisaaKortti(kortti1);
        pakka.lisaaKortti(kortti4);
        
        assertEquals(1, pakka.pakanKoko());
    }
}

