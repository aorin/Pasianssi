package pasianssi.logiikka.domain;

public class KorttipakkaVuoroVareinJaJarjestyksessa extends Korttipakka {

    public KorttipakkaVuoroVareinJaJarjestyksessa() {
    }
    
    @Override
    public void lisaaKortti(Kortti kortti) {
        if (kaySeuraavaksi(kortti)) {
            super.lisaaKortti(kortti);
        }  
    }
    
    private boolean kaySeuraavaksi(Kortti kortti) {
        if (this.pakanKoko() == 0 || !kortti.oikeinPain()) {
            return true;
        }
        
        Kortti edellinenKortti = this.haeKortti(this.pakanKoko() - 1);
        
        if (!edellinenKortti.oikeinPain()) {
            return true;
        }
        
        if (kortti.getMaa().getArvo() % 2 == edellinenKortti.getMaa().getArvo() % 2) {
            return false;
        }
        
        return kortti.getArvo() == edellinenKortti.getArvo() - 1;
    }
}
