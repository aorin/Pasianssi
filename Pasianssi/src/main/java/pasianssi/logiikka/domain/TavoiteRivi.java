package pasianssi.logiikka.domain;

import java.util.ArrayList;
import java.util.List;

public class TavoiteRivi {
    private List<KorttipakkaMaittainJaJarjestyksessa> pakat;

    public TavoiteRivi() {
        this.pakat = new ArrayList<>();
        
        pakat.add(new KorttipakkaMaittainJaJarjestyksessa());
        pakat.add(new KorttipakkaMaittainJaJarjestyksessa());
        pakat.add(new KorttipakkaMaittainJaJarjestyksessa());
        pakat.add(new KorttipakkaMaittainJaJarjestyksessa());
    }
    
}
