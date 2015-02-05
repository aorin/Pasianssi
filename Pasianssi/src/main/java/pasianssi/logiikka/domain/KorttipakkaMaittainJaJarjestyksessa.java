package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee korttipakan, jonka kaikki kortit ovat samaa maata
 * ja kortit ovat järjestyksessä.
 */
public class KorttipakkaMaittainJaJarjestyksessa extends Korttipakka {

/**
 * Metodi lisää kortin korttipakkaan, jos kortti on samaa maata kuin edellinen
 * kortti ja arvoltaan yhden suurempi. Jos pakka on tyhjä ja kortti on arvoltaan
 * 1, myös silloin kortti voidaan lisätä.
 * 
 * @param kortti Lisättävä kortti
 * 
 * @return Palauttaa totta, jos lisäys onnistui 
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
