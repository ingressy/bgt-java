import java.lang.Math;
import java.util.Scanner;

public class kreisberechnung {
    public static void main (String[] args) {

        Scanner robj = new Scanner(System.in);
        boolean i = false;
        String in = "";
        while (!i) {
            System.out.println("Radius eingeben, mit q beenden:");
            in = robj.nextLine();

            if (isinputnum(in)) {
                double r = Double.parseDouble(in);
                System.out.println("r = " + r);
                System.out.println("A = " + rechnung(r));
            } else {
                if (in.equalsIgnoreCase("q")) {
                    robj.close();
                    break;
                } else {
                    System.out.println("Bitte probiere es noch mal!");
                }
            }
        }
    }
    private static double rechnung (double r) {
        return  java.lang.Math.PI*Math.pow(r, 2);
    }
    private static boolean isinputnum(String in) {
        try {
            Double.parseDouble(in);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
