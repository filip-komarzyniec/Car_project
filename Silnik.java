package auto;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import org.junit.rules.Timeout;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Silnik extends Komponent implements Runnable {
    private int max_obroty;
    private volatile float obroty;

    private boolean blinker;
    private volatile float obroty2;      // to obserwuję
    private ArrayList<JTextField> observers = new ArrayList<JTextField>();  // lista obserwatorów


    // konstruktor
    public Silnik(String nazwa, float waga, float cena, int max_obroty) {
        super(nazwa, waga, cena);         // inicjalizuje atrybuty klasy komponent
        this.max_obroty = max_obroty;
        this.obroty = 0;
    }

    public String uruchom() {
        this.obroty = 100;        // na początku obroty są niskie przy włączeniu
        return "Silnik uruchomiony";
    }

    public String zatrzymaj() {
        this.obroty = 0;        // przy wyłączeniu obroty silnika ustają
        return "Silnik zatrzymany";
    }


    public float zwieksz_obroty(float obroty) {
        // obroty nie mogą przekroczyc maksymalnych obrotów
        if (!(this.obroty >= 100)) {              // nie można nic zrobić jak silnik wyłączony
            System.out.println("Silnik nie uruchomiony");
            return this.obroty;
        } else if (this.obroty + obroty > this.max_obroty) {
            System.out.println("obroty nie mogą przekroczyć max obrotów");
            return this.obroty;
        } else {
            this.obroty += obroty;
            System.out.println("Zwiększono obroty!");
            return this.obroty;
        }
    }

    public float zmniejsz_obroty(float obroty) {
        if (!(this.obroty >= 100)) {         // nie można nic zrobić jak silnik wyłączony
            System.out.println("Silnik nie uruchomiony");
            return this.obroty;
        }
        // obroty nei mogą być ujemne
        else if (this.obroty - obroty < 0) {
            System.out.println("Obroty nie mogą być ujemne");
            return this.obroty;
        } else {
            this.obroty -= obroty;
            System.out.println("Zmniejszono obroty!");
            return this.obroty;
        }
    }

    // gettery
    public int getMax_obroty() {
        return this.max_obroty;
    }

    public float getObroty() {
        return this.obroty;
    }

    public ArrayList<JTextField> getObservers() {
        return observers;
    }

    public float getObroty2() {
        return obroty2;
    }

    // settery
    public void setObroty(float obroty) {
        this.obroty = obroty;
    }

    public void setBlinker(boolean blinker) {
        this.blinker = blinker;
    }

    // nowy wątek
    @Override
    public void run() {
        System.out.println("Uruchomiono kolejny wątek eloeloe");
        Random r = new Random();
        while (blinker) {
            if (this.obroty > 110 && this.obroty + 10 < this.max_obroty) {
                int start = (int) (obroty) - 10;
                int stop = (int) (obroty) + 10;
                obroty2 = r.nextFloat() * (stop - start) + start;
            }
            else if (this.obroty == 100) {      // jeśli obroty 100 to oscylacja tylko +10 w góre
                int stop = (int) (obroty) + 10;
                obroty2 = r.nextFloat() * (stop - 100) + 100;
            }
            else if (this.obroty == this.max_obroty) {   // jeśl iobroty max to oscylacja tylko -10 w dół
                int start = (int) (obroty) - 10;
                obroty2 = r.nextFloat() * ((this.max_obroty) - start) + start;
            }
            try {
                notifyObservers();       // powiadamiam obserwatora o zmianie
                Thread.sleep(1000);     //  zmiana obrotów co 1s
            } catch (java.lang.InterruptedException i) { System.out.println("przerwano"); }
        }
        System.out.println("wychodzę z while'a");
        obroty2 = 0;
    }

    public void stopThread(){
        blinker = false;
    }
// observer pattern

    public void addObserver(JTextField observer) {
        observers.add(observer);
    }
    public void removeObserver(JTextField observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (JTextField observer:
             observers) {
            observer.setText(String.valueOf(obroty2));     // uaktualniam zmienione obroty
            observer.setEditable(false);
        }
    }

// TEST
    public static void main(String[] args) {
        Silnik silnik = new Silnik("sil",789,7987,5000);
        Thread thread = new Thread(silnik);
        silnik.blinker = true;
        thread.start();
        silnik.uruchom();
        try {
            silnik.zwieksz_obroty(20);
            Thread.sleep(10000);
        }catch (InterruptedException i){}
        silnik.zmniejsz_obroty(20);
        silnik.blinker = false;
    }
}
