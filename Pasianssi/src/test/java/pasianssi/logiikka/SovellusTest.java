package pasianssi.logiikka;

import org.junit.Before;
import org.junit.Test;

public class SovellusTest {
    private Sovellus sovellus;

    public SovellusTest() {
    }
    
    @Before
    public void setUp() {
        this.sovellus = new Sovellus();
        sovellus.kaynnista();
    }
}
