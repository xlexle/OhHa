package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;

public class EnnenPeliaInteraktiot extends Pelisessio {

    private boolean peliAlkaa = false;

    public void suorita() {
//        while (!peliAlkaa) {            
////            pelaaja on yhteydessä käyttöliittymään; looppi loppuu kun kaikki alkuparametrit on asetettu
//        }

        //testikoodia
//        lisaaPelaaja("Pietari"); //pelaajalle tulisi ilmestyä 7 kirjainta käyttöön, ja Kirjainvarastosta tulisi hävitä samat 7
    }

    public void lisaaPelaaja(String nimi) {
        Pelaaja pelaaja = new Pelaaja(nimi);
        
        super.pelaajat.add(pelaaja);
    }
    
    @Override
    public Pelaaja haePelaaja(String nimi) {
        return super.haePelaaja(nimi);
    }

    public boolean isPeliAlkaa() {
        return peliAlkaa;
    }

    public void aloitaPeli() {
        this.peliAlkaa = true;
    }
}
