package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee korttipakan, jonka kortit ovat järjestyksessä 
 * ja vuorovärein. 
 */
public class KorttipakkaVuoroVareinJaJarjestyksessa extends Korttipakka {
    
/**
 * Metodi lisää kortin pakkaan, jos lisättävän kortin maa on pakan paallimmaisen
 * kortin maan kanssa erivärinen ja jos sen arvo on yhtä pienempi kuin pakan
 * paallimmaisen kortin.
 * 
 * @param kortti Lisättävä kortti
 * 
 * @return Palauttaa true, jos lisäys onnistui.
 */    
    @Override
    public boolean lisaaKorttiEhdolla(Kortti kortti) {
        if (kaySeuraavaksi(kortti)) {
            lisaaKortti(kortti);
            return true;
        }
        return false;
    }
    
    private boolean kaySeuraavaksi(Kortti kortti) {
        if (koko() == 0) {
            return kortti.getArvo() == 13;
        }
        
        Kortti edellinenKortti = haePaallimmainenKortti();
        if (!edellinenKortti.oikeinPain()) {
            return false;
        }
        
        if (kortti.getMaa().getArvo() % 2 == edellinenKortti.getMaa().getArvo() % 2) {
            return false;
        }
        
        return kortti.getArvo() == edellinenKortti.getArvo() - 1;
    }
}
