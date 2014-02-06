package ahaakkoset.sovelluslogiikka;

import ahaakkoset.sovelluslogiikka.Pelisessio;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PelisessioTest {
    Pelisessio sessio;

    @Before
    public void setUp() {
        sessio = new Pelisessio();
    }
}