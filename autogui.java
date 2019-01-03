package auto;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Hashtable;


public class autogui {

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton stwórzSamochódButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JButton OBROTYButton;
    private JButton OBROTYButton1;
    private JButton WCIŚNIJButton;
    private JButton ODPUŚĆButton;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JSlider slider1;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JTextField textField25;
    private JTextField textField26;
    private JTextField textField27;
    private JTextField textField28;
    private JTextField textField29;
    private JTextField textField30;
    private JTextField textField31;
    private JButton WYLACZButton;
    private JButton ODPALButton;
    private JTextField textField32;

    private Samochod samochod;
    private Hashtable table;
    private Thread thread;      // wątek z obrotami

    public autogui() {
        GhostText duch = new GhostText(textField13,"nie więcej niż 210");
        stwórzSamochódButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tworzę obiekt samochód przy naciśnięciu przycisku
                samochod = new Samochod(textField2.getText(), Float.parseFloat(textField3.getText()), Float.parseFloat(textField4.getText())
                        , Integer.parseInt(textField5.getText()), textField6.getText(), Float.parseFloat(textField7.getText()), Float.parseFloat(textField8.getText()),
                        textField1.getText(), Float.parseFloat(textField21.getText()), Float.parseFloat(textField22.getText()), Integer.parseInt(textField23.getText()),
                        textField9.getText(), textField10.getText(), textField11.getText(), textField12.getText(),
                        Float.parseFloat(textField13.getText()));

                // komunikat o powodzeniu
                JOptionPane.showMessageDialog(null,
                        "STWORZONO SAMOCHÓD",
                        "SUCCESS",
                        JOptionPane.INFORMATION_MESSAGE);

                // dodaje obserwatora
                samochod.silnik.addObserver(textField32);
                //System.out.println(samochod.silnik.getObservers());
                // uaktualniam wszystkie stałe wartości w zakładce "stan samochodu"
                textField16.setText(textField23.getText()); // max obroty
                textField16.setEditable(false);
                textField18.setText(textField5.getText());     // ilość biegów
                textField18.setEditable(false);
                textField14.setText(textField9.getText());  // kolor auta
                textField14.setEditable(false);
                textField24.setText(textField10.getText());     // nr rejestracyjny auta
                textField24.setEditable(false);
                textField25.setText(textField11.getText());     // marka auta
                textField25.setEditable(false);
                textField26.setText(textField12.getText());     // model auta
                textField26.setEditable(false);
                textField28.setText(textField13.getText());     // max prędkość auta
                textField28.setEditable(false);
                textField27.setText(String.valueOf(samochod.obliczWage()));     // waga samochodu
                textField27.setEditable(false);

                table.put(samochod.skrzynia.getIloscBiegow(),new JLabel("MAX"));
                slider1.setLabelTable(table);
                slider1.revalidate();       // odświeżenie slidera
                // ustawiam a później uaktualniam zmienne wartości w zakładce "stan samochodu"
                Timer update = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {        // przy każdej zmianie biegów ma liczyć przełożenie
                        textField17.setText(String.valueOf(samochod.silnik.getObroty()));
                        textField17.setEditable(false);
                        textField20.setText(String.valueOf(samochod.skrzynia.getAktualnyBieg()));
                        textField20.setEditable(false);
                        textField19.setText(String.valueOf(samochod.skrzynia.getAktualnePrzelozenie()));
                        textField19.setEditable(false);
                        if(samochod.skrzynia.sprzeglo.isStanSprzegla()) {
                            textField15.setText("wciśnięte");
                            textField15.setEditable(false);
                        }
                        else {
                            textField15.setText("nie wciśnięte");
                            textField15.setEditable(false);
                        }
                    }
                });
                Timer update_quick = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField29.setText(String.valueOf(samochod.aktualnaPredkosc()));
                        textField29.setEditable(false);

                    }
                });
                update.start();
                update_quick.start();
            }

        });
        // włączam silnik
        ODPALButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = samochod.wlacz();
                if (msg.equals("Silnik już włączony")) {
                    JOptionPane.showMessageDialog(null,
                            "SILNIK JEST już WŁĄCZONY",
                            "WARNING",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    thread = new Thread(samochod.silnik);
                    samochod.silnik.setBlinker(true);
                    thread.start();}  // uruchamiam wątek
            }
        });
        //wyłączam silnik
        WYLACZButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = samochod.wylacz();
                if (msg.equals("Silnik już wyłączony")) {
                    JOptionPane.showMessageDialog(null,
                            "SILNIK JEST już WYŁĄCZONY",
                            "WARNING",
                            JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    try {
                        samochod.silnik.setBlinker(false);      // wyłączam dodatkowy wątek
                        Thread.sleep(1000);     // czekam tu, żeby w metodzie run() zresetowały się obroty2 na 0
                        textField32.setText(String.valueOf(samochod.silnik.getObroty2())); // ustawiam rzeczywiste obroty na 0 po zakończeniu wątku
                        textField32.setEditable(false);
                        slider1.setValue(0);        // ustawiam bieg na 0 "luz"
                        thread = null;                          // zeruję pole
                        System.out.println("wątek zabity");
                    }catch (InterruptedException a ){}
                }
            }
        });

        // zwiększam obroty
        OBROTYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (samochod.silnik.getObroty() < 100) {
                    // okienko komunikatu -> silnik nie jest włączony
                    JOptionPane.showMessageDialog(null,
                            "SILNIK NIE JEST WŁĄCZONY",
                            "WARNING",
                            JOptionPane.PLAIN_MESSAGE);
                } else if (samochod.silnik.getObroty() + Float.parseFloat(textField30.getText()) > samochod.silnik.getMax_obroty()) {
                    // komunikat, obroty nie mogą przekroczyć max obrotów
                    JOptionPane.showMessageDialog(null,
                            "OBROTY NIE MOGĄ PRZEKROCZYc MAX OBROTÓW",
                            "WARNING",
                            JOptionPane.PLAIN_MESSAGE);
                } else {      //wszystko normalnie, silnik włączony
                    samochod.silnik.zwieksz_obroty(Float.parseFloat(textField30.getText()));
                    textField30.setText("");        // czyszczę okienko z tekstu po zwiększeniu obrotów
                    System.out.println("obroty: "+samochod.silnik.getObroty());
                }
            }
        });
        // zmniejszam obroty
        OBROTYButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (samochod.silnik.getObroty() <= 100) {
                    // nie można nic zrobić, bo wyłączony samochód
                    JOptionPane.showMessageDialog(null,
                            "SILNIK NIE JEST WŁĄCZONY",
                            "WARNING",
                            JOptionPane.PLAIN_MESSAGE);
                } else if (samochod.silnik.getObroty() - Float.parseFloat(textField31.getText()) < 100) {
                    JOptionPane.showMessageDialog(null,
                            "OBROTY NIE MOGĄ PRZEKROCZYC MIN OBROTÓW (100)",
                            "WARNING",
                            JOptionPane.PLAIN_MESSAGE);
                } else {  // wszystko okej, zmniejsz obroty
                    samochod.silnik.zmniejsz_obroty(Float.parseFloat(textField31.getText()));
                    textField31.setText("");        // czyszczę okienko z tekstu po zmniejszeniu obrotów
                }
            }
        });
        // tworze slider

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {           // jeśli zmieniam na nieistaniejący bieg
                    if (slider1.getValue() > samochod.skrzynia.getIloscBiegow()) {
                        JOptionPane.showMessageDialog(null,
                                "Nie ma takiego biegu",
                                "WARNING",
                                JOptionPane.WARNING_MESSAGE);
                        slider1.setValue(samochod.skrzynia.getIloscBiegow());   // wracam kursorem na MAX bieg
                    }
                    // resetowanie slidera
                    else if(samochod.silnik.getObroty()==0 && slider1.getValue()==0 || !samochod.skrzynia.sprzeglo.isStanSprzegla() && slider1.getValue()==0){
                        ; // pass, gdy wyłączony i wracam na 0 albo gdy sprzęgło niewciśnięte i wracam na 0

                    }
                    else if (samochod.silnik.getObroty() >=100){        // normalnie zmieniam bieg i liczę przełożenie
                        samochod.skrzynia.zmienBieg(((JSlider) e.getSource()).getValue());
                        samochod.skrzynia.obliczPrzelozenie();
                    }
                    else if (samochod.silnik.getObroty()==0){      // kiedy auto nie jest uruchomione
                        JOptionPane.showMessageDialog(null,
                                "włącz AUTO !!!!",
                                "WARNING",
                                JOptionPane.WARNING_MESSAGE);
                        slider1.setValue(0);        // resetuję do początkowego położenia

                    }
                }
                catch (sprzeglo_exception i) {      // kiedy nie jest wciśnięte sprzęgło
                    i.oCoKaman();   // wypisuję błąd
                    JOptionPane.showMessageDialog(null,
                            "WCIŚNIJ SPRZĘGŁO!!!",
                            "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                    slider1.setValue(0);        // resetuję do początkowego położenia
                }
            }
        });
        // wciskam sprzęgło
        WCIŚNIJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!samochod.skrzynia.sprzeglo.isStanSprzegla()) {
                    samochod.skrzynia.sprzeglo.zalacz();
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "SPRZĘGŁO JEST JUŻ WCIŚNIĘTE",
                            "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // odpuszczam sprzęgło
        ODPUŚĆButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (samochod.skrzynia.sprzeglo.isStanSprzegla()) {
                    samochod.skrzynia.sprzeglo.rozlacz();
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "SPRZĘGŁO JUŻ NIE JEST WCISNIĘTE!!!",
                            "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

    public static void main(String[] args) {        // tworzę ramkę interfejsu
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (UnsupportedLookAndFeelException e){
            System.out.println("fuckup 1");
        }catch (ClassNotFoundException e){
            System.out.println("fuckup2");
        }catch (InstantiationException e){
            System.out.println("fakup3");
        }catch (IllegalAccessException e){
            System.out.println("fakup4");
        }
        JFrame frame = new JFrame("autogui");
        frame.setContentPane(new autogui().tabbedPane1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

   private void createUIComponents() {
       // TODO: place custom component creation code here
       DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(0, 0, 0, 10);
       slider1 = new JSlider(model);
       slider1.setOrientation(JSlider.HORIZONTAL);
       slider1.setValue(0);            // wartość startowa
       slider1.setMinorTickSpacing(1);     // linie co 1 bieg
       slider1.setPaintTicks(true);        // pokazuj linie odstępów co 1 bieg
       table = new Hashtable();
       table.put(0,new JLabel("MIN"));

   }

}