package auto;

import org.junit.Test;

import static org.junit.Assert.*;

public class SilnikTest {

    @Test
    public void zwieksz_obroty() {
        Silnik silnik = new Silnik("sil",400,10000,5000);
        System.out.println("test0:");
        float curr = silnik.zwieksz_obroty(400);
        assertEquals(0,curr,0.1);   // zwiększam obroty bez uruchomionego silnika
        silnik.uruchom();
        System.out.println("test1:");
        curr = silnik.zwieksz_obroty(5000);
        assertEquals(100,curr,0.1); // zwiększam obroty bardziej niż max
        System.out.println("test2:");
        curr = silnik.zwieksz_obroty(400);
        assertEquals(500,curr,0.1); // zwiększam obroty normalnie
    }

    @Test
    public void zmniejsz_obroty() {
        Silnik silnik = new Silnik("sil",500,14000,3000);
        System.out.println("test0:");
        float curr = silnik.zmniejsz_obroty(50);
        assertEquals(0,curr,0.1);       // zmniejszam obroty bez uruchomionego silnika
        silnik.uruchom();
        System.out.println("test1:");
        curr = silnik.zmniejsz_obroty(200);
        assertEquals(100,curr,0.1);     // zmniejszam obroty na ujemne
        System.out.println("test2:");
        curr = silnik.zmniejsz_obroty(50);
        assertEquals(50,curr,0.1);      // zmniejszam obroty normalnie
    }
}