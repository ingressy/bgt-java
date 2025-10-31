import java.util.Arrays;
import java.util.Random;

public class temperatur_messung {
    static int[] ara_ara;  //Array

    private static double Mittelwert(int[] ara_ara) {
        double mittelwert = 0;

        for (int i = 0; i < 24; i++) {
            mittelwert = ara_ara[i] + mittelwert;
        }
        return Math.round((mittelwert / ara_ara.length) * 100.0) / 100.0;
    }

    private static double Median(int[] ara_ara) {
        Arrays.sort(ara_ara);
        int n = ara_ara.length;

        if (n % 2 == 0) {
            return (ara_ara[n/2-1] + ara_ara[n/2]) / 2.0;
        } else {
            return ara_ara[n/2];
        }
    }

    private static int Messung() {
        Random rand = new Random();
        return rand.nextInt(-10, 50);
    }

    public static void main(String[] args) {
        ara_ara = new int[24]; //von 0-23


        for (int i=0; i<24; i++) {
            ara_ara[i] = Messung();
        }

        for(int i = 0; i<24; i++ ){
            System.out.println("Messwert " + i + " "+ ara_ara[i]);
        }
        double Mittelwert = Mittelwert(ara_ara);
        double Median = Median(ara_ara);
        System.out.println("Mittelwert: " + Mittelwert);
        System.out.println("Median: " + Median);
        System.out.println("Differenz: " + (Mittelwert-Median));

    }
}
