import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class kopfrechnetrainer {
    public static void main(String[] args) {
        boolean run = true;
        int anzahl = 0;
        Scanner input = new Scanner(System.in);

        Random r= new Random();

        System.out.println("Additionskopfrecher auf Geschwindigkeit");

        while(run) {
            int eingabe;
            int r1 = r.nextInt(-100,100);
            int r2 = r.nextInt(-100,100);
            int solve = r1+r2;

            long startTime = System.nanoTime();
            System.out.print(r1 +" + "+r2 +" = " );
            try {
                eingabe = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Bitte nur Ganzezahlen eingeben:");
                eingabe = input.nextInt();
            }

            if (eingabe == solve) {
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;

                System.out.println("Richtig!");
                System.out.println("Benötigte Zeit: "+ TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS) +"sec");
                anzahl++;
            }else {
                System.out.println("Das war leider die falsche Antwort! Richtig wäre: " + solve + " gewesen. Richtige Antworten:" + anzahl);
                run = false;
            }
        }
    }
}
