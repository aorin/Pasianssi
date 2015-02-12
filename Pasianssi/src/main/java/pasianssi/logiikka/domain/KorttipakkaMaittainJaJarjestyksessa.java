package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee korttipakan, jonka kaikki kortit ovat samaa maata
 * ja järjestyksessä.
 */
public class KorttipakkaMaittainJaJarjestyksessa extends Korttipakka {

/**
 * Metodi lisää kortin korttipakkaan, jos kortti on samaa maata kuin edellinen
 * kortti ja arvoltaan yhden suurempi tai jos pakka on tyhjä ja kortti on arvoltaan
 * 1.
 * 
 * @param kortti Lisättävä kortti.
 * 
 * @return Palauttaa totta, jos lisäys onnistui. 
 */
    @Override
    public boolean lisaaKorttiEhdolla(Kortti kortti) {
        if (onJarjestyksessaSeuraavaKortti(kortti) || kayPakanEnsimmaiseksiKortiksi(kortti)) {
            lisaaKortti(kortti);
            return true;
        }
        return false;
    }

    private boolean onJarjestyksessaSeuraavaKortti(Kortti kortti) {
        if (this.koko() == 0) {
            return false;
        }
        
        Kortti edellinenKortti = this.haeKortti(koko() - 1);

        return edellinenKortti.getMaa().equals(kortti.getMaa()) && edellinenKortti.getArvo() == kortti.getArvo() - 1;
    }

    private boolean kayPakanEnsimmaiseksiKortiksi(Kortti kortti) {
        return koko() == 0 && kortti.getArvo() == 1;
    }
}
