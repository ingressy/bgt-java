package gui;

import java.net.URI;
import java.nio.file.Files;

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GUI {
    private static File currentFile = null;
    public static void guibuild() {
        String[] columnNames = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
        String[][] data = { {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""}};

        JFrame frame = new JFrame();

        edittable model = new edittable(data, columnNames);
        JTable table = new JTable(model);

        JMenuBar menuBar = new JMenuBar();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.setTitle("Backbord IT | Stundenplan");
        frame.setSize(800, 300);

        JScrollPane sp = new JScrollPane(table);
        JMenu sonstiges = new JMenu("Sonstiges");
        JMenu file = new JMenu("Datei");
        JMenu einstellung = new JMenu("Einstellung");

        JMenuItem aboutItem = new JMenuItem("Über");
        aboutItem.addActionListener(e -> {aboutgui();});

        JMenuItem githubItem = new JMenuItem("Code");
        githubItem.addActionListener(e -> {    try {
            Desktop.getDesktop().browse(URI.create("https://github.com/ingressy/bgt-java/blob/main/stundenplan.java"));} catch (Exception ex) {ex.printStackTrace();}});

        JLabel timeLable = new JLabel("");

        JCheckBoxMenuItem datumItem = new JCheckBoxMenuItem("Zeit anzeigen");
        datumItem.setState(false);
        datumItem.addActionListener(e -> {
            if (datumItem.getState()) {
                model.addColumn("Zeit");
                timeLable.setText(timeLablebuilder());
            } else {
                timeLable.setText("");
            }
        });
        Timer timer = new Timer(1000, e -> { // alle 1000 ms = 1 Sekunde
            if (datumItem.getState()) {       // nur aktualisieren, wenn die Checkbox aktiviert ist
                timeLable.setText(timeLablebuilder());
            }
        });
        timer.start();

        JMenuItem saveItem = new JMenuItem("Speichern");
        saveItem.addActionListener(e -> {filehandler.save(currentFile, model, frame);});

        JMenuItem saveasItem = new JMenuItem("Speichern als");
        saveasItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Backbord IT Stundenplan Datei", "bis"));

            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getName().toLowerCase().endsWith(".bis")) {
                    selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + ".bis");
                }

                try (BufferedWriter writer = Files.newBufferedWriter(selectedFile.toPath())) {
                    int rowCount = model.getRowCount();
                    int colCount = model.getColumnCount();

                    for (int row = 0; row < rowCount; row++) {
                        for (int col = 0; col < colCount; col++) {
                            Object value = model.getValueAt(row, col);
                            writer.write(value != null ? value.toString() : "");
                            if (col < colCount - 1) writer.write(";");
                        }
                        writer.newLine();
                    }

                    JOptionPane.showMessageDialog(frame, "Tabelle gespeichert: " + selectedFile.getAbsolutePath(), "Info", JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame,
                            "Fehler beim Schreiben der Datei: " + ex.getMessage(),
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JCheckBoxMenuItem schreibenItem = new JCheckBoxMenuItem("Beschreibbar");
        schreibenItem.setState(false);
        schreibenItem.addActionListener(e -> model.setEditableCustom(schreibenItem.getState()));

        JMenuItem openItem = new JMenuItem("Öffnen");
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Backbord IT Stundenplan Datei", "bis"));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION ) {
                File selectedFile = fileChooser.getSelectedFile();
                currentFile = selectedFile;
                String fileName = selectedFile.getName();
                String extension = "";

                int i = fileName.lastIndexOf('.');
                if (i > 0 && i < fileName.length() - 1) {
                    extension = fileName.substring(i + 1).toLowerCase();
                }
                if (!extension.equals("bis")) { //bis == Backbord It Stundenplan
                    JOptionPane.showMessageDialog(frame,
                            "Ungültige Datei! Bitte wähle eine BIS-Datei.",
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.setTitle("Backbord IT | Stundenplan | " + selectedFile.getAbsolutePath());
                    try {
                        java.util.List<String> lines = Files.readAllLines(selectedFile.toPath());

                        // Alte Daten löschen
                        model.setRowCount(0);

                        for (String line : lines) {
                            String[] values = line.split(";"); // Trennzeichen
                            model.addRow(values);
                        }
                        JOptionPane.showMessageDialog(frame, selectedFile.getAbsolutePath() + " wurde erfolgreich geladen!", "Info", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame,
                                "Fehler beim Lesen der Datei: " + ex.getMessage(),
                                "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JPopupMenu mousetablepopup = new JPopupMenu();
        JMenuItem infosanzeigen = new JMenuItem("Informationen anzeigen");
        JMenuItem infobearbeiten = new JMenuItem("Informationen bearbeiten");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());

                    if (row >= 0 && col >= 0) {
                        table.setRowSelectionInterval(row, row);
                        table.setColumnSelectionInterval(col, col);
                    }

                    mousetablepopup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        infobearbeiten.addActionListener(ev -> {
            infogui.infoeditfenster(table);
        });

        mousetablepopup.add(infosanzeigen);
        mousetablepopup.add(infobearbeiten);

        menuBar.add(file);
        menuBar.add(einstellung);
        menuBar.add(sonstiges);

        file.add(openItem);
        file.add(saveItem);
        file.add(saveasItem);

        einstellung.add(datumItem);
        einstellung.add(schreibenItem);

        sonstiges.add(githubItem);
        sonstiges.add(aboutItem);

        frame.setJMenuBar(menuBar);

        panel.add(sp);
        panel.add(timeLable);

        frame.add(panel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void aboutgui() {
        JDialog dialog = new JDialog((Frame) null, "Über", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Backbord IT Stundenplan");
        JLabel version = new JLabel("Version 1.0");
        JLabel author = new JLabel("(c) 2017-2025 Backbord IT UG");

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

    private static String timeLablebuilder() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String stringtime = myDateObj.format(timeformat);
        String stringdate = myDateObj.format(dateformat);
        return "Es ist " + stringtime + " Uhr, am " + stringdate;
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
