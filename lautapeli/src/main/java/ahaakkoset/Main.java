package ahaakkoset;
import ahaakkoset.gui.Kayttoliittyma;
import ahaakkoset.sovelluslogiikka.Pelisessio;

public class Main {

    public static void main(String[] args) {
        Pelisessio sessio = new Pelisessio();
//        Kayttoliittyma UI = new Kayttoliittyma(sessio);
        sessio.kaynnista();
    }
}
