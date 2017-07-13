package app.view;

import javax.swing.*;
import java.awt.*;

class TextPanel extends JPanel {
    private final JTextArea textArea;

    TextPanel(int width, int height) {
        super(new BorderLayout());

        textArea = new JTextArea(height, width);
        textArea.setEditable(false);
        add(new JScrollPane(textArea));
    }

    void appendText(String text) {
        textArea.append(text + "\n");
    }

    void clearText() {
        textArea.setText("");
    }
}
