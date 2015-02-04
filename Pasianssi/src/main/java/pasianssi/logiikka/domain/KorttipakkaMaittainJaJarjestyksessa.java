package pasianssi.logiikka.domain;

public class KorttipakkaMaittainJaJarjestyksessa extends Korttipakka {

    @Override
    public boolean lisaaKortti(Kortti kortti) {
        if (onJarjestyksessaSeuraavaKortti(kortti) || kayPakanEnsimmaiseksiKortiksi(kortti)) {
            return super.lisaaKortti(kortti);
        }
        return false;
    }

    @Override
    public boolean poistaKortti(Kortti kortti) {
        return false;
    }

    private boolean onJarjestyksessaSeuraavaKortti(Kortti kortti) {
        if (this.pakanKoko() == 0) {
            return false;
        }
        
        Kortti edellinenKortti = this.haeKortti(this.pakanKoko() - 1);

        return edellinenKortti.getMaa().equals(kortti.getMaa()) && edellinenKortti.getArvo() == kortti.getArvo() - 1;
    }

    private boolean kayPakanEnsimmaiseksiKortiksi(Kortti kortti) {
        return this.pakanKoko() == 0 && kortti.getArvo() == 1;
    }
}
