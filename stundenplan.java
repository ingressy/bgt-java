import java.awt.*;
import java.net.URI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class stundenplan {
    static void main() {
        gui();
        String[][] stundenplan = new String[7] [5];
    }

    private static void gui() {
        String[] columnNames = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
        String[][] data = { {"Mathe", "", "", "", ""},
                {"Darstellendes Spiel", "", "", "", ""},
                {"bla", "", "", "", ""},
                {"bla", "", "", "", ""},
                {"bla", "", "", "", ""}};

        JFrame frame = new JFrame();

        edittable model = new edittable(data, columnNames);
        JTable table = new JTable(model);

        JMenuBar menuBar = new JMenuBar();

        frame.setTitle("Stundenplan");
        frame.setSize(800, 300);
        table.setBounds(30,40,200,300);

        JScrollPane sp = new JScrollPane(table);
        JMenu sonstiges = new JMenu("Sonstiges");
        JMenu file = new JMenu("Datei");
        JMenu einstellung = new JMenu("Einstellung");

        JMenuItem aboutItem = new JMenuItem("Über");
        aboutItem.addActionListener(e -> {aboutgui();});
        
        JMenuItem githubItem = new JMenuItem("Code");
        githubItem.addActionListener(e -> {    try {Desktop.getDesktop().browse(URI.create("https://github.com/ingressy/bgt-java/blob/main/stundenplan.java"));} catch (Exception ex) {ex.printStackTrace();}});
        
        JCheckBoxMenuItem datumItem = new JCheckBoxMenuItem("Datum anzeigen");
        datumItem.setState(false);
        datumItem.addActionListener(e -> {});
        
        JMenuItem saveItem = new JMenuItem("Speichern");
        
        JCheckBoxMenuItem schreibenItem = new JCheckBoxMenuItem("Beschreibbar");
        schreibenItem.setState(false);
        schreibenItem.addActionListener(e -> model.setEditableCustom(schreibenItem.getState()));

        frame.add(sp);

        menuBar.add(file);
        menuBar.add(einstellung);
        menuBar.add(sonstiges);

        file.add(saveItem);

        einstellung.add(datumItem);
        einstellung.add(schreibenItem);

        sonstiges.add(githubItem);
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
        JLabel author = new JLabel("(c) 2017-2025 Noora");

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

class edittable extends DefaultTableModel {
    private boolean editable = false;

    public edittable(Object[][] data, Object[] columns) {
        super(data, columns);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return editable;
    }

    public void setEditableCustom(boolean edit) {
        editable = edit;
    }
}
