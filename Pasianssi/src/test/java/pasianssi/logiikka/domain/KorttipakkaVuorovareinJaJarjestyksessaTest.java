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

        kortti1.kaannaKorttiOikeinpain();
        kortti2.kaannaKorttiOikeinpain();
        kortti3.kaannaKorttiOikeinpain();
        kortti4.kaannaKorttiOikeinpain();

        pakka.lisaaKortti(kortti1);
    }

    @Test
    public void lisaaVuorovarisenJaJarjestyksessaSeuraavanKortinEhdolla() {
        pakka.lisaaKorttiEhdolla(kortti2);

        assertEquals(2, pakka.pakanKoko());
    }

    @Test
    public void eiLisaaSamanvaristaKorttiaEhdolla() {
        pakka.lisaaKorttiEhdolla(kortti3);

        assertEquals(1, pakka.pakanKoko());
    }

    @Test
    public void eiLisaaVaaranArvoistaKorttiaEhdolla() {
        pakka.lisaaKorttiEhdolla(kortti4);

        assertEquals(1, pakka.pakanKoko());
    }

    @Test
    public void palauttaaTrueJosLisaysOnnistuu() {
        assertTrue(pakka.lisaaKorttiEhdolla(kortti2));
    }

    @Test
    public void palauttaaFalseJosLisaysEiOnnistu() {
        assertTrue(!pakka.lisaaKorttiEhdolla(kortti3));
    }
}
