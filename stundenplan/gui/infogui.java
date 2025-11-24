package gui;

import javax.swing.*;
import java.awt.*;

public class infogui {
    public static void infoeditfenster(JTable table) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        Object value = table.getValueAt(row, col);

        JFrame frame = new JFrame();
        JMenuBar menuBar = new JMenuBar();

        JMenuItem addbox = new JMenuItem("Hinzufügen");
        addbox.addActionListener(e -> {
            JFrame hframe = new JFrame();
            hframe.setTitle("Backbord IT | Hinzufügen |"+ value);

            String[] option = {"Raum", "Lehrkraft", "Besonderheit"};
            String getoption = (String) JOptionPane.showInputDialog(
                    null,
                    "Was möchstest du hinzufügen?",
                    "Hinzufügen",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    option,
                    option[0]);

            hframe.setVisible(true);
            hframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        });

        frame.setTitle("Backbord IT | Bearbeiten |"+ value);
        frame.setSize(800, 300);

        JPanel lessionbox = new JPanel() {
            private final int BOX_SIZE = 150;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int boxX = (getWidth() - BOX_SIZE) /2;
                int boxY = (getHeight() -BOX_SIZE) /2;
                g.setColor(Color.DARK_GRAY);
                g.fillRect(boxX,boxY,BOX_SIZE,BOX_SIZE);

                String txt = value.toString();
                g.setColor(Color.WHITE);
                FontMetrics fm = g.getFontMetrics();

                int textWidth = fm.stringWidth(txt);
                int textHeight = fm.getAscent();

                int textX = boxX + (BOX_SIZE - textWidth) /2;
                int textY = boxY + (BOX_SIZE - textHeight) /2;
                g.drawString(txt,textX,textY);
            }
        };
        menuBar.add(addbox);

        frame.setJMenuBar(menuBar);

        frame.add(lessionbox);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
