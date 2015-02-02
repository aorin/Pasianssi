package pasianssi.kayttoliittyma;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import pasianssi.logiikka.domain.Kortti;

public class Korttikuva extends Image implements Comparable<Korttikuva> {
    private int x;
    private int y;
    private Kortti kortti;
    private Image kuva;
    private boolean siirrettavana;

    public Korttikuva(Image kuva, int x, int y, Kortti kortti) {
        this.kuva = kuva;
        this.x = x;
        this.y = y;
        this.kortti = kortti;
        this.siirrettavana = false;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
    public int getWidth(ImageObserver io) {
        return kuva.getWidth(io);
    }

    @Override
    public int getHeight(ImageObserver io) {
        return kuva.getHeight(io);
    }

    @Override
    public ImageProducer getSource() {
        return kuva.getSource();
    }

    @Override
    public Graphics getGraphics() {
        return kuva.getGraphics();
    }

    @Override
    public Object getProperty(String string, ImageObserver io) {
        return kuva.getProperty(string, io);
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
        
        return this.getY() - k.getY();
    }
}
