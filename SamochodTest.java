package auto;

import org.junit.Test;

import static org.junit.Assert.*;

public class SamochodTest {

    @Test
    public void obliczWage() {
        Samochod samochod = new Samochod("skrzynia",5000,4000,7,"sprzeglo",
                3555,3876,"silnik",6890,14000,8000,
                "czerwony","979","camaro","porshe",320);
        System.out.println("test1:");
        float weight = samochod.obliczWage();
        assertEquals(15445,weight,0.1);     // tworzę obiekt samochód i liczę wagę elementów
    }

    @Test
    public void aktualnaPredkosc() {
    }

    @Test
    public void wlacz() {
        Samochod samochod = new Samochod("skrzynia",5000,4000,7,"sprzeglo",
                3555,3876,"silnik",6890,14000,8000,
                "czerwony","979","camaro","porshe",320);
        System.out.println("test1:");
        String msg = samochod.wlacz();
        assertTrue("włączenie samochodu nie działa","Silnik uruchomiony".equals(msg));  // właczam wyłączony samochód
        System.out.println("test2:");
        msg = samochod.wlacz();
        assertFalse("włączenie samochodu działa, a nie powinno","Silnik uruchomiony".equals(msg));  // właczam włączony samochód
    }

    @Test
    public void wylacz() {
        Samochod samochod = new Samochod("skrzynia",5000,4000,7,"sprzeglo",
                3555,3876,"silnik",6890,14000,8000,
                "czerwony","979","camaro","porshe",320);
        System.out.println("test1:");
        String msg = samochod.wlacz();
        assertTrue("włączenie samochodu działa","Silnik uruchomiony".equals(msg));  // wyłączam włączony samochód
        System.out.println("test2:");
        msg = samochod.wylacz();
        assertFalse("Włączenie samochodu działa, a nie powinno","Silnik uruchomiony".equals(msg));  // wyłączam wyłączony samochód
    }
}