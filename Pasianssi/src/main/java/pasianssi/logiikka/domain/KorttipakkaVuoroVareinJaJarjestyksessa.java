package pasianssi.logiikka.domain;

public class KorttipakkaVuoroVareinJaJarjestyksessa extends Korttipakka {

    public KorttipakkaVuoroVareinJaJarjestyksessa() {
    }
    
    @Override
    public boolean lisaaKortti(Kortti kortti) {
        if (kaySeuraavaksi(kortti)) {
            return super.lisaaKortti(kortti);
        }
        return false;
    }

    @Override
    public boolean poistaKortti(Kortti kortti) {
        if (kortti.oikeinPain()) {
            int i = haeIndeksi(kortti);
            
            for (int j = pakanKoko() - 1; i <= i; i--) {
                super.poistaKortti(kortti);
            }
            
            return true;
        }
        
        return false;
    }
    
    private boolean kaySeuraavaksi(Kortti kortti) {
        if (this.pakanKoko() == 0 || !kortti.oikeinPain()) {
            return true;
        }
        
        Kortti edellinenKortti = this.haeKortti(pakanKoko() - 1);
        
        if (!edellinenKortti.oikeinPain()) {
            return true;
        }
        
        if (kortti.getMaa().getArvo() % 2 == edellinenKortti.getMaa().getArvo() % 2) {
            return false;
        }
        
        return kortti.getArvo() == edellinenKortti.getArvo() - 1;
    }
}
