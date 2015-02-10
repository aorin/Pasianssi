package pasianssi.logiikka.domain;

/**
 * Luokka määrittelee korttipakan, jonka oikeinpäin olevat kortit
 * ovat järjestyksessä ja vuorovärein.Pakan alkuosassa voi olla
 * mitä tahansa väärinpäin olevia kortteja.
 */
public class KorttipakkaVuoroVareinJaJarjestyksessa extends Korttipakka {
    
/**
 * Metodi lisää kortin pakkaan, jos lisättävän kortin maa on pakan viimeisen
 * kortin maan kanssa erivärinen ja jos sen arvo on yhtä pienempi kuin pakan
 * viimeisen kortin.
 * 
 * @param kortti Lisättävä kortti
 * 
 * @return Palauttaa totta, jos lisäys onnistui
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
        if (pakanKoko() == 0) {
            return kortti.getArvo() == 13;
        }
        
        Kortti edellinenKortti = this.haeKortti(pakanKoko() - 1);
        
        if (kortti.getMaa().getArvo() % 2 == edellinenKortti.getMaa().getArvo() % 2) {
            return false;
        }
        
        return kortti.getArvo() == edellinenKortti.getArvo() - 1;
    }
}
