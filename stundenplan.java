import java.awt.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;

public class stundenplan {
    static void main() {

        gui();

        String[][] stundenplan = new String[7] [5];

        for (int i= 0; i < stundenplan[i].length; i++) {
            if (i == 0) {
                System.out.println("Mon");
            } else if (i == 1) {
                System.out.println("Die");
            }else if (i == 2) {
                System.out.println("Mit");
            }else if (i == 3) {
                System.out.println("Don");
            }else if (i == 4) {
                System.out.println("Fri");
            }
            for (int j = 0; j < stundenplan[i].length; j++) {
                System.out.println(stundenplan[i][j]);
            }
        }
        add_lession();
    }
    private static void add_lession() {
        Scanner in = new Scanner(System.in);
        System.out.println("Möchtest du eine neue Stunde hinzufügen?");
    }
    private static void gui() {

        String[] columnNames = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
        String[][] data = { {"Mathe", "", "", "", ""},
                {"Darstellendes Spiel", "", "", "", ""},
                {"bla", "", "", "", ""},
                {"bla", "", "", "", ""},
                {"bla", "", "", "", ""}};

        JFrame frame = new JFrame();
        JTable table = new JTable(data, columnNames);

        frame.setTitle("Stundenplan");
        frame.setSize(800, 300);
        table.setBounds(30,40,200,300);

        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
