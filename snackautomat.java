import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class snackautomat {
    public static void main(String[] args) {
        boolean run = true;

        try (InputStream input = new FileInputStream("/home/ingressy/IdeaProjects/bgt241/src/snackautomat.conf")) {
            Properties props = new Properties();
            props.load(input);

            while (run) {
                byte menuauswahl = menu();
                if (menuauswahl == 9) {
                    System.out.print("Vielen Dank für deine Benutzung!");
                    run = !run;
                } else if (menuauswahl > 0 || menuauswahl < 5) {
                    System.out.println("Eine "+ props.getProperty("produkt"+menuauswahl+"name") + ". OK.");
                    boolean bezahlvorgang = bezahlen(Double.parseDouble(props.getProperty("produkt"+menuauswahl+"preis")));
                    if  (!bezahlvorgang) {
                        System.out.println("Irgendwas ist schiefgelaufen, bitte rufe die Hotline an!\nTel.: 42 42 42 42 ... Error Printing Lines!");
                        run = !run;
                    } else if (bezahlvorgang) {
                        System.out.println("Hier ist deine "+ props.getProperty("produkt"+menuauswahl+"name") + "! OK.");
                    }
                }else if (menuauswahl < 0 ) {
                    System.out.println("Bitte wiederhole deinen Eingabe!");
                    menu();
                }else {
                    System.out.println("Bitte wiederhole deinen Eingabe!");
                    menu();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static byte menu() {
        byte auswahl;
        Scanner menuein = new Scanner(System.in);

        System.out.println("--- SNACKAUTOMAT 3000 ---");
        System.out.println("1. Waffel (1,50€)\n2. Random Katjes (1,08€)\n3. Monster Energy (1,50€)\n4. Kaffee (1,00€)\n5. Conrads 3er Kondom (4,00€)");
        System.out.println("9. Automaten zu beenden.");
        System.out.print("Eingabe: ");
        try {
            auswahl = menuein.nextByte();
            return auswahl;
        } catch (InputMismatchException e) {
            System.out.println("Bitte gebe nur Zahlen ein!");
            return -1;
        }

    }

    protected static boolean bezahlen(double preis) {
        double geld = -1;
        double rueckgeld = -1;

        Scanner geldin = new Scanner(System.in);

        System.out.print("Zu Bezahlen: €"+ preis + " - Bitte Geld einwerfen, bitte KOMMAZAHLEN: ");
        geld = geldin.nextDouble();
        if (geld < 0) {
            System.out.println("OK. Bitte kein negatives Geld einwerfen. Dieses ist nicht möglich!");
             return bezahlen(preis);
        } else if (geld > 2) {
            System.out.println("OK. Bitte kein falsch Geld einwerfen. Dieses ist verboten!");
             return bezahlen(preis);
        } else {
            System.out.println("Geld gegeben: €" + geld);
            if (geld > preis) {
                rueckgeld = geld - preis;
                System.out.println("Rückgeld: €" + rueckgeld);
                return true;
            } else if (geld < preis) {
                return bezahlen((preis-geld));
            } //else if (geld == preis) {
              //  return true;
            //}
            return true;
        }
    }
}
