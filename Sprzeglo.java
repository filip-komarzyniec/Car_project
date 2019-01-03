package auto;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;  // z początku niewciśnięte

    // konstruktor
    public Sprzeglo(String nazwa, float waga, float cena) {
        super(nazwa, waga,cena);        // inicjalizueje atrybuty klasy komponent
        stanSprzegla = false;   // na początku sprzęgło nie jest wcisniete
    }

    public boolean zalacz (){
        this.stanSprzegla = true;   // sprzeglo wcisniete
        return stanSprzegla;
    }
    public boolean rozlacz (){      // sprzeglo wcisniete
        this.stanSprzegla = false;
        return stanSprzegla;
    }

    public boolean isStanSprzegla() {
        return stanSprzegla;
    }
}
