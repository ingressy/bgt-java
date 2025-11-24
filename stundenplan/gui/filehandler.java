package gui;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.io.File;

public class filehandler {
    public static void save(File currentFile, edittable model, JFrame frame) {
        if (currentFile == null) {
            JOptionPane.showMessageDialog(frame, "Keine Datei geöffnet zum Speichern!", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (BufferedWriter writer = Files.newBufferedWriter(currentFile.toPath())) {
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
            JOptionPane.showMessageDialog(frame,currentFile.getAbsolutePath() + "wurde gespeichert!", "Info", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    "Fehler beim Schreiben der Datei: " + ex.getMessage(),
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
