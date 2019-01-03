package auto;

public abstract class Komponent {
    // abstrakcyjna, bo tylko do dziedziczenia, nie tworze obiektu komponent
    private String nazwa;
    private float waga;
    private float cena;


// konstruktor
    public Komponent(String nazwa, float waga, float cena) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public float getWaga() {
        return waga;
    }

    public float getCena() {
        return cena;
    }
}
