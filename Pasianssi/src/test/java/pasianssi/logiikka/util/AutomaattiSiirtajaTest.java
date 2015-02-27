package pasianssi.logiikka.util;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pasianssi.logiikka.domain.*;

public class AutomaattiSiirtajaTest {

    private Pelialusta alusta;
    private AutomaattiSiirtaja siirtaja;
    private Kortti kortti1, kortti2, kortti3, kortti4, kortti5, kortti6, kortti7;

    @Before
    public void setUp() {
        alusta = new Pelialusta();

        luoKortit();
        lisaaRivistonPakat();
        lisaaTavoiteRivistonPakat();
        lisaaKorttipakanKortit();

        siirtaja = new AutomaattiSiirtaja(alusta);
    }

    private void luoKortit() {
        kortti1 = new Kortti(Maa.HERTTA, 1);
        kortti2 = new Kortti(Maa.HERTTA, 3);
        kortti3 = new Kortti(Maa.PATA, 2);
        kortti4 = new Kortti(Maa.RISTI, 9);
        kortti5 = new Kortti(Maa.RUUTU, 1);
        kortti6 = new Kortti(Maa.RUUTU, 8);
        kortti7 = new Kortti(Maa.PATA, 12);

        kortti1.kaannaKorttiOikeinpain();
        kortti2.kaannaKorttiOikeinpain();
        kortti3.kaannaKorttiOikeinpain();
        kortti4.kaannaKorttiOikeinpain();

        kortti7.kaannaKorttiOikeinpain();
    }

    private void lisaaRivistonPakat() {
        Korttirivisto rivisto = alusta.getKorttirivisto();

        Korttipakka pakka1 = new KorttipakkaVuoroVareinJaJarjestyksessa();
        pakka1.lisaaKortti(kortti1);

        Korttipakka pakka2 = new KorttipakkaVuoroVareinJaJarjestyksessa();
        pakka2.lisaaKortti(kortti2);

        Korttipakka pakka3 = new KorttipakkaVuoroVareinJaJarjestyksessa();
        pakka3.lisaaKortti(kortti3);

        Korttipakka pakka4 = new KorttipakkaVuoroVareinJaJarjestyksessa();
        pakka4.lisaaKortti(kortti4);

        rivisto.lisaaPakka(pakka1);
        rivisto.lisaaPakka(pakka2);
        rivisto.lisaaPakka(pakka3);
        rivisto.lisaaPakka(pakka4);
    }

    private void lisaaTavoiteRivistonPakat() {
        for (int i = 0; i < 4; i++) {
            alusta.getTavoiterivisto().lisaaPakka(new KorttipakkaMaittainJaJarjestyksessa());
        }
    }

    private void lisaaKorttipakanKortit() {
        Korttipakka pakka = alusta.getKorttipakka();
        pakka.lisaaKortti(kortti5);
        pakka.lisaaKortti(kortti6);
        pakka.lisaaKortti(kortti7);
    }

    @Test
    public void siirtaaEnsimmaiseksiRivistostaTavoiteriviin() {
        List<Kortti> siirrettyKortti = siirtaja.siirraKortti();

        assertEquals(kortti1, siirrettyKortti.get(0));
    }

    @Test
    public void siirtaaRivistostaToiseen() {
        siirtaja.siirraKortti();
        List<Kortti> siirrettyKortti = siirtaja.siirraKortti();

        assertEquals(kortti3, siirrettyKortti.get(0));
    }

    @Test
    public void siirtaaPakastaRivistoon() {
        List<Kortti> siirrettyKortti = null;

        for (int i = 0; i < 5; i++) {
            siirrettyKortti = siirtaja.siirraKortti();
        }

        assertEquals(kortti5, siirrettyKortti.get(0));
    }

    @Test
    public void siirtaaPakastaTavoiteRivistoon() {
        List<Kortti> siirrettyKortti = null;

        for (int i = 0; i < 4; i++) {
            siirrettyKortti = siirtaja.siirraKortti();
        }

        assertEquals(kortti6, siirrettyKortti.get(0));
    }

    @Test
    public void kayPakkaaLapi() {
        List<Kortti> siirrettyKortti = null;

        for (int i = 0; i < 3; i++) {
            siirrettyKortti = siirtaja.siirraKortti();
        }

        assertEquals(kortti7, siirrettyKortti.get(0));
    }

    @Test
    public void kaantaaVaarinpainOlevanKortin() {
        kortti1.kaannaKorttiVaarinpain();
        siirtaja.siirraKortti();

        assertTrue(kortti1.oikeinPain());
    }

    @Test
    public void eiSiirraLiianMontaaKorttiaKerralla() {
        List<Kortti> siirrettyKortti = null;

        for (int i = 0; i < 3; i++) {
            siirrettyKortti = siirtaja.siirraKortti();
        }
        
        assertEquals(1, siirrettyKortti.size());
    }
}
