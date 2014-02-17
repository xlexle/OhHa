package ahaakkoset.sovelluslogiikka;

import ahaakkoset.domain.Pelaaja;
import ahaakkoset.domain.Sana;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Pelisessio-luokka toimii välikappaleena käyttöliittymän ja pelin
 * domain-luokkien interaktiossa. Sovelluslogiikasta vastuussa olevana tämä
 * luokka käsittelee käyttöliittymästä saapuvia metodikutsuja ja joko muuttaa
 * oliomuuttujiensa ominaisuuksia tai palauttaa käyttöliittymälle informaatiota
 * oliomuuttujiensa ominaisuuksista.
 *
 * @author Ville Lehtinen
 */
public class Pelisessio {

    private int pelaajallaKirjaimia;
    private int pelinPituus;
    private int indeksi = -1;
    private Pelaaja aktiivinenPelaaja;
    private List<Pelaaja> pelaajat = new ArrayList<>();
    private Kirjainvarasto vapaatKirjaimet;
    private Sana viimeisinLuotu;

    public Pelisessio() {
    }

    /**
     * Metodi lisää pelaajat-listaan uuden Pelaajan jonka nimi saadaan
     * parametrina käyttöliittymästä. Pelaajalle asetetaan maksimikirjainmäärä
     * ennaltamäärätyn oliomuuttujan 'kirjaimia' perusteella. Uutta Pelaajaa ei
     * lisätä mikäli nimi on väärän pituinen tai sessiosta löytyy jo
     * samanniminen Pelaaja.
     *
     * @param nimi
     * @return metodikutsun onnistuminen
     */
    public boolean asetaPelinPituus(String pituus) {
        if (pituus == null) {
            return false;
        } else {
            if (pituus.equals("Normaali")) {
                this.pelinPituus = 1;
                return true;
            } else if (pituus.equals("Marathon")) {
                this.pelinPituus = 2;
                return true;
            }
        }

        return false;
    }

    public boolean asetaPelaajallaKirjaimia(String vaikeustaso) {
        if (vaikeustaso == null) {
            return false;
        } else {
            if (vaikeustaso.equals("Pala kakkua")) {
                this.pelaajallaKirjaimia = 9;
                return true;
            } else if (vaikeustaso.equals("Rokataan")) {
                this.pelaajallaKirjaimia = 8;
                return true;
            } else if (vaikeustaso.equals("Täältä pesee")) {
                this.pelaajallaKirjaimia = 7;
                return true;
            } else if (vaikeustaso.equals("Däämn oon hyvä")) {
                this.pelaajallaKirjaimia = 6;
                return true;
            }
        }

        return false;
    }

    public boolean lisaaPelaaja(String nimi) {
        if (nimi == null || nimi.length() < 3 || nimi.length() > 16) {
            return false;
        }

        if (pelaajat.contains(new Pelaaja(nimi, pelaajallaKirjaimia))) {
            return false;
        }

        pelaajat.add(new Pelaaja(nimi, pelaajallaKirjaimia));
        return true;
    }

    public void luoKirjainVarasto() {
        this.vapaatKirjaimet = new Kirjainvarasto(pelinPituus);
    }

    /**
     *
     */
    public void arvoPelaajienAloitusKirjaimet() {
        for (int i = 0; i < pelaajallaKirjaimia; i++) {
            for (int j = 0; j < pelaajat.size(); j++) {
                pelaajat.get(j).lisaaKirjain(vapaatKirjaimet.arvoKirjain());
            }
        }
    }

    /**
     *
     */
    public void seuraavaPelaaja() {
        if (indeksi == -1 || indeksi == pelaajat.size() - 1) {
            indeksi = 0;
        } else {
            indeksi++;
        }

        if (aktiivinenPelaaja != null) {
            aktiivinenPelaaja.lisaaVuoro();
        }

        aktiivinenPelaaja = pelaajat.get(indeksi);
    }

