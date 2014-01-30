package ahaakkoset.gui;

import ahaakkoset.sovelluslogiikka.Pelisessio;

public class Kayttoliittyma implements Runnable {
    Pelisessio sessio;

    public Kayttoliittyma(Pelisessio sessio) {
        this.sessio = sessio;
    }    

    @Override
    public void run() {
        
    }
    
//    public void luoKomponentit(Container container) {
//        luodaan komponentit;
//        Nappaimistonkuuntelija nk = new Nappaimistonkuuntelija();
//    }
}
