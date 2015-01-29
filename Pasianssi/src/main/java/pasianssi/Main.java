package pasianssi;

import javax.swing.SwingUtilities;
import pasianssi.kayttoliittyma.Kayttoliittyma;
import pasianssi.logiikka.Sovellus;

public class Main {
    
    public static void main(String[] args) {
        Sovellus sovellus = new Sovellus();
        sovellus.kaynnista();
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(sovellus.getPelialusta());
        SwingUtilities.invokeLater(kayttoliittyma);
    }
    
}
