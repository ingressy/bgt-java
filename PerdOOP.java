import java.util.Objects;

public class PerdOOP {
    public static void main() {
        PerdOOP mika = new PerdOOP("Richard Frosch der Kleine", "Fiat", 167, 0, 69.0);
        System.out.println(mika);
        System.out.println("Das Pferd hat was mit " + mika.Isst("Apfel")+ " kcal gegessen!");
        System.out.println(mika.Laeft(10));
        System.out.println(mika);
        System.out.println("Das Pferd hat was mit " + mika.Isst("Lassage")+ " kcal gegessen!");
        System.out.println(mika.Laeft(10));
        System.out.println(mika.toLasagne());

    }

    //Attribute
    private String name;
    private final String marke;
    private int hoechsg;
    private int kcal;
    private double pstark;
    public int kcalproh = 172000;

    public PerdOOP(String n, String m, int h, int k, double p) {
        this.name = n;
        this.marke = m;
        this.hoechsg = h;
        this.kcal = k;
        this.pstark = p;
    }

    public String Laeft(int km) {
        double t_h = (double) km / this.hoechsg; // km ÷ km/h = h
        double kneed = kcalproh * t_h;

        if (kneed > this.kcal) {
            double maxStrecke = this.kcal * this.hoechsg / kcalproh;
            return "Das Pferd " + this.name + " würde " + km + " km laufen, hat aber nur " + this.kcal + " kcal und schafft nur " + String.format("%.2f", maxStrecke) + " km";
        } else {
            this.kcal -= kneed;
            return "Das Pferd " + this.name + " läuft " + km + " km und verbraucht " + String.format("%.2f", kneed) + " kcal";
        }
    }

    public int Isst(String Essen) {
        int kEssen = 0;
        if (Objects.equals(Essen, "Apfel")) {
            kEssen = 65;
            this.kcal += kEssen;
            return kEssen;
        }
        if (Objects.equals(Essen, "Lassage")) {
            kEssen = 2500;
            this.kcal += kEssen;
            return kEssen;
        }
        return kEssen;
    }

    public String toLasagne() {
        if (this.kcal > 0) {
            return "Lassagne, lecker lecker Lassagne";
        }
        return "Nicht möglich!";
    }

    @Override
    public String toString() {
        return "Das Pferd heißt " + this.name + ",hat die Marke " + this.marke + ", läuft " + this.hoechsg +"km/h und hat "+  this.kcal + " Kalorien und hat " + this.pstark + "PS";
    }
}

