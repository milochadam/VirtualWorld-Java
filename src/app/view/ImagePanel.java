package app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class ImagePanel extends JPanel {
    private BufferedImage image = Images.empty;

    ImagePanel() {
        setMinimumSize(new Dimension(50, 50));
        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
    }

    void displayImage(BufferedImage image, String tooltip) {
        this.image = image;
        this.repaint();

        this.setToolTipText(tooltip);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, this);
    }
}
