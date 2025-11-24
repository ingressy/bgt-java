import java.awt.*;
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

        JMenuBar menuBar = new JMenuBar();

        frame.setTitle("Stundenplan");
        frame.setSize(800, 300);
        table.setBounds(30,40,200,300);

        JScrollPane sp = new JScrollPane(table);
        JMenu sonstiges = new JMenu("Sonstiges");

        JMenuItem aboutItem = new JMenuItem("Über");
        aboutItem.addActionListener(e -> {aboutgui();});

        frame.add(sp);

        menuBar.add(sonstiges);
        sonstiges.add(aboutItem);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void aboutgui() {
        JDialog dialog = new JDialog((Frame) null, "Über", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Stundenplan");
        JLabel version = new JLabel("Version 1.0");
        JLabel author = new JLabel("(c) 2025 Noora");

        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        version.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        author.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        dialog.add(Box.createVerticalStrut(15));
        dialog.add(title);
        dialog.add(version);
        dialog.add(author);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
