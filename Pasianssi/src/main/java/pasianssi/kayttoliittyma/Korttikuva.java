package pasianssi.kayttoliittyma;

import java.awt.Image;
import java.awt.Rectangle;
import pasianssi.logiikka.domain.Kortti;

public class Korttikuva extends Rectangle implements Comparable<Korttikuva> {
    private Kortti kortti;
    private Image oikeinPainkuva;
    private Image vaarinPainKuva;
    private Image kuva;
    private boolean siirrettavana;

    public Korttikuva(int x, int y, int leveys, int korkeus, Image oikeinpain, Image vaarinpain, Kortti kortti) {
        super(x, y, leveys, korkeus);
        this.oikeinPainkuva = oikeinpain;
        this.vaarinPainKuva = vaarinpain;
        this.kortti = kortti;
        this.siirrettavana = false;
        
        if (!kortti.oikeinPain()) {
            this.kuva = vaarinpain;
        } else {
            this.kuva = oikeinpain;
        }
    }
    
    public void kaannaKorttiOikeinpain() {
        kortti.kaannaKorttiOikeinpain();
        kuva = oikeinPainkuva;
    }
    
    public void kaannaKorttiVaarinpain() {
        kortti.kaannaKorttiVaarinpain();
        kuva = vaarinPainKuva;
    }

    public Image getKuva() {
        return kuva;
    }

    public void setKuva(Image kuva) {
        this.kuva = kuva;
    }

    public Kortti getKortti() {
        return kortti;
    }

    public void setSiirrettavana(boolean siirrettavana) {
        this.siirrettavana = siirrettavana;
    }

    @Override
    public int compareTo(Korttikuva k) {
        if (k.siirrettavana || this.siirrettavana) {
            if (k.siirrettavana) {
                return -1;
            } else {
                return 1;
            }
        }
        
        if (k.getKortti().oikeinPain() != this.getKortti().oikeinPain()) {
            if (k.getKortti().oikeinPain()) {
                return -1;
            } else {
                return 1;
            }
        }  
        
        return (int) (this.getY() - k.getY());
    }
}
