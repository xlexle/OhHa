package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Kirjainvarasto;
import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Pelilauta;
import ahaakkoset.domain.Ruutu;
import ahaakkoset.domain.Sana;
import java.util.Iterator;
import java.util.List;

public class PelinInteraktiot extends Pelisessio {

    private boolean peliKaynnissa = true;
    private int kirjaimia;
    private Pelilauta pelilauta;
    private Ruudunaktivoija aktivoija;
    private List<Pelaaja> pelaajat;
    private Kirjainvarasto vapaatKirjaimet;

    public PelinInteraktiot(int kirjaimia, Pelilauta pelilauta, List pelaajat, Kirjainvarasto vapaatKirjaimet) {
        this.kirjaimia = kirjaimia;
        this.pelilauta = pelilauta;
        this.pelaajat = pelaajat;
        this.vapaatKirjaimet = vapaatKirjaimet;
        this.aktivoija = new Ruudunaktivoija(pelilauta.getRuudut());
    }

    public void suorita() {  // ei testattu
        Iterator<Pelaaja> vuoronVaihtaja = pelaajat.iterator();
        Pelaaja aktiivinen = vuoronVaihtaja.next();

        while (peliKaynnissa) {
            aktivoija.asetaLopullisestiInaktiiviset();
            aktivoija.tyhjennaEdellinenValittu();

            if (peliVuoro(aktiivinen)) {
                aktiivinen.lisaaVuoro();
            }

            if (vuoronVaihtaja.hasNext()) {
                aktiivinen = vuoronVaihtaja.next();
            } else {
                aktiivinen = pelaajat.get(0);
            }
        }
    }

    private boolean peliVuoro(Pelaaja pelaaja) {  // ei testattu
        aktivoija.asetaKaikkienRuutujenAktiivisuus(true);

//            if ( ) { // GUI: pelaaja lopettaa pelin
//                peliKaynnissa = false;
//                return false;         
//            }
//            if ( ) { // GUI: pelaaja haluaa skipata vuoron
//                return false;
//            }
        luoSana(pelaaja);
        vapaatKirjaimet.arvoUudetKirjaimet(pelaaja);
        return true;
    }

    private void luoSana(Pelaaja pelaaja) {  // ei testattu
        String kirjaimet = "";
        Ruutu eka = valitseRuutu(pelaaja);
        aktivoija.maaritaEnsimmaisenRuudunJalkeisetAktiiviset(eka);
        if (eka.getKirjain() != null) {
            kirjaimet += eka.getKirjain();
        } else {
            kirjaimet += valitseKirjain(eka, pelaaja);
        }

        while (true) {
//              if () { // GUI:ssa klikataan "valmis" TAI pelaajalla ei kirjaimia TAI laudan pääty saavutettu; 
////                    // valmis ei voi klikata jos ei käyttänyt omia kirjaimia yhtäkään tai sana vain 1 kirjaimen pituinen
////                break;         
////            }

            Ruutu seur = valitseRuutu(pelaaja);
            aktivoija.maaritaSeuraavatAktiiviset(seur);
            if (seur.getKirjain() != null) {
                kirjaimet += seur.getKirjain();
            } else {
                kirjaimet += valitseKirjain(seur, pelaaja);
            }

            break; //turha
        }

        String merkitys = ""; // GUI: kysyy merkityksen pelaajalta
        Sana uusi = new Sana(kirjaimet, merkitys);
        pelaaja.lisaaSana(uusi);
        uusi.asetaPisteet(0); // GUI: kysyy pisteet pelaajalta
    }

    private Ruutu valitseRuutu(Pelaaja pelaaja) { // ei testattu
        return pelilauta.haeRuutu(0);  // GUI: tietää mitkä ruudut aktiivisia
    }

    private Character valitseKirjain(Ruutu ruutu, Pelaaja pelaaja) { // ei testattu
        Character kirjain = null;
        while (kirjain == null) {
            kirjain = pelaaja.otaKirjain('x'); // GUI: pyytää kirjaimen pelaajalta
        }

        ruutu.setKirjain(kirjain);
        return kirjain;
    }
}
