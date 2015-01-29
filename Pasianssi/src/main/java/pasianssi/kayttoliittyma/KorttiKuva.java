package pasianssi.kayttoliittyma;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import pasianssi.logiikka.domain.Kortti;

public class KorttiKuva extends Image {
    private int x;
    private int y;
    private Kortti kortti;
    private Image kuva;

    public KorttiKuva(Image kuva, int x, int y, Kortti kortti) {
        this.kuva = kuva;
        this.x = x;
        this.y = y;
        this.kortti = kortti;
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
}
