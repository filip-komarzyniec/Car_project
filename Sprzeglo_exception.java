package auto;

public class Sprzeglo_exception extends Exception {
    public Sprzeglo_exception() {}      // pusty konstruktor

    public void oCoKaman() {
        System.out.println("Nie masz wciśniętego sprzęgła!!");
    }
}
