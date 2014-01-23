package ahaakkoset.sovelluslogiikka;

import java.util.Scanner;

public class PelinInteraktiot extends Pelisessio {    
    // Interaktiot sisältää pelaajan ja Pelisession väliset toiminnot
    private Scanner lukija = new Scanner(System.in);
    private boolean peliLoppuu = false;

    public void suorita() {
//        while (peliLoppuu) {
////             pelaaja on yhteydessä käyttöliittymään; looppi loppuu kun session kirjainvarasto on tyhjä tai pelaaja päättää lopettaa pelin
//        }
//        super.arvoPelaajienAloitusKirjaimet();
    }

    public void lopetaPeli() {
        this.peliLoppuu = true;
    }
}
