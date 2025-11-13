import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class temperatur_messung {
    static int[] ara_ara;  //Array

    private static double Mittelwert(int[] ara_ara) {
        double mittelwert = 0;

        for (int i = 0; i < ara_ara.length; i++) {
            mittelwert = ara_ara[i] + mittelwert;
        }
        return Math.round((mittelwert / ara_ara.length) * 100.0) / 100.0;
    }

    private static double Median(int[] ara_ara) {
        megaBubbleSortDeluxxe(ara_ara);

        if (ara_ara.length % 2 == 0) {
            return (ara_ara[ara_ara.length/2-1] + ara_ara[ara_ara.length/2]) / 2.0;
        } else {
            return ara_ara[ara_ara.length/2];
        }
    }

    private static void megaBubbleSortDeluxxe(int[] ara_ara) {
        long startTime = System.nanoTime();
        for(int i = 0; i < ara_ara.length-1; i++){
            for(int ai = 0; ai< ara_ara.length-1; ai++ ) {
                if(ara_ara[ai] > ara_ara[ai+1]){ //Schlechtfall: Tauschen, musste arbeiten
                    //System.out.println(ara_ara[ai]);
                    int temp = 0;
                    temp = ara_ara[ai];
                    ara_ara[ai] = ara_ara[ai+1];
                    ara_ara[ai+1] = temp;
                }
            }
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        System.out.println(Arrays.toString(ara_ara));
        System.out.println("Zeit: "+ TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS) +"MS");
        //return new int[ara_ara.length];
    }

    private static int Messung() {
        Random rand = new Random();
        return rand.nextInt(-10, 2000005);
    }

    public static void main(String[] args) {
        ara_ara = new int[20600];//von 0-23



        for (int i = 0; i<ara_ara.length; i++) {
            ara_ara[i] = Messung();
        }

        for(int i = 0; i< ara_ara.length; i++ ){
            System.out.println("Messwert " + i + " "+ ara_ara[i]);
        }

        System.out.println(Arrays.toString(ara_ara));
        double Mittelwert = Mittelwert(ara_ara);
        double Median = Median(ara_ara);
        System.out.println("Mittelwert: " + Mittelwert);
        System.out.println("Median: " + Median);
        System.out.println("Differenz: " + (Mittelwert-Median));

    }
}
