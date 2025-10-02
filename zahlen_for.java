public class zahlen_for {
    public static void main (String[] args) {
        int zahl;
        boolean erste = false;

        System.out.println("Zahl ist durch drei teilbar:");
        for (zahl= 1; zahl<101; zahl++) {
            if (zahl % 3 == 0) {
                if (erste) System.out.print(", ");
                System.out.print(zahl);
                erste = true;
            }
        }
        System.out.println("\nZahl ist nicht durch fünf aber durch 4 teilbar:");
        erste = false;
        for (zahl= 1; zahl<101; zahl++) {
            if (zahl % 4 == 0) {
                if (zahl % 5 != 0) {
                    if (erste) System.out.print(", ");
                    System.out.print(zahl);
                    erste = true;
                }
            }
        }
        System.out.println("\nZahl ist eine Primzahl");
        erste = false;

        outer:
        for (zahl = 2; zahl<101; zahl++) {
            for (int i = 2; i <= Math.sqrt(zahl); i++) {
                if (zahl % i == 0) {
                    continue outer;
                }
            }
            if (erste) System.out.print(", ");
            System.out.print(zahl);
            erste = true;
        }
    }
}
