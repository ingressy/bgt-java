public class Bruch {
    public int zaehler;
    public int nenner;

    public Bruch(int z, int n) {
        this.zaehler = z;
        this.nenner = n;
    }

    @Override
    public String toString() {
        return this.zaehler+"/"+this.nenner;
    }

    public static Bruch addiere(Bruch b1, Bruch b2) {
        return new Bruch(((b1.zaehler*b2.nenner)+(b1.nenner* b2.zaehler)),(b1.nenner* b2.nenner));
    }
    public static Bruch subtrahiere (Bruch b1, Bruch b2) {
        return new Bruch((b1.zaehler*b2.nenner- b2.zaehler* b1.nenner), (b1.nenner* b2.nenner));
    }

    public static Bruch multipliziere(Bruch b1, Bruch b2) {
        return new Bruch(b1.zaehler* b2.zaehler, b1.nenner* b2.nenner);
    }

    public static Bruch dividiere(Bruch b1, Bruch b2) {
        return  new Bruch(b1.zaehler* b2.nenner,b2.zaehler*b1.nenner);
    }

    public static Bruch quadiere(Bruch b1) {
        return new Bruch (b1.zaehler*b1.zaehler, b1.nenner* b1.nenner);
    }

    private static Bruch kuerzen(Bruch b1) {
        int teiler = ggT(b1.zaehler, b1.nenner);
        return new Bruch(b1.zaehler / teiler, b1.nenner / teiler);
    }
    private static int ggT (int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main()  {
        Bruch b1 = new Bruch(2,3);
        Bruch b2 = new Bruch(1,4);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println("Addieren " + kuerzen(addiere(b1,b2)));
        System.out.println("Subtrahieren " + kuerzen(subtrahiere(b1,b2)));
        System.out.println("Multiplizieren " + kuerzen(multipliziere(b1, b2)));
        System.out.println("Dividieren " + kuerzen(dividiere(b1, b2)));
        System.out.println("Quadieren " + kuerzen(quadiere(b1)));
    }
}
