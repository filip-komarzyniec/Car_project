package auto;

import java.util.Random;

public class Samochod {
    SkrzyniaBiegow skrzynia;
    Silnik silnik;          // package private dostęp

    private String kolor;
    private String nrRejestr;
    private String model;
    private String marka;
    private float predkosc_max;

    // konstruktor
    public Samochod(String skrzNazwa,float skrzWaga,float skrzCena, int skrzBiegi, String sNazwa, float sWaga, float sCena,
                    String silNazwa, float silWaga, float silCena, int silMaxObroty,
                    String kolor, String nrRejestr, String model, String marka, float predkosc_max){

        this.skrzynia = new SkrzyniaBiegow(skrzNazwa,skrzWaga,skrzCena,skrzBiegi,sNazwa,sWaga,sCena);
        this.silnik = new Silnik(silNazwa,silWaga,silCena,silMaxObroty);

        this.kolor = kolor;
        this.nrRejestr = nrRejestr;
        this.model = model;
        this.marka = marka;
        this.predkosc_max = predkosc_max;
    }

    public String wlacz() {
        if(silnik.getObroty() ==0) {
            return silnik.uruchom();
        }
        else {return "Silnik już włączony";}
    }

    public String wylacz() {
        if(silnik.getObroty() >=100) {
            skrzynia.setAktualnyBieg(0); skrzynia.obliczPrzelozenie();        // jeśli wyłączam auto to bieg jest 0 i nowe przełożenie
            return silnik.zatrzymaj();
        }
        else {return "Silnik już wyłączony";}
    }

    public void jazda() {
        System.out.println("jadę!!");
    }

    public float obliczWage() {
        return  silnik.getWaga()+skrzynia.getWaga();    // waga silnika, skrzyni + sprzęgła
    }

    public float aktualnaPredkosc() {
        float curr_speed = 0f;
        Random r = new Random();
        switch (skrzynia.getAktualnyBieg()) {
            default:
                return 0;
            case 1:
                curr_speed = r.nextFloat() * (10);     // losowy float z zakresu [0...10]
                //return "Aktualna prędkość to : "+ String.valueOf(curr_speed);
                return curr_speed;
            case 2:
                curr_speed = r.nextFloat() * (30-10) + 10;  // zakres [10..30]
                return curr_speed;
            case 3 :
                curr_speed = r.nextFloat() * (55-30)  +30; // zakres [30..55]
                return curr_speed;
            case 4 :
                curr_speed = r.nextFloat() * (75-55) + 55; // zakres [55...75]
                return curr_speed;
            case 5 :
                curr_speed = r.nextFloat() * (130-75) + 75;  // zakres [75..130]
                return curr_speed;
            case 6 :
                curr_speed = r.nextFloat() * (210-130) + 130;  // zakres [130..210]
                return curr_speed;
        }
    }

}


