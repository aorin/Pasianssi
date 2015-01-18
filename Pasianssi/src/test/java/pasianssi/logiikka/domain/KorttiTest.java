package pasianssi.logiikka.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttiTest {
    
    public KorttiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Kortti kortti = new Kortti(Maa.HERTTA, 7);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void asettaaKortilleOikeanArvon() {
        
    }
}
