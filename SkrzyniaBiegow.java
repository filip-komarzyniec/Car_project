package auto;

public class SkrzyniaBiegow extends Komponent{
    Sprzeglo sprzeglo;      // zmienna package private

    private int aktualnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;

// konstruktor
    public SkrzyniaBiegow(String nazwa, float waga, float cena, int iloscBiegow, String snazwa, float swaga, float scena) {
        super(nazwa,waga,cena);     // inicjalizuje atrybuty klasy komponent
        this.sprzeglo = new Sprzeglo(snazwa,swaga,scena);     // inicjalizuje tez sprzeglo
        this.aktualnyBieg = 0;  // bieg na początku to luz
        this.iloscBiegow = iloscBiegow;
        this.aktualnePrzelozenie = 0;   // przełożenie na luzie to 0
    }
    public int zmienBieg(int bieg) throws sprzeglo_exception {
        //try {
            if(sprzeglo.isStanSprzegla() && bieg <= this.iloscBiegow) {
                this.aktualnyBieg = bieg;
                System.out.println("Zmieniłem pomyślnie bieg!");
                return this.aktualnyBieg;
            }
            else if (bieg > this.iloscBiegow){
                System.out.println("Nie ma takiego biegu");
                return this.aktualnyBieg;
            }
            else if(!sprzeglo.isStanSprzegla()) {
                throw new sprzeglo_exception();
            }   // ten wyjątek obsługiwany jest w interfejsie graficznym
        /*} catch (sprzeglo_exception i) {
            i.oCoKaman();       // wypisuje blad, ze sprzeglo niewcisniete
            return this.aktualnyBieg;*/
        //}
        return this.aktualnyBieg;
    }

    public double obliczPrzelozenie() {
        double bieg = this.aktualnyBieg;
        double Ibieg = this.iloscBiegow;
        this.aktualnePrzelozenie = bieg/Ibieg*365.5;
        return this.aktualnePrzelozenie;
    }

// gettery
    public int getAktualnyBieg() {
        return aktualnyBieg;
    }

    public double getAktualnePrzelozenie() {
        return aktualnePrzelozenie;
    }

    public int getIloscBiegow() {
        return iloscBiegow;
    }

    @Override
   public float getWaga() {
        return super.getWaga()+sprzeglo.getWaga();        // suma wag skrzyni biegów + sprzęgła
   }
// settery

    public void setAktualnyBieg(int aktualnyBieg) {
        this.aktualnyBieg = aktualnyBieg;
    }
}
