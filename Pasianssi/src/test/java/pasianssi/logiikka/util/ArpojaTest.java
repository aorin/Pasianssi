package pasianssi.logiikka.util;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pasianssi.logiikka.domain.Kortti;
import pasianssi.logiikka.domain.Korttipakka;
import pasianssi.logiikka.domain.Maa;

public class ArpojaTest {
    private Arpoja arpoja;
    private Korttipakka pakka;
    private Kortti kortti1, kortti2, kortti3;
    
    public ArpojaTest() {
    }

    @Before
    public void setUp() {
        arpoja = new Arpoja();
        pakka = new Korttipakka();
        kortti1 = new Kortti(Maa.PATA, 2);
        kortti2 = new Kortti(Maa.RISTI, 10);
        kortti3 = new Kortti(Maa.HERTTA, 10);
        
        pakka.lisaaKortti(kortti1);
        pakka.lisaaKortti(kortti2);
        pakka.lisaaKortti(kortti3);
    }
    
    @Test
    public void arpooJonkunKortinPakasta() {
        Kortti kortti = arpoja.arvoKortti(pakka);
        pakka.poistaKortti(kortti);
        assertEquals(2, pakka.pakanKoko());
    }
    
    @Test
    public void eiArvoNeljaKertaaSamaaKorttia() {
        Kortti kortti4 = arpoja.arvoKortti(pakka);
        Kortti kortti5 = arpoja.arvoKortti(pakka);
        Kortti kortti6 = arpoja.arvoKortti(pakka);
        Kortti kortti7 = arpoja.arvoKortti(pakka);
        
        boolean eiArvoSamaaKorttia = true;
        
        if (kortti4 == kortti5 && kortti5 == kortti6 && kortti6 == kortti7) {
            eiArvoSamaaKorttia = false;
        }
        
        assertTrue(eiArvoSamaaKorttia);
    }
    
    @Test
    public void arpooAinakinKerranKortinKolme() {
        boolean arpoiKortinKolme = false;
        
        for (int i = 0; i < 50; i++) {
            Kortti kortti = arpoja.arvoKortti(pakka);
            
            if (kortti == kortti3) {
                arpoiKortinKolme = true;
                break;
            }
        }
        
        assertTrue(arpoiKortinKolme);
    }
}