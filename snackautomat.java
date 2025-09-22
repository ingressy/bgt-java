import java.util.Scanner;

public class snackautomat {
    public static void main(String[] args) {
        boolean run = true;

        while (run) {
            byte menuauswahl = menu();
            if (menuauswahl == 9)  {
                System.out.print("Vielen Dank für deine Benutzung!");
                run = !run;
            } else if (menuauswahl == 1 ) {
                System.out.println("Eine Waffel. OK.");
                boolean bezahlvorgang = bezahlen(1.50);
                if  (bezahlvorgang == false) {
                    System.out.println("Irgendwas ist schiefgelaufen, bitte rufe die Hotline an!\nTel.: 42 42 42 42 ... Error Printing Lines!");
                    run = !run;
                }

            }else if (menuauswahl == 2 ) {
                System.out.println("Auswahl: " + menuauswahl);
            }else if (menuauswahl == 3 ) {
                System.out.println("Auswahl: " + menuauswahl);
            }else if (menuauswahl == 4 ) {
                System.out.println("Auswahl: " + menuauswahl);
            }else if (menuauswahl < 0 ) {
                System.out.println("Bitte wiederhole deinen Eingabe!");
                menu();
            }else {
                System.out.println("Bitte wiederhole deinen Eingabe!");
                menu();
            }
        }
    }

    protected static byte menu() {
        byte auswahl;
        Scanner menuein = new Scanner(System.in);

        System.out.println("--- SNACKAUTOMAT 3000 ---");
        System.out.println("1. Waffel (1,50€)\n2. Random Katjes (1,08€)\n3. Monster Energy (1,50€)\n4. Kaffee (1,00€)");
        System.out.println("9. Automaten zu beenden.");
        System.out.print("Eigabe: ");
        auswahl = menuein.nextByte();

        return auswahl;
    }
    protected static boolean bezahlen(double preis) {
        double geld = -1;
        double rueckgeld = -1;
        double newgeld = -1;

        Scanner geldin = new Scanner(System.in);

        System.out.print("Zu Bezahlen: €"+ preis + " - Bitte Geld einwerfen, bitte KOMMAZAHLEN: ");
        geld = geldin.nextDouble();
        System.out.println("Geld gegeben: €" + geld);
        if (geld > preis) {
            rueckgeld = geld - preis;
            System.out.println("Rückgeld: €" + rueckgeld);
            return true;
        } else if (geld < preis) {
            newgeld = preis - geld;
            bezahlen(newgeld);
        } else if (geld == preis) {
            return true;
        }
        return false;
    }
}
