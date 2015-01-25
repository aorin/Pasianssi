package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

public class TavoiteRivi {
    private List<Korttipakka> pakat;

    public TavoiteRivi() {
        this.pakat = new ArrayList<>();
        
        pakat.add(new Korttipakka());
        pakat.add(new Korttipakka());
        pakat.add(new Korttipakka());
        pakat.add(new Korttipakka());
    }
    
}
