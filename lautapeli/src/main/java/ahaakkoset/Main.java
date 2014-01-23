package ahaakkoset;
import ahaakkoset.sovelluslogiikka.Pelisessio;

public class Main {

    public static void main(String[] args) {
        Pelisessio sessio = new Pelisessio();
//        luo kayttoliittyma
        sessio.aloita();
    }
}
