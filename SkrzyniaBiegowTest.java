package auto;

import org.junit.Test;

import static org.junit.Assert.*;

public class SkrzyniaBiegowTest {

    @Test
    public void zmienBieg() throws sprzeglo_exception {
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("skrzyn",400,5000,6,"sprzeg",450,700);
        System.out.println("test1:");
        int curr = skrzynia.zmienBieg(1);
        assertEquals(0,curr);       // zmieniam bieg bez zalaczenia sprzegla
        System.out.println("test2:");
        skrzynia.sprzeglo.zalacz();
        curr = skrzynia.zmienBieg(2);
        assertEquals(2,curr);       // wciskam sprzeglo i zmieniam bieg
        System.out.println("test3:");
        curr = skrzynia.zmienBieg(7);
        assertEquals(2,curr);       // zmieniam bieg na większy niż max ilość biegów
    }

    @Test
    public void obliczPrzelozenie() throws sprzeglo_exception{
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("skrzyn",400,5000,8,"sprzeg",450,700);
        System.out.println("test1:");
        skrzynia.sprzeglo.zalacz(); // wciskam sprzęgło
        skrzynia.zmienBieg(5);
        double curr = skrzynia.obliczPrzelozenie();
        assertEquals(228.5,curr,0.1);       // liczę przełożenie 5. biegu -> 5/8*365.6, +- 0.1
    }
}