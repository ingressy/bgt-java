import java.util.InputMismatchException;
import java.util.Scanner;

public class konto {
    private final String kontonummer;
    private double kontostand;

    public konto(String kontonummer, double kontostand) {
        this.kontonummer = kontonummer;
        this.kontostand = kontostand;
    }

    public String getKontonummer() {
        return kontonummer;
    }
    public double getKontostand() {
        return kontostand;
    }

    public void einzahlen(double geld) {
        kontostand = kontostand + geld;
    }
    public void auszahlen(double geld) {
        kontostand = kontostand - geld;
    }

    @Override
    public String toString() {
        return "Kontonummer: " + kontonummer + ", Kontostand: "+ kontostand+ "€";
    }

    public static void main() {
        boolean run = true;
        byte auswahl;
        konto k1 = null;

        while (run) {
            Scanner input = new Scanner(System.in);
            System.out.println("NooraBank | Geldautomat");
            System.out.println("1. Konto erstellen\n2. Einzahlen\n3. Auszahlen\n9. Exit\n10. Erste Aufgabe");
            System.out.print("Eingabe: ");

            try {
                auswahl = input.nextByte();
                if (auswahl == 1) {
                    Scanner erstelleninput = new Scanner(System.in);
                    try {
                        System.out.print("Kontonummer:");
                        String nummer = erstelleninput.next();
                        System.out.print("Kontostand:");
                        Double kontostand = erstelleninput.nextDouble();
                        k1 = new konto(nummer, kontostand);
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte eine Kontostand");
                    }
                } else if (auswahl == 2) {
                    if (k1 == null) {
                        System.out.println("Kein Konto erstellt!");
                    }
                    Scanner einzahleninput = new Scanner(System.in);
                    try {
                        System.out.print("Einzahlen, in € ");
                        Double geld = einzahleninput.nextDouble();
                        k1.einzahlen(geld);
                        System.out.println(k1);
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte nur Geld angeben!");
                    }
                } else if (auswahl == 3) {
                    if (k1 == null) {
                        System.out.println("Kein Konto erstellt!");
                    }
                    Scanner auszahleninput = new Scanner(System.in);
                    try {
                        System.out.print("Auzahlsumme, in €");
                        Double geld = auszahleninput.nextDouble();
                        k1.auszahlen(geld);
                        System.out.println(k1);
                    } catch (InputMismatchException e) {
                     System.out.println("Bitte ein Geldbetrag angeben!");   
                    }
                } else if (auswahl == 9) {
                    run = !run;
                } else if (auswahl == 10) {
                    konto konto1 = new konto("0000000001", 1000.00);
                    konto1.einzahlen(500.00);
                    System.out.println(konto1);
                    konto1.auszahlen(750.00);
                    System.out.println(konto1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Bitte gebe nur Zahlen ein!");
            }
        }

    }
}
