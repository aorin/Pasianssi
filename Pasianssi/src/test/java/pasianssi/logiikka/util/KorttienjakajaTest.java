package pasianssi.logiikka.util;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.Korttirivisto;
import pasianssi.logiikka.domain.Pelialusta;

public class KorttienjakajaTest {

    private Korttienjakaja jakaja;
    private Pelialusta pelialusta;
    private Korttipakka pakka;
    private Korttirivisto rivisto;

    public KorttienjakajaTest() {
    }

    @Before
    public void setUp() {
        this.pelialusta = new Pelialusta();
        this.jakaja = new Korttienjakaja(pelialusta);
        this.pakka = pelialusta.getKorttipakka();
        this.rivisto = pelialusta.getKorttirivisto();
        
        jakaja.jaaKortit();
    }

    @Test
    public void pakassaLopuksi24korttia() {
        assertEquals(24, pakka.pakanKoko());
    }

    @Test
    public void ensimmaisetKolmeKorttiaEivatOleJarjestyksessaPakassa() {
        boolean jarjestyksessa = true;

        Kortti kortti1 = pakka.haeKortti(0);
        Kortti kortti2 = pakka.haeKortti(1);
        Kortti kortti3 = pakka.haeKortti(2);

        if (kortti1.getMaa() != kortti2.getMaa() || kortti2.getMaa() != kortti3.getMaa()) {
            jarjestyksessa = false;
        }

        if (kortti1.getArvo() == 1 && kortti2.getArvo() == 2 && kortti3.getArvo() == 3) {
            jarjestyksessa = false;
        }

        assertTrue(!jarjestyksessa);
    }
    
    @Test
    public void rivistonViimeisessaRivissaOnYksiKortti() {
        assertEquals(1, rivisto.haeRivi(6).getKortit().pakanKoko());
    }
    
    @Test
    public void rivistossaEiEnemmanKuinSeitsemanRivia() {
        boolean eiYlimaaraisiaRiveja = false;
        
        try {
            rivisto.haeRivi(7);
        } catch (Exception e) {
            eiYlimaaraisiaRiveja = true;
        }
        
        assertTrue(eiYlimaaraisiaRiveja);
    }
}