    /**
     *
     * @return
     */
    public boolean kaikillaVahintaanYksiVuoro() {
        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.getVuorot() == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @return
     */
    public boolean pelaajallaEiKirjaimia() {
        if (!aktiivinenPelaaja.getOmatKirjaimet().isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     *
     */
    public void arvoPelaajalleKirjaimia() {
        int montako = aktiivinenPelaaja.getEnintaanKirjaimia() - aktiivinenPelaaja.getOmatKirjaimet().size();
        if (montako > vapaatKirjaimet.getKirjainSailio().size()) {
            montako = vapaatKirjaimet.getKirjainSailio().size();
        }
        
        for (int i = 0; i < montako; i++) {
            aktiivinenPelaaja.lisaaKirjain(vapaatKirjaimet.arvoKirjain());
        }
    }
    
    public void vaihdaPelaajanKirjaimet() {
        List<Character> uudet = new ArrayList<>();
        Iterator<Character> iteraattori = vapaatKirjaimet.getKirjainSailio().iterator();
        
        while (iteraattori.hasNext() && uudet.size() < pelaajallaKirjaimia) {            
            uudet.add(vapaatKirjaimet.arvoKirjain());
        }
        
        for (Character c : aktiivinenPelaaja.getOmatKirjaimet()) {
            vapaatKirjaimet.getKirjainSailio().add(c);
        }
        
        aktiivinenPelaaja.setOmatKirjaimet(uudet);        
        arvoPelaajalleKirjaimia();
    }

    public boolean tarkistaKirjaimet(String sana) {
        List<Character> kirjaimet = new ArrayList<>(aktiivinenPelaaja.getOmatKirjaimet());

        for (int i = 0; i < sana.length(); i++) {
            Character kirjain = sana.charAt(i);
            if (kirjaimet.contains(kirjain)) {
                kirjaimet.remove(kirjain);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param merkitys
     * @param pisteet
     */
    public void lisaaSanaPelaajalle(String sana, String merkitys, int pisteet) {
        Sana uusiSana = new Sana(sana, merkitys, pisteet);
        aktiivinenPelaaja.lisaaSana(uusiSana);
        this.viimeisinLuotu = uusiSana;

        if (!sana.isEmpty()) {
            for (int i = 0; i < sana.length(); i++) {
                aktiivinenPelaaja.poistaKirjain(sana.charAt(i));
            }
        }
    }
    
    public void lisaaTyhjaSanaPelaajalle() {
        lisaaSanaPelaajalle("", "", 0);
    }

    public int getKirjaimia() {
        return pelaajallaKirjaimia;
    }

    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    public Pelaaja getAktiivinenPelaaja() {
        return aktiivinenPelaaja;
    }

    public Kirjainvarasto getVapaatKirjaimet() {
        return vapaatKirjaimet;
    }

    public String uusiSanaLuotu() {
        return "Vuoro " + aktiivinenPelaaja.getVuorot()
                + " - " + aktiivinenPelaaja.getNimi()
                + " keksi sanan " + viimeisinLuotu.toString() + "\n\n";
    }

    @Override
    public String toString() {
        String loppu = "";
        int vuoroonSaakka = vahitenVuoroja();
        for (int i = 0; i < pelaajat.size(); i++) {
            int pisteet = pelaajat.get(i).laskePisteet(vuoroonSaakka);
            loppu += pelaajat.get(i).getNimi() + ": "
                    + pisteet + " pistettä\n\n";
        }

        return loppu;
    }

    private int vahitenVuoroja() {
        int vahitenVuoroja = pelaajat.get(0).getVuorot();
        for (int i = 1; i < pelaajat.size(); i++) {
            int vuoroja = pelaajat.get(i).getVuorot();
            if (vuoroja < vahitenVuoroja) {
                vahitenVuoroja = vuoroja;
            }
        }

        return vahitenVuoroja;
    }
}
